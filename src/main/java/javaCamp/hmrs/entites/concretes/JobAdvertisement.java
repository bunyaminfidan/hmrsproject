package javaCamp.hmrs.entites.concretes;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
//@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "job_advertisements")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })

public class JobAdvertisement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private int id;

	@Column(name = "min_salary")
	private double minSalary;

	@Column(name = "max_salary")
	private double maxSalary;

	@Column(name = "number_of_position")
	private int numberOfPosition;

	@Column(name = "application_deadline")
	private LocalDate applicationDeadline;
	
	@Column(name = "release_date")
	private LocalDate releaseDate;

	@Column(name = "description")
	private String description;

	@Column(name = "status")
	private boolean status;
	
	
	@ManyToOne()
	@JoinColumn(name = "city_id", referencedColumnName = "id" )
	private City city;

	@ManyToOne()
	@JoinColumn(name = "employer_id", referencedColumnName = "user_id")
	private EmployerUser employerUser;

	@ManyToOne()
	@JoinColumn(name = "job_position_id", referencedColumnName = "id")
	private JobPosition jobPosition;

	


}
