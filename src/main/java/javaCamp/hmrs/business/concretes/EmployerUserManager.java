package javaCamp.hmrs.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javaCamp.hmrs.business.abstracts.EmployerUserService;
import javaCamp.hmrs.core.utilities.helpers.GetUserDetailHelper;
import javaCamp.hmrs.core.utilities.results.DataResult;
import javaCamp.hmrs.core.utilities.results.ErrorResult;
import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.core.utilities.results.SuccessDataResult;
import javaCamp.hmrs.core.utilities.results.SuccessResult;
import javaCamp.hmrs.core.utilities.validation.CompanyNameValidator;
import javaCamp.hmrs.core.utilities.validation.EmailIsWebsiteDomainValidator;
import javaCamp.hmrs.core.utilities.validation.PhoneNumberValidator;
import javaCamp.hmrs.core.utilities.validation.WebsiteValidator;
import javaCamp.hmrs.dataAccess.abstracts.EmployerUserDao;
import javaCamp.hmrs.dataAccess.abstracts.UserDao;
import javaCamp.hmrs.entites.concretes.EmployerUser;
import javaCamp.hmrs.entites.concretes.User;

@Service
public class EmployerUserManager extends UserManager implements EmployerUserService {

	private EmployerUserDao employerUserDao;

	@Autowired
	public EmployerUserManager(UserDao userDao, EmployerUserDao employerUserDao) {
		super(userDao);
		this.employerUserDao = employerUserDao;
	}

	@Override
	public DataResult<List<EmployerUser>> getAll() {

		return new SuccessDataResult<>(this.employerUserDao.findAll(), "İş verenler getirildi");
	}

	@Override
	public Result add(EmployerUser employerUser, String passwordAgain) {

		if (!checkValues(employerUser, passwordAgain).isSuccess())
			return new ErrorResult(checkValues(employerUser, passwordAgain).getMessage());

		if (!super.add(employerUser, passwordAgain).isSuccess())
			return new ErrorResult(super.add(employerUser, passwordAgain).getMessage());

		int getUserId = GetUserDetailHelper.getUserId(super.userDao, employerUser);
		if (getUserId != 0) {
			employerUser.setId(getUserId);
			this.employerUserDao.save(employerUser);
			return new SuccessResult("İşveren kayıt edildi");
		} else {
			return new ErrorResult("İşveren kayıt edilemedi");
		}

	}

	Result checkValues(EmployerUser employerUser, String passwordAgain) {

		Result companyNameValid = CompanyNameValidator.valid(employerUser.getCompanyName());
		Result phoneNumberValid = PhoneNumberValidator.valid(employerUser.getPhoneNumber());
		Result websiteValid = WebsiteValidator.valid(employerUser.getWebsite());
		Result emailIsWebsiteDomain = EmailIsWebsiteDomainValidator.valid(employerUser.getEmail(),
				employerUser.getWebsite());

		if (!companyNameValid.isSuccess())
			return new ErrorResult(companyNameValid.getMessage());

		if (!phoneNumberValid.isSuccess())
			return new ErrorResult(phoneNumberValid.getMessage());

		if (!websiteValid.isSuccess())
			return new ErrorResult(websiteValid.getMessage());

		if (!emailIsWebsiteDomain.isSuccess())
			return new ErrorResult(emailIsWebsiteDomain.getMessage());

		return new SuccessResult();
	}

}
