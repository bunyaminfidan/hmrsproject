package javaCamp.hmrs.business.abstracts;

import java.util.List;

import javaCamp.hmrs.core.utilities.results.DataResult;
import javaCamp.hmrs.entites.concretes.User;

public interface UserService {

	DataResult<List<User>> getAll();

}
