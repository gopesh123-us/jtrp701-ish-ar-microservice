package live.learnjava.applicationregistrationservice.bindings;

import java.time.LocalDate;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class CitizenAppRegistrationInputs {
	@Column(length = 30)
	private String fullName;

	@Column(length = 50)
	private String email;

	@Column()
	private Long phoneNo;

	@Column()
	private Long ssn;

	@Column(length = 1)
	private String gender;
	
	@Column()
	private LocalDate dob;
}
