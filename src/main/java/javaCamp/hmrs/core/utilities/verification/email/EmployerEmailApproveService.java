package javaCamp.hmrs.core.utilities.verification.email;

import javaCamp.hmrs.core.utilities.results.DataResult;
import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.entites.concretes.BaseEmailApprove;
import javaCamp.hmrs.entites.concretes.EmployerEmailApprove;
import javaCamp.hmrs.entites.concretes.JobSeekerEmailApprove;

public interface EmployerEmailApproveService extends EmailVerificationService {
	
	

	
	DataResult<EmployerEmailApprove> getApproveByVerifyCode(String verifyCode);
	


}
