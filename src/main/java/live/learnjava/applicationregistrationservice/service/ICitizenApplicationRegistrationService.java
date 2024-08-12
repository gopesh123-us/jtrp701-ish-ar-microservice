package live.learnjava.applicationregistrationservice.service;

import live.learnjava.applicationregistrationservice.bindings.CitizenAppRegistrationInputs;

public interface ICitizenApplicationRegistrationService {
	public Integer registerCitizenApplication(CitizenAppRegistrationInputs entity);
}
