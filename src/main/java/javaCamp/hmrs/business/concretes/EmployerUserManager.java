package javaCamp.hmrs.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javaCamp.hmrs.business.abstracts.EmployerUserService;
import javaCamp.hmrs.core.utilities.helpers.GetUserDetailHelper;
import javaCamp.hmrs.core.utilities.results.DataResult;
import javaCamp.hmrs.core.utilities.results.ErrorResult;
import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.core.utilities.results.SuccessDataResult;
import javaCamp.hmrs.core.utilities.results.SuccessResult;
import javaCamp.hmrs.core.utilities.validation.BaseCompanyUserValidator;
import javaCamp.hmrs.core.utilities.validation.CompanyNameValidator;
import javaCamp.hmrs.core.utilities.validation.EmailIsWebsiteDomainValidator;
import javaCamp.hmrs.core.utilities.validation.PhoneNumberValidator;
import javaCamp.hmrs.core.utilities.validation.WebsiteValidator;
import javaCamp.hmrs.core.utilities.verification.mernis.MernisVerificationServiceAdapter;
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

		if (!BaseCompanyUserValidator.checkValues(employerUser).isSuccess())

			return new ErrorResult(BaseCompanyUserValidator.checkValues(employerUser).getMessage());
		if (!super.add(employerUser, passwordAgain).isSuccess())
			return new ErrorResult(super.add(employerUser, passwordAgain).getMessage());

		this.employerUserDao.save(employerUser);
		return new SuccessResult("İşveren kayıt edildi");

	}

}
