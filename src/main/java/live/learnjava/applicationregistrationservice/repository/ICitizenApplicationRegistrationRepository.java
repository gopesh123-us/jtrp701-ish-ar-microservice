package live.learnjava.applicationregistrationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import live.learnjava.applicationregistrationservice.entity.CitizenAppRegistrationEntity;

public interface ICitizenApplicationRegistrationRepository
		extends JpaRepository<CitizenAppRegistrationEntity, Integer> {

}
