package javaCamp.hmrs.business.abstracts;


import java.util.List;

import javaCamp.hmrs.core.utilities.results.DataResult;
import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.entites.concretes.EmployerUser;

public interface EmployerUserService {
	
	DataResult<List<EmployerUser>> getAll();
	
	Result add(EmployerUser employerUser);

}
