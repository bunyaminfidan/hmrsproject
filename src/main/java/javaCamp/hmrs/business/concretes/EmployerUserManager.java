package javaCamp.hmrs.business.concretes;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javaCamp.hmrs.business.abstracts.EmployerUserService;
import javaCamp.hmrs.core.utilities.helpers.RandomUUIDCodeHelper;
import javaCamp.hmrs.core.utilities.results.DataResult;
import javaCamp.hmrs.core.utilities.results.ErrorResult;
import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.core.utilities.results.SuccessDataResult;
import javaCamp.hmrs.core.utilities.results.SuccessResult;
import javaCamp.hmrs.core.utilities.validation.BaseCompanyUserValidator;
import javaCamp.hmrs.core.utilities.verification.email.EmailVerificationService;
import javaCamp.hmrs.core.utilities.verification.email.EmployerEmailApproveService;
import javaCamp.hmrs.core.utilities.verification.systemManager.SystemManagerVerificationManager;
import javaCamp.hmrs.dataAccess.abstracts.EmployerUserDao;
import javaCamp.hmrs.dataAccess.abstracts.UserDao;
import javaCamp.hmrs.entites.concretes.EmployerEmailApprove;
import javaCamp.hmrs.entites.concretes.EmployerUser;

@Service
public class EmployerUserManager extends UserManager implements EmployerUserService {

	private EmployerUserDao employerUserDao;
	private EmployerEmailApproveService employerEmailApproveService;

	@Qualifier("emailVerificationManager")
	private EmailVerificationService emailVerificationService;

	private SystemManagerVerificationManager systemManagerVerificationManager;

	@Autowired
	public EmployerUserManager(UserDao userDao, EmployerUserDao employerUserDao,
			@Qualifier("emailVerificationManager") EmailVerificationService emailVerificationService,
			SystemManagerVerificationManager systemManagerVerificationManager,
			EmployerEmailApproveService employerEmailApproveService) {
		super(userDao);
		this.employerUserDao = employerUserDao;
		this.emailVerificationService = emailVerificationService;
		this.systemManagerVerificationManager = systemManagerVerificationManager;
		this.employerEmailApproveService = employerEmailApproveService;
	}

	@Override
	public DataResult<List<EmployerUser>> getAll() {

		return new SuccessDataResult<>(this.employerUserDao.findAll(), "???? verenler getirildi");
	}

	@Override
	public Result add(EmployerUser employerUser, String passwordAgain) {

		if (!BaseCompanyUserValidator.checkValues(employerUser).isSuccess())

			return new ErrorResult(BaseCompanyUserValidator.checkValues(employerUser).getMessage());

		if (!super.add(employerUser, passwordAgain).isSuccess())
			return new ErrorResult(super.add(employerUser, passwordAgain).getMessage());

		this.employerUserDao.save(employerUser);

		// email verify send();
		EmployerEmailApprove employerEmailApprove = new EmployerEmailApprove();
		employerEmailApprove.setUserId(employerUser.getId());
		employerEmailApprove.setVerifyCode(RandomUUIDCodeHelper.randomUuidCreate());
		employerEmailApprove.setApprovalDate(LocalDate.now());
		employerEmailApprove.setApproved(false);

		this.emailVerificationService.add(employerEmailApprove);

		// systemManagerVerificationManager.add(employerUser);

		return new SuccessResult("????veren kay??t edildi");

	}

	@Override
	public Result verifyemail(String code) {
		if (this.employerEmailApproveService.verifyemail(code).isSuccess())
			return new SuccessResult(this.employerEmailApproveService.verifyemail(code).getMessage());

		return new ErrorResult(this.employerEmailApproveService.verifyemail(code).getMessage());

	}

}
