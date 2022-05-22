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
import javaCamp.hmrs.dataAccess.abstracts.JobSeekerMernisApproveDao;
import javaCamp.hmrs.dataAccess.abstracts.JobSeekerUserDao;
import javaCamp.hmrs.dataAccess.abstracts.UserDao;
import javaCamp.hmrs.entites.concretes.JobSeekerEmailApprove;
import javaCamp.hmrs.entites.concretes.JobSeekerUser;

@Service
public class JobSeekerUserManager extends UserManager implements JobSeekerUserService {

	private JobSeekerUserDao jobSeekerUserDao;

	@Qualifier("mernisVerificationManager")
	private MernisVerificationService mernisVerificationService;

	@Qualifier("emailVerificationManager")
	private EmailVerificationService emailVerificationService;

	@Autowired
	public JobSeekerUserManager(UserDao userDao, JobSeekerUserDao jobSeekerUserDao,
			JobSeekerMernisApproveDao jobSeekerMernisApproveDao, BaseEmailApproveDao baseEmailApproveDao,

			@Qualifier("emailVerificationManager") EmailVerificationService emailVerificationService,
			@Qualifier("mernisVerificationManager") MernisVerificationService mernisVerificationService) {

		super(userDao);
		this.jobSeekerUserDao = jobSeekerUserDao;
		this.mernisVerificationService = mernisVerificationService;
		this.emailVerificationService = emailVerificationService;

	}

	@Override
	public DataResult<List<JobSeekerUser>> getAll() {
		return new SuccessDataResult<>(this.jobSeekerUserDao.findAll(), "İş arayanlar getirildi");

	}

	@Override
	public Result add(JobSeekerUser jobSeekerUser, String passwordAgain) {

		if (!BaseIndividualValidator.checkValuesIndividualUser(jobSeekerUser.getFirstName(),
				jobSeekerUser.getLastName(), jobSeekerUser.getNationalityId(), jobSeekerUser.getDateOfBirth())
				.isSuccess())
			return new ErrorResult(BaseIndividualValidator.checkValuesIndividualUser(jobSeekerUser.getFirstName(),
					jobSeekerUser.getLastName(), jobSeekerUser.getNationalityId(), jobSeekerUser.getDateOfBirth())
					.getMessage());

		// Tc kimlik no kayıtlı mı sorgusu buraya gelecek.
		if (GetUserDetailHelper.getJobSeekerUserByNationalityId(jobSeekerUserDao, jobSeekerUser.getNationalityId()))
			return new ErrorResult("Tc Kimlik Numarası sistemde kayıtlı");

		// Mernis Doğrulaması yapıyor.
		if (!mernisVerificationService.verify())
			return new ErrorResult("Kullanıcı bilgileri mernis ile doğrulanamadı");

		// approve.setApproved(true);

		if (!super.add(jobSeekerUser, passwordAgain).isSuccess())
			return new ErrorResult(super.add(jobSeekerUser, passwordAgain).getMessage());

		// kayıt
		jobSeekerUserDao.save(jobSeekerUser);

		// mernis approve kayıt bilgileri
		mernisVerificationService.add(jobSeekerUser);

		// email verify send();
		JobSeekerEmailApprove jobSeekerEmailApprove = new JobSeekerEmailApprove();
		jobSeekerEmailApprove.setUserId(jobSeekerUser.getId());
		jobSeekerEmailApprove.setVerifyCode(RandomUUIDCodeHelper.randomUuidCreate());
		jobSeekerEmailApprove.setApprovalDate(LocalDate.now());
		jobSeekerEmailApprove.setApproved(false);

		this.emailVerificationService.add(jobSeekerEmailApprove);

		return new SuccessResult("İş arayan  kayıt edildi");

	}

	@Override
	public DataResult<JobSeekerUser> getByNationalityId(String nationalityId) {

		return new SuccessDataResult<JobSeekerUser>(this.jobSeekerUserDao.findByNationalityIdIs(nationalityId),
				"Tc Kimlik Numarasına göre getirildi");
	}

}
