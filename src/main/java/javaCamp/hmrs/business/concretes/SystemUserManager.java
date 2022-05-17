package javaCamp.hmrs.business.concretes;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javaCamp.hmrs.business.abstracts.SystemUserService;
import javaCamp.hmrs.core.utilities.helpers.GetUserDetailHelper;
import javaCamp.hmrs.core.utilities.results.DataResult;
import javaCamp.hmrs.core.utilities.results.ErrorResult;
import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.core.utilities.results.SuccessDataResult;
import javaCamp.hmrs.core.utilities.results.SuccessResult;
import javaCamp.hmrs.core.utilities.validation.DateOfBirthValidator;
import javaCamp.hmrs.core.utilities.validation.FirstNameValidator;
import javaCamp.hmrs.core.utilities.validation.LastNameValidator;
import javaCamp.hmrs.core.utilities.validation.NationalityIdValidator;
import javaCamp.hmrs.dataAccess.abstracts.SystemUserDao;
import javaCamp.hmrs.dataAccess.abstracts.UserDao;

import javaCamp.hmrs.entites.concretes.SystemUser;

@Service
public class SystemUserManager extends UserManager implements SystemUserService {

	private SystemUserDao systemUserDao;

	@Autowired
	public SystemUserManager(UserDao userDao, SystemUserDao systemUserDao) {
		super(userDao);
		this.systemUserDao = systemUserDao;
		// TODO Auto-generated constructor stub
	}

	@Override
	public DataResult<List<SystemUser>> getAll() {
		return new SuccessDataResult<>(this.systemUserDao.findAll(), "Sistem personelleri getirildi");
	}

	@Override
	public Result add(SystemUser systemUser, String passwordAgain) {

		if (!checkValues(systemUser, passwordAgain).isSuccess())
			return new ErrorResult(checkValues(systemUser, passwordAgain).getMessage());

		if (!super.add(systemUser,passwordAgain).isSuccess())
			return new ErrorResult(super.add(systemUser,passwordAgain).getMessage());

		int getUserId = GetUserDetailHelper.getUserId(super.userDao, systemUser);

		if (getUserId != 0) {
			systemUser.setId(getUserId);
			this.systemUserDao.save(systemUser);
			return new SuccessResult("Sistem personeli kayıt edildi");
		} else {
			return new ErrorResult("Sistem personeli kayıt edilemedi");
		}

	}

	Result checkValues(SystemUser systemUser, String passwordAgain) {

		Result firstNameValid = FirstNameValidator.valid(systemUser.getFirstName());
		Result lastNameValid = LastNameValidator.valid(systemUser.getLastName());
		Result nationalityIdValid = NationalityIdValidator.valid(systemUser.getNationalityId());

		if (systemUser.getDateOfBirth() == null)
			return new ErrorResult("Dogum tarihi boş olamaz");

		if (!firstNameValid.isSuccess())
			return new ErrorResult(firstNameValid.getMessage());

		if (!lastNameValid.isSuccess())
			return new ErrorResult(lastNameValid.getMessage());

		if (!nationalityIdValid.isSuccess())
			return new ErrorResult(nationalityIdValid.getMessage());

		return new SuccessResult();
	}

}
