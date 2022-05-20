package javaCamp.hmrs.business.abstracts;

import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.entites.concretes.Approve;

public interface EmployerEmailApproveService {
	
	Result add(Approve approve);

}
