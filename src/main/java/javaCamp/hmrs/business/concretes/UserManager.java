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
import javaCamp.hmrs.core.utilities.validation.EmailValidator;
import javaCamp.hmrs.core.utilities.validation.PasswordValidator;
import javaCamp.hmrs.core.utilities.verification.email.EmailVerificationService;
import javaCamp.hmrs.dataAccess.abstracts.UserDao;
import javaCamp.hmrs.entites.concretes.User;

@Service
public class UserManager implements UserService {

	UserDao userDao;
	EmailVerificationService emailVerificationService;

	@Autowired
	public UserManager(UserDao userDao) {
		this.userDao = userDao;

	}

	@Override
	public Result add(User user, String passwordAgain) {

		if (!checkValues(user, passwordAgain).isSuccess())
			return new ErrorResult(checkValues(user, passwordAgain).getMessage());

		if (GetUserDetailHelper.isEmailRegistered(userDao, user.getEmail())) {
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

	@Override
	public Result verifyEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	Result checkValues(User user, String passwordAgain) {

		Result emailVaid = EmailValidator.valid(user.getEmail());
		Result passwordValid = PasswordValidator.valid(user.getPassword(), passwordAgain);

		if (!emailVaid.isSuccess())
			return new ErrorResult(emailVaid.getMessage());

		if (!passwordValid.isSuccess())
			return new ErrorResult(passwordValid.getMessage());

		return new SuccessResult();
	}

}
