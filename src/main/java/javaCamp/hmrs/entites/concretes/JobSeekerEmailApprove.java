package javaCamp.hmrs.entites.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

public class JobSeekerEmailApprove {

	@Entity
	@Data
	@EqualsAndHashCode(callSuper = false)
	@AllArgsConstructor
	@NoArgsConstructor
	@PrimaryKeyJoinColumn(name = "approve_id")
	@Table(name = "job_seeker_email_approves")
	public class EmployerEmailApprove extends Approve {

		@Column(name = "email")
		private String email;

	}

}
