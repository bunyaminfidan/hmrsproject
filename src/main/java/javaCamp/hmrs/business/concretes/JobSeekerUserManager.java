package javaCamp.hmrs.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javaCamp.hmrs.business.abstracts.JobSeekerUserService;
import javaCamp.hmrs.core.utilities.helpers.GetUserDetailHelper;
import javaCamp.hmrs.core.utilities.results.DataResult;
import javaCamp.hmrs.core.utilities.results.ErrorResult;
import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.core.utilities.results.SuccessDataResult;
import javaCamp.hmrs.core.utilities.results.SuccessResult;
import javaCamp.hmrs.core.utilities.validation.FirstNameValidator;
import javaCamp.hmrs.core.utilities.validation.LastNameValidator;
import javaCamp.hmrs.core.utilities.validation.NationalityIdValidator;
import javaCamp.hmrs.dataAccess.abstracts.JobSeekerUserDao;
import javaCamp.hmrs.dataAccess.abstracts.UserDao;
import javaCamp.hmrs.entites.concretes.JobSeekerUser;
import javaCamp.hmrs.entites.concretes.SystemUser;

@Service
public class JobSeekerUserManager extends UserManager implements JobSeekerUserService {

	private JobSeekerUserDao jobSeekerUserDao;

	@Autowired
	public JobSeekerUserManager(UserDao userDao) {
		super(userDao);
	}

	@Override
	public DataResult<List<JobSeekerUser>> getAll() {
		return new SuccessDataResult<>(this.jobSeekerUserDao.findAll(), "İş arayanlar getirildi");

	}

	@Override
	public Result add(JobSeekerUser jobSeekerUser, String passwordAgain) {

		if (!checkValues(jobSeekerUser, passwordAgain).isSuccess())
			return new ErrorResult(checkValues(jobSeekerUser, passwordAgain).getMessage());

		
		//Tc kimlik no kayıtlı mı sorgusu buraya gelecek.
		
		
		if (!super.add(jobSeekerUser, passwordAgain).isSuccess())
			return new ErrorResult(super.add(jobSeekerUser, passwordAgain).getMessage());

		int getUserId = GetUserDetailHelper.getUserId(super.userDao, jobSeekerUser);

		if (getUserId != 0) {
			jobSeekerUser.setId(getUserId);
			this.userDao.save(jobSeekerUser);
			return new SuccessResult("Sistem personeli kayıt edildi");
		} else {
			return new ErrorResult("Sistem personeli kayıt edilemedi");
		}
	}
	
	
	
	Result checkValues(SystemUser jobSeekerUser, String passwordAgain) {

		Result firstNameValid = FirstNameValidator.valid(jobSeekerUser.getFirstName());
		Result lastNameValid = LastNameValidator.valid(jobSeekerUser.getLastName());
		Result nationalityIdValid = NationalityIdValidator.valid(jobSeekerUser.getNationalityId());

		if (jobSeekerUser.getDateOfBirth() == null)
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
