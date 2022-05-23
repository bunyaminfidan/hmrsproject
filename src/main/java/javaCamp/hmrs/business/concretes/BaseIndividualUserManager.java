package javaCamp.hmrs.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javaCamp.hmrs.business.abstracts.BaseIndividualUserService;
import javaCamp.hmrs.core.utilities.helpers.GetUserDetailHelper;
import javaCamp.hmrs.core.utilities.results.DataResult;
import javaCamp.hmrs.core.utilities.results.ErrorResult;
import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.core.utilities.results.SuccessDataResult;
import javaCamp.hmrs.core.utilities.results.SuccessResult;
import javaCamp.hmrs.core.utilities.validation.BaseIndividualValidator;
import javaCamp.hmrs.core.utilities.verification.mernis.MernisVerificationService;
import javaCamp.hmrs.dataAccess.abstracts.BaseIndividualUserDao;
import javaCamp.hmrs.dataAccess.abstracts.UserDao;
import javaCamp.hmrs.entites.concretes.BaseIndividualUser;

@Service
public class BaseIndividualUserManager extends UserManager implements BaseIndividualUserService {

	
	private BaseIndividualUserDao baseIndividualUserDao;
	
	@Qualifier("mernisVerificationManager")
	private MernisVerificationService mernisVerificationService;
	
	@Autowired
	public BaseIndividualUserManager(UserDao userDao, BaseIndividualUserDao baseIndividualUserDao,
			@Qualifier("mernisVerificationManager")	MernisVerificationService mernisVerificationService) {
		super(userDao);
		this.baseIndividualUserDao = baseIndividualUserDao;
		this.mernisVerificationService = mernisVerificationService;
	}

	@Override
	public Result add(BaseIndividualUser baseIndividualUser, String passwordAgain) {
		// Girilen değerlerin uygunluğunu kontrol eder
		if (!BaseIndividualValidator.checkValuesIndividualUser(baseIndividualUser.getFirstName(), baseIndividualUser.getLastName(),
				baseIndividualUser.getNationalityId(), baseIndividualUser.getDateOfBirth()).isSuccess())
			return new ErrorResult(BaseIndividualValidator.checkValuesIndividualUser(baseIndividualUser.getFirstName(),
					baseIndividualUser.getLastName(), baseIndividualUser.getNationalityId(), baseIndividualUser.getDateOfBirth()).getMessage());

		// Mernis Doğrulaması yapıyor.
		if (!mernisVerificationService.verify())
			return new ErrorResult("Kullanıcı bilgileri mernis ile doğrulanamadı");

		// Kullanıcı email ve password kontrol ve kayıt eder
		if (!super.add(baseIndividualUser, passwordAgain).isSuccess())
			return new ErrorResult(super.add(baseIndividualUser, passwordAgain).getMessage());
		
		return new SuccessResult("Kayıt edildi");
	}
}
