package live.learnjava.applicationregistrationservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import live.learnjava.applicationregistrationservice.bindings.CitizenAppRegistrationInputs;
import live.learnjava.applicationregistrationservice.entity.CitizenAppRegistrationEntity;
import live.learnjava.applicationregistrationservice.exceptions.InvalidSSNException;
import live.learnjava.applicationregistrationservice.repository.ICitizenApplicationRegistrationRepository;
import reactor.core.publisher.Mono;

@Service
public class CitizenApplicationRegistrationServiceImpl implements ICitizenApplicationRegistrationService {
	Logger logger = LoggerFactory.getLogger(CitizenApplicationRegistrationServiceImpl.class);
	
	@Autowired private RestTemplate template;
	
	@Autowired
	private WebClient webClient;

	@Autowired
	private ICitizenApplicationRegistrationRepository citizenRepo;

	@Value("${ar.ssa-web.url}")
	private String endPointUrl;

	@Value("${ar.state}")
	private String targetState;

	@Override
	public Integer registerCitizenApplication(CitizenAppRegistrationInputs inputs) throws InvalidSSNException {
		logger.info("***endpointurl::" + endPointUrl);
		/** using RestTemplate **/
		/** perform webservice all to check if SSN is valid or not **/
		/*
		 * ResponseEntity<String> response = template.exchange(endPointUrl,
		 * HttpMethod.GET, null, String.class, inputs.getSsn());
		 * String stateName = response.getBody();
		 */
		/** start using webclient **/
		Mono<String> response = webClient.get()
		         .uri(endPointUrl, inputs.getSsn())
		         .retrieve()
		         .onStatus(HttpStatus.BAD_REQUEST::equals, res ->
		                 res.bodyToMono(String.class)
		                         .flatMap(errorBody -> Mono.error(new InvalidSSNException(errorBody))))
		         .bodyToMono(String.class);
		 String stateName = response.block();

		/** end using webclient **/
		
		if (stateName.equalsIgnoreCase(targetState)) {
			// prepare entity object
			CitizenAppRegistrationEntity entity = new CitizenAppRegistrationEntity();
			BeanUtils.copyProperties(inputs, entity);
			entity.setStateName(stateName);

			// save the object and generate appId
			int appId = citizenRepo.save(entity).getAppId();
			return appId;
		}
		throw new InvalidSSNException("Looser::Invalid SSN");
	}

}
