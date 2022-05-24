package javaCamp.hmrs.entites.concretes;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
//@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="job_postings")
@AllArgsConstructor
@NoArgsConstructor
public class JobPosting {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column(name="id", nullable = false)
	private int id;
	
	
	@Column(name="employer_id")
	private int employerId;
	
	@Column(name="job_position_id")
	private int jobPositionId;
	
	@Column(name="city_id")
	private int cityId;
	
	@Column(name="min_salary")
	private double minSalary;
	
	@Column(name="max_salary")
	private double maxSalary;
	
	@Column(name="number_of_open_position")
	private int numberOfOpenPosition;
	
	@Column(name="application_deadline")
	private LocalDate applicationDeadline;
	
	@Column(name="description")
	private String description;
	
	@Column(name="jobOpen")
	private boolean jobOpen;

}






