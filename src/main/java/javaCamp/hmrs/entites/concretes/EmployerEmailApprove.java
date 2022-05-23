package javaCamp.hmrs.entites.concretes;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "approve_id")
@Table(name = "employer_email_approves")
public class EmployerEmailApprove extends BaseEmailApprove {


}
