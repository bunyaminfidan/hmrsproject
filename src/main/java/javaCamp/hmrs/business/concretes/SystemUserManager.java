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
import javaCamp.hmrs.core.utilities.verification.mernis.MernisVerificationService;
import javaCamp.hmrs.dataAccess.abstracts.BaseIndividualUserDao;
import javaCamp.hmrs.dataAccess.abstracts.SystemUserDao;
import javaCamp.hmrs.dataAccess.abstracts.UserDao;
import javaCamp.hmrs.entites.concretes.BaseIndividualUser;
import javaCamp.hmrs.entites.concretes.SystemUser;

@Service
public class SystemUserManager extends BaseIndividualUserManager implements SystemUserService {

	private SystemUserDao systemUserDao;

	@Autowired
	public SystemUserManager(UserDao userDao, BaseIndividualUserDao baseIndividualUserDao,

			MernisVerificationService mernisVerificationService, SystemUserDao systemUserDao) {
		super(userDao, baseIndividualUserDao, mernisVerificationService);
		this.systemUserDao = systemUserDao;

	}
	
	@Override
	public Result add(SystemUser systemUser, String passwordAgain) {
		if (GetUserDetailHelper.getSystemUserByNationalityId(systemUserDao, systemUser.getNationalityId()))
			return new ErrorResult("Tc Kimlik Numarası sistemde kayıtlı");
		
		if (!super.add(systemUser, passwordAgain).isSuccess()) 
			return new ErrorResult(super.add(systemUser, passwordAgain).getMessage());
		

		this.systemUserDao.save(systemUser);

		return new SuccessResult("Sistem personeli kayıt edildi");
	}



	@Override
	public DataResult<SystemUser> getByNationalityId(String nationalityId) {
		return new SuccessDataResult<SystemUser>(this.systemUserDao.findByNationalityIdIs(nationalityId),
				"Tc Kimlik Numarasına göre getirildi");
	}

	@Override
	public DataResult<List<SystemUser>> getAll() {

		return new SuccessDataResult<>(this.systemUserDao.findAll(), "Sistem personelleri getirildi");
	}







}
