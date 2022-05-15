package javaCamp.hmrs.business.abstracts;


import java.util.List;

import javaCamp.hmrs.core.utilities.results.DataResult;
import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.entites.concretes.User;

public interface UserService {

	Result add(User user);
	
	DataResult<User> getByEmail(String email);
	
}
