package javaCamp.hmrs.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javaCamp.hmrs.business.abstracts.UserService;
import javaCamp.hmrs.core.utilities.helpers.GetUserDetailHelper;
import javaCamp.hmrs.core.utilities.results.DataResult;
import javaCamp.hmrs.core.utilities.results.ErrorResult;
import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.core.utilities.results.SuccessDataResult;
import javaCamp.hmrs.core.utilities.results.SuccessResult;
import javaCamp.hmrs.core.utilities.validation.BaseIndividualValidator;
import javaCamp.hmrs.dataAccess.abstracts.UserDao;
import javaCamp.hmrs.entites.concretes.Approve;
import javaCamp.hmrs.entites.concretes.User;

@Service
public class UserManager implements UserService {

	UserDao userDao;


	@Autowired
	public UserManager(UserDao userDao) {
		this.userDao = userDao;

	}

	@Override
	public Result add(User user, String passwordAgain) {

		if (!BaseIndividualValidator.checkValuesUser(user, passwordAgain).isSuccess())
			return new ErrorResult(BaseIndividualValidator.checkValuesUser(user, passwordAgain).getMessage());

		if (GetUserDetailHelper.isEmailRegistered(userDao, user.getEmail())) {
			return new ErrorResult("Email sistemde kayıtlı");
		} else {
			return new SuccessResult("Kullanıcı kayıt için uygun");
		}

	}

	@Override
	public DataResult<User> getByEmail(String email) {

		return new SuccessDataResult<User>(this.userDao.findByEmail(email));

	}

	@Override
	public DataResult<User> verifyEmail(User user, Approve approve) {
		
		
		
		
		
		
		return null;
		
		
	
		
	}

}
