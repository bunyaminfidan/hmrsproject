package javaCamp.hmrs.core.utilities.verification.email;

import javaCamp.hmrs.core.utilities.results.DataResult;
import javaCamp.hmrs.entites.concretes.EmployerEmailApprove;

public interface EmployerEmailApproveService extends EmailVerificationService {
	
	

	
	DataResult<EmployerEmailApprove> getApproveByVerifyCode(String verifyCode);
	


}
