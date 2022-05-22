package javaCamp.hmrs.core.utilities.verification.email;

import javaCamp.hmrs.core.utilities.results.DataResult;
import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.entites.concretes.BaseEmailApprove;
import javaCamp.hmrs.entites.concretes.User;

public interface EmailVerificationService {

	Result add(BaseEmailApprove baseEmailApprove );
	
	Result verifyemail(String code );	

	//DataResult<BaseEmailApprove> getApproveByVerifyCode(String verifyCode);



}
