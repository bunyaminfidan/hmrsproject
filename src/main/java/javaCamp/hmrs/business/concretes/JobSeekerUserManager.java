package javaCamp.hmrs.business.concretes;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javaCamp.hmrs.business.abstracts.JobSeekerUserService;
import javaCamp.hmrs.core.utilities.helpers.GetUserDetailHelper;
import javaCamp.hmrs.core.utilities.helpers.RandomUUIDCodeHelper;
import javaCamp.hmrs.core.utilities.results.DataResult;
import javaCamp.hmrs.core.utilities.results.ErrorResult;
import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.core.utilities.results.SuccessDataResult;
import javaCamp.hmrs.core.utilities.results.SuccessResult;
import javaCamp.hmrs.core.utilities.validation.BaseIndividualValidator;
import javaCamp.hmrs.core.utilities.verification.email.EmailVerificationService;
import javaCamp.hmrs.core.utilities.verification.mernis.MernisVerificationService;
import javaCamp.hmrs.dataAccess.abstracts.BaseEmailApproveDao;
import javaCamp.hmrs.dataAccess.abstracts.BaseIndividualUserDao;
import javaCamp.hmrs.dataAccess.abstracts.JobSeekerMernisApproveDao;
import javaCamp.hmrs.dataAccess.abstracts.JobSeekerUserDao;
import javaCamp.hmrs.dataAccess.abstracts.UserDao;
import javaCamp.hmrs.entites.concretes.BaseIndividualUser;
import javaCamp.hmrs.entites.concretes.JobSeekerEmailApprove;
import javaCamp.hmrs.entites.concretes.JobSeekerUser;
import javaCamp.hmrs.entites.concretes.SystemUser;

@Service
public class JobSeekerUserManager extends BaseIndividualUserManager implements JobSeekerUserService {

	JobSeekerEmailApprove jobSeekerEmailApprove = new JobSeekerEmailApprove();
	private JobSeekerUserDao jobSeekerUserDao;

	@Qualifier("emailVerificationManager")
	private EmailVerificationService emailVerificationService;

	@Qualifier("mernisVerificationManager")
	private MernisVerificationService mernisVerificationService;

	public JobSeekerUserManager(UserDao userDao, BaseIndividualUserDao baseIndividualUserDao,
			JobSeekerUserDao jobSeekerUserDao,
			@Qualifier("emailVerificationManager") EmailVerificationService emailVerificationService,
			@Qualifier("mernisVerificationManager") MernisVerificationService mernisVerificationService) {
		super(userDao, baseIndividualUserDao, mernisVerificationService);
		this.jobSeekerUserDao = jobSeekerUserDao;
		this.emailVerificationService = emailVerificationService;
		this.mernisVerificationService = mernisVerificationService;

	}

	@Override
	public Result add(JobSeekerUser jobSeekerUser, String passwordAgain) {
		
		if (GetUserDetailHelper.getJobSeekerUserByNationalityId(jobSeekerUserDao,
				jobSeekerUser.getNationalityId()))
			return new ErrorResult("Tc Kimlik Numarası sistemde kayıtlı");

		
		if (!super.add(jobSeekerUser, passwordAgain).isSuccess()) 
			return new ErrorResult(super.add(jobSeekerUser, passwordAgain).getMessage());
		
		
		this.jobSeekerUserDao.save(jobSeekerUser);
		
		
		// mernis approve kayıt bilgileri
		mernisVerificationService.add(jobSeekerUser);

		// email verify send();

		jobSeekerEmailApprove.setUserId(jobSeekerUser.getId());
		jobSeekerEmailApprove.setVerifyCode(RandomUUIDCodeHelper.randomUuidCreate());
		jobSeekerEmailApprove.setApprovalDate(LocalDate.now());
		jobSeekerEmailApprove.setApproved(false);

		this.emailVerificationService.add(jobSeekerEmailApprove);
		
		return new SuccessResult("İş arayan  kayıt edildi");
	}


	@Override
	public DataResult<List<JobSeekerUser>> getAll() {
		return new SuccessDataResult<>(this.jobSeekerUserDao.findAll(), "Sistem personelleri getirildi");
	}

	@Override
	public DataResult<JobSeekerUser> getByNationalityId(String nationalityId) {
		return new SuccessDataResult<JobSeekerUser>(this.jobSeekerUserDao.findByNationalityIdIs(nationalityId),
				"Tc Kimlik Numarasına göre getirildi");
	}




}
