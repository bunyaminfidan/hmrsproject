package javaCamp.hmrs.core.utilities.verification.systemManager;

import javaCamp.hmrs.core.utilities.results.DataResult;
import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.entites.concretes.EmployerUser;
import javaCamp.hmrs.entites.concretes.SystemManagerEmployerUserApprove;

public interface SystemManagerVerificationService {

	Result add(int userId, int systemManagerId);

	DataResult<SystemManagerEmployerUserApprove> getByEmployerUserId(int id);

}
