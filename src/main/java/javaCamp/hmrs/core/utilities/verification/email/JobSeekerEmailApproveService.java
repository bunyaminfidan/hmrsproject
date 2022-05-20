package javaCamp.hmrs.core.utilities.verification.email;

import javaCamp.hmrs.core.utilities.results.DataResult;
import javaCamp.hmrs.entites.concretes.BaseEmailApprove;
import javaCamp.hmrs.entites.concretes.JobSeekerEmailApprove;

public interface JobSeekerEmailApproveService  extends EmailVerificationService{
	

	DataResult<JobSeekerEmailApprove> verifyCode(String code );
	
	


}
