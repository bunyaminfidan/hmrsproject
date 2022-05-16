package javaCamp.hmrs.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
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
import javaCamp.hmrs.core.utilities.validation.EmailValidator;
import javaCamp.hmrs.core.utilities.validation.PasswordValidator;
import javaCamp.hmrs.core.utilities.validation.PhoneNumberValidator;
import javaCamp.hmrs.core.utilities.validation.WebsiteValidator;
import javaCamp.hmrs.core.utilities.verification.email.EmailVerificationService;
import javaCamp.hmrs.dataAccess.abstracts.EmployerUserDao;
import javaCamp.hmrs.dataAccess.abstracts.UserDao;
import javaCamp.hmrs.entites.concretes.EmployerUser;

@Service
@Component
@Qualifier("EmployerUserManager")
@Primary
public class EmployerUserManager extends UserManager implements EmployerUserService {

	private EmployerUserDao employerUserDao;

	@Autowired
	public EmployerUserManager(UserDao userDao, EmployerUserDao employerUserDao) {
		super(userDao);
		this.employerUserDao = employerUserDao;
	}

	@Override
	public DataResult<List<EmployerUser>> getAll() {
		// TODO Auto-generated method stub
		return new SuccessDataResult<>(this.employerUserDao.findAll(), "İş verenler getirildi");
	}

	@Override
	public Result add(EmployerUser employerUser, String passwordAgain) {

		if (!checkValues(employerUser, passwordAgain).isSuccess())
			return new ErrorResult(checkValues(employerUser, passwordAgain).getMessage());

		if (!super.add(employerUser).isSuccess())
			return new ErrorResult("İşveren sistemde zaten kayıtlı");

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
		Result emailVaid = EmailValidator.valid(employerUser.getEmail());
		Result phoneNumberValid = PhoneNumberValidator.valid(employerUser.getPhoneNumber());
		Result websiteValid = WebsiteValidator.valid(employerUser.getWebsite());
		Result passwordValid = PasswordValidator.valid(employerUser.getPassword(), passwordAgain);
		Result emailIsWebsiteDomain = EmailIsWebsiteDomainValidator.valid(employerUser.getEmail(),
				employerUser.getWebsite());

		if (!companyNameValid.isSuccess()) {
			return new ErrorResult(companyNameValid.getMessage());
		} else if (!emailVaid.isSuccess()) {
			return new ErrorResult(emailVaid.getMessage());
		} else if (!phoneNumberValid.isSuccess()) {
			return new ErrorResult(phoneNumberValid.getMessage());
		} else if (!websiteValid.isSuccess()) {
			return new ErrorResult(websiteValid.getMessage());
		} else if (!emailIsWebsiteDomain.isSuccess()) {
			return new ErrorResult(emailIsWebsiteDomain.getMessage());
		} else if (!passwordValid.isSuccess()) {
			return new ErrorResult(passwordValid.getMessage());
		}

		return new SuccessResult();
	}

}
