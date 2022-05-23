package javaCamp.hmrs.entites.concretes;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;


import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper=false)
@MappedSuperclass
public class BaseEmailApprove extends Approve {
	
	@Column(name = "verify_code")  
	private String verifyCode;

}
