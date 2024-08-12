package live.learnjava.applicationregistrationservice.ms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import live.learnjava.applicationregistrationservice.bindings.CitizenAppRegistrationInputs;
import live.learnjava.applicationregistrationservice.service.ICitizenApplicationRegistrationService;

@RestController
@RequestMapping("/citizen-ar-api")
public class CitizenApplicationRegistrationOperationsController {

	@Autowired
	private ICitizenApplicationRegistrationService service;

	@PostMapping("/save")
	public ResponseEntity<String> saveCitizenApplication(@RequestBody CitizenAppRegistrationInputs inputs) {

		try {
			// use service
			int appId = service.registerCitizenApplication(inputs);
			if (appId > 0) {
				return new ResponseEntity<String>("Citizen Application is Registered with the id::" + appId,
						HttpStatus.CREATED);
			} else {
				return new ResponseEntity<String>("Invalid SSN or Citizen must belong to California state::" + appId,
						HttpStatus.OK);
			}

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
