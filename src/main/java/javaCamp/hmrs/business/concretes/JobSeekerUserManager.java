package javaCamp.hmrs.business.concretes;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javaCamp.hmrs.business.abstracts.JobSeekerUserService;
import javaCamp.hmrs.core.utilities.helpers.GetUserDetailHelper;
import javaCamp.hmrs.core.utilities.results.DataResult;
import javaCamp.hmrs.core.utilities.results.ErrorResult;
import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.core.utilities.results.SuccessDataResult;
import javaCamp.hmrs.core.utilities.results.SuccessResult;
import javaCamp.hmrs.core.utilities.validation.BaseIndividualValidator;
import javaCamp.hmrs.core.utilities.validation.FirstNameValidator;
import javaCamp.hmrs.core.utilities.validation.LastNameValidator;
import javaCamp.hmrs.core.utilities.validation.NationalityIdValidator;
import javaCamp.hmrs.core.utilities.verification.email.EmailVerificationService;
import javaCamp.hmrs.core.utilities.verification.mernis.MernisVerificationServiceAdapter;
import javaCamp.hmrs.dataAccess.abstracts.ApproveDao;
import javaCamp.hmrs.dataAccess.abstracts.JobSeekerMernisApproveDao;
import javaCamp.hmrs.dataAccess.abstracts.JobSeekerUserDao;
import javaCamp.hmrs.dataAccess.abstracts.UserDao;
import javaCamp.hmrs.entites.concretes.Approve;
import javaCamp.hmrs.entites.concretes.JobSeekerMernisApprove;
import javaCamp.hmrs.entites.concretes.JobSeekerUser;
import javaCamp.hmrs.entites.concretes.SystemUser;

@Service
public class JobSeekerUserManager extends UserManager implements JobSeekerUserService {

	private JobSeekerUserDao jobSeekerUserDao;
	private JobSeekerMernisApproveDao jobSeekerMernisApproveDao;

	@Qualifier("mernisVerificationManager")
	private MernisVerificationServiceAdapter mernisVerificationService;

	@Qualifier("emailVerificationManager")
	private EmailVerificationService emailVerificationService;

	@Autowired
	public JobSeekerUserManager(
			UserDao userDao, 
			JobSeekerUserDao jobSeekerUserDao,
			JobSeekerMernisApproveDao jobSeekerMernisApproveDao,
			@Qualifier("mernisVerificationManager") MernisVerificationServiceAdapter mernisVerificationService,
			@Qualifier("emailVerificationManager") EmailVerificationService emailVerificationService) {
		
		super(userDao);
		this.jobSeekerUserDao = jobSeekerUserDao;
		this.mernisVerificationService = mernisVerificationService;
		this.jobSeekerMernisApproveDao = jobSeekerMernisApproveDao;
		this.emailVerificationService = emailVerificationService;
	}

	JobSeekerMernisApprove jobSeekerMernisApprove = new JobSeekerMernisApprove();

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

		jobSeekerUserDao.save(jobSeekerUser);

		int getUserId = GetUserDetailHelper.getUserId(super.userDao, jobSeekerUser);

		if (getUserId != 0) {

			// mernis approve kayıt bilgileri
			jobSeekerMernisApprove.setUserId(getUserId);
			jobSeekerMernisApprove.setApprovalDate(LocalDate.now());
			jobSeekerMernisApprove.setApproved(true);

			jobSeekerMernisApproveDao.save(jobSeekerMernisApprove);
			
			//emailVerificationService.verify();
			
			
			
			
			

			return new SuccessResult("İş arayan  kayıt edildi");
		} else {
			return new ErrorResult("İş arayan kayıt edilemedi");
		}

	}

	@Override
	public DataResult<JobSeekerUser> getByNationalityId(String nationalityId) {

		return new SuccessDataResult<JobSeekerUser>(this.jobSeekerUserDao.findByNationalityIdIs(nationalityId),
				"Tc Kimlik Numarasına göre getirildi");
	}

}
