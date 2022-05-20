package javaCamp.hmrs.core.utilities.verification.email;

import javaCamp.hmrs.core.utilities.results.DataResult;
import javaCamp.hmrs.entites.concretes.BaseEmailApprove;
import javaCamp.hmrs.entites.concretes.EmployerEmailApprove;

public interface EmployerEmailApproveService extends EmailVerificationService {
	DataResult<EmployerEmailApprove> verifyCode(EmployerEmailApprove employerEmailApprove);

}
