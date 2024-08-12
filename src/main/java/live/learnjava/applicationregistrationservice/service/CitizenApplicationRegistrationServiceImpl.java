package live.learnjava.applicationregistrationservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import live.learnjava.applicationregistrationservice.bindings.CitizenAppRegistrationInputs;
import live.learnjava.applicationregistrationservice.entity.CitizenAppRegistrationEntity;
import live.learnjava.applicationregistrationservice.repository.ICitizenApplicationRegistrationRepository;

@Service
public class CitizenApplicationRegistrationServiceImpl implements ICitizenApplicationRegistrationService {
	Logger logger = LoggerFactory.getLogger(CitizenApplicationRegistrationServiceImpl.class);
	@Autowired
	private RestTemplate template;

	@Autowired
	private ICitizenApplicationRegistrationRepository citizenRepo;

	@Value("${ar.ssa-web.url}")
	private String endPointUrl;

	@Value("${ar.state}")
	private String targetState;

	@Override
	public Integer registerCitizenApplication(CitizenAppRegistrationInputs inputs) {
		// perform webservice call to check if SSN is valid or not and get state
		logger.info("***endpointurl::"+endPointUrl);
		ResponseEntity<String> response = template.exchange(endPointUrl, HttpMethod.GET, null, String.class,
				inputs.getSsn());
		// get state
		String stateName = response.getBody();

		// register the citizen if he belongs to california state generate
		if (stateName.equalsIgnoreCase(targetState)) {
			// prepare entity object
			CitizenAppRegistrationEntity entity = new CitizenAppRegistrationEntity();
			BeanUtils.copyProperties(inputs, entity);
			entity.setStateName(stateName);

			// save the object and generate appId
			int appId = citizenRepo.save(entity).getAppId();
			return appId;
		}
		return 0;
	}

}
