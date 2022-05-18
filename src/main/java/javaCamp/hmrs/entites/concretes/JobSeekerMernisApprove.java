package javaCamp.hmrs.entites.concretes;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

public class JobSeekerMernisApprove {

	@Entity
	@Data
	@EqualsAndHashCode(callSuper = false)
	// @AllArgsConstructor
	// @NoArgsConstructor
	@PrimaryKeyJoinColumn(name = "approve_id")
	@Table(name = "job_seeker_mernis_approves")
	public class EmployerEmailApprove extends Approve {

	}

}
