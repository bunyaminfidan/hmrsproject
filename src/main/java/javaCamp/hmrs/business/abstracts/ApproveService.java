package javaCamp.hmrs.business.abstracts;

import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.entites.concretes.Approve;

public interface ApproveService {

	Result add(Approve approve);

//	DataResult<Approve> getByUserId(int userId);

}
