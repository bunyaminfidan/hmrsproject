package javaCamp.hmrs.entites.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
// @AllArgsConstructor
// @NoArgsConstructor
@PrimaryKeyJoinColumn(name = "approve_id")
@Table(name = "job_seeker_mernis_approves")
public class JobSeekerMernisApprove extends Approve {
	
//	@Column(name="approve_id")
//	private int approveId;

}
