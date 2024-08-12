package live.learnjava.applicationregistrationservice.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CITIZEN_APPLICATION")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CitizenAppRegistrationEntity {

	//@formatter:off
	@SequenceGenerator(name = "gen1_seq", 
						sequenceName = "app_id_seq", 
						initialValue = 1000, 
						allocationSize = 1)
	//@formatter:on
	@Id
	@GeneratedValue(generator = "gen1_seq", strategy = GenerationType.SEQUENCE)
	private Integer appId;

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

	@Column(length = 30)
	private String stateName;
	
	@CreationTimestamp()
	@Column(insertable = true, updatable = false)
	private LocalDateTime createdAt;

	@Column(length = 30)
	private String createdBy;

	@UpdateTimestamp()
	@Column(insertable = false, updatable = true)
	private LocalDateTime updatedAt;

	@Column(length = 30)
	private String updatedBy;

}
