package javaCamp.hmrs.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import javaCamp.hmrs.business.abstracts.UserService;
import javaCamp.hmrs.core.utilities.helpers.IsEmailRegistered;
import javaCamp.hmrs.core.utilities.results.DataResult;
import javaCamp.hmrs.core.utilities.results.ErrorDataResult;
import javaCamp.hmrs.core.utilities.results.ErrorResult;
import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.core.utilities.results.SuccessDataResult;
import javaCamp.hmrs.core.utilities.results.SuccessResult;
import javaCamp.hmrs.dataAccess.abstracts.UserDao;
import javaCamp.hmrs.entites.concretes.User;

@Service
@Component
@Qualifier("UserManager")

public class UserManager implements UserService {

	UserDao userDao;

	@Autowired
	public UserManager(UserDao userDao) {
		super();
		this.userDao = userDao;
	}

	@Override
	public Result add(User user) {

		if (IsEmailRegistered.userEmailCheck(user.getEmail(), userDao)) {

			return new ErrorResult("Email sistemde kayıtlı");
		} else {
			this.userDao.save(user);
			return new SuccessResult("Kullanıcı kaydedildi");
		}

	}

	@Override
	public DataResult<User> getByEmail(String email) {

		return new SuccessDataResult<User>(this.userDao.findByEmail(email));

	}

}
