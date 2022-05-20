package javaCamp.hmrs.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javaCamp.hmrs.business.abstracts.SystemUserService;
import javaCamp.hmrs.core.utilities.helpers.GetUserDetailHelper;
import javaCamp.hmrs.core.utilities.results.DataResult;
import javaCamp.hmrs.core.utilities.results.ErrorDataResult;
import javaCamp.hmrs.core.utilities.results.ErrorResult;
import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.core.utilities.results.SuccessDataResult;
import javaCamp.hmrs.core.utilities.results.SuccessResult;
import javaCamp.hmrs.core.utilities.validation.BaseIndividualValidator;
import javaCamp.hmrs.core.utilities.validation.FirstNameValidator;
import javaCamp.hmrs.core.utilities.validation.LastNameValidator;
import javaCamp.hmrs.core.utilities.validation.NationalityIdValidator;
import javaCamp.hmrs.core.utilities.verification.mernis.MernisVerificationServiceAdapter;
import javaCamp.hmrs.dataAccess.abstracts.SystemUserDao;
import javaCamp.hmrs.dataAccess.abstracts.UserDao;

import javaCamp.hmrs.entites.concretes.SystemUser;

@Service
public class SystemUserManager extends UserManager implements SystemUserService {

	private SystemUserDao systemUserDao;

	@Qualifier("mernisVerificationManager")
	private MernisVerificationServiceAdapter mernisVerificationService;

	@Autowired
	public SystemUserManager(UserDao userDao, SystemUserDao systemUserDao,
			@Qualifier("mernisVerificationManager") MernisVerificationServiceAdapter mernisVerificationService) {
		super(userDao);
		this.systemUserDao = systemUserDao;
		this.mernisVerificationService = mernisVerificationService;
	}

	@Override
	public DataResult<List<SystemUser>> getAll() {
		return new SuccessDataResult<>(this.systemUserDao.findAll(), "Sistem personelleri getirildi");
	}

	@Override
	public DataResult<SystemUser> getByNationalityId(String nationalityId) {

		return new SuccessDataResult<SystemUser>(this.systemUserDao.findByNationalityIdIs(nationalityId),
				"Tc Kimlik Numarasına göre getirildi");
	}

	@Override
	public Result add(SystemUser systemUser, String passwordAgain) {

		// Girilen değerlerin uygunluğunu kontrol eder
		if (!BaseIndividualValidator.checkValuesIndividualUser(systemUser.getFirstName(), systemUser.getLastName(),
				systemUser.getNationalityId(), systemUser.getDateOfBirth()).isSuccess())
			return new ErrorResult(BaseIndividualValidator.checkValuesIndividualUser(systemUser.getFirstName(),
					systemUser.getLastName(), systemUser.getNationalityId(), systemUser.getDateOfBirth()).getMessage());

		// Tc kimlik no kayıtlı mı sorgusu buraya gelecek.
		if (GetUserDetailHelper.getSystemUserByNationalityId(systemUserDao, systemUser.getNationalityId()))
			return new ErrorResult("Tc Kimlik Numarası sistemde kayıtlı");

		// Mernis Doğrulaması yapıyor.
		if (!mernisVerificationService.verify())
			return new ErrorResult("Kullanıcı bilgileri mernis ile doğrulanamadı");

		// Kullanıcı email ve password kontrol ve kayıt eder
		if (!super.add(systemUser, passwordAgain).isSuccess())
			return new ErrorResult(super.add(systemUser, passwordAgain).getMessage());

		systemUserDao.save(systemUser);
		return new SuccessResult("Sistem personeli kayıt edildi");

	}

}
