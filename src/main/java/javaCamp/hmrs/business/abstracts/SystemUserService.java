package javaCamp.hmrs.business.abstracts;

import java.util.List;

import javaCamp.hmrs.core.utilities.results.DataResult;
import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.entites.concretes.SystemUser;
import javaCamp.hmrs.entites.concretes.User;

public interface SystemUserService {
	
	DataResult<List<SystemUser>> getAll();
	
	Result add(SystemUser systemUser, String passwordAgain);
	
	DataResult<SystemUser>  getByNationalityId(String nationalityId);


}
