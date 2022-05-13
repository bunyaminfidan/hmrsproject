package javaCamp.hmrs.business.abstracts;


import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.entites.concretes.User;

public interface UserService {

	
	Result emailCheck(User user);
}
