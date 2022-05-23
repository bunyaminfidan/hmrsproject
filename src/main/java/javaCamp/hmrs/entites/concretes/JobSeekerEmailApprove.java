package javaCamp.hmrs.entites.concretes;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
//@AllArgsConstructor
//@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "approve_id")
@Table(name = "job_seeker_email_approves")
public class JobSeekerEmailApprove extends BaseEmailApprove {


//		@Column(name = "email")
//		private String email;

	

}
