package javaCamp.hmrs.business.concretes;

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
import javaCamp.hmrs.core.utilities.verification.mernis.MernisVerificationService;
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
	private ApproveDao approveDao;
	private JobSeekerMernisApproveDao jobSeekerMernisApproveDao;

	@Qualifier("mernisVerificationManager")
	private MernisVerificationService mernisVerificationService;

	@Autowired
	public JobSeekerUserManager(UserDao userDao, JobSeekerUserDao jobSeekerUserDao,
			@Qualifier("mernisVerificationManager") MernisVerificationService mernisVerificationService,
			ApproveDao approveDao, JobSeekerMernisApproveDao jobSeekerMernisApproveDao) {
		super(userDao);
		this.jobSeekerUserDao = jobSeekerUserDao;
		this.mernisVerificationService = mernisVerificationService;
		this.jobSeekerMernisApproveDao = jobSeekerMernisApproveDao;
		this.approveDao = approveDao;
	}

	JobSeekerMernisApprove approve = new JobSeekerMernisApprove();
	Date approveDate;

	@Override
	public DataResult<List<JobSeekerUser>> getAll() {
		return new SuccessDataResult<>(this.jobSeekerUserDao.findAll(), "İş arayanlar getirildi");

	}

	@Override
	public Result add(JobSeekerUser jobSeekerUser, String passwordAgain) {

		if (!BaseIndividualValidator.checkValuesIndividualUser(jobSeekerUser.getFirstName(),
				jobSeekerUser.getLastName(), jobSeekerUser.getNationalityId(), jobSeekerUser.getDateOfBirth())
				.isSuccess())
			return new ErrorResult(checkValues(jobSeekerUser, passwordAgain).getMessage());

		// Tc kimlik no kayıtlı mı sorgusu buraya gelecek.
		if (GetUserDetailHelper.getJobSeekerUserByNationalityId(jobSeekerUserDao, jobSeekerUser.getNationalityId()))
			return new ErrorResult("Tc Kimlik Numarası sistemde kayıtlı");

		// Mernis Doğrulaması yapıyor.
		if (mernisVerificationService.verify())
			return new ErrorResult("Kullanıcı bilgileri mernis ile doğrulanamadı");

		// approve.setApproved(true);

		if (!super.add(jobSeekerUser, passwordAgain).isSuccess())
			return new ErrorResult(super.add(jobSeekerUser, passwordAgain).getMessage());

		jobSeekerUserDao.save(jobSeekerUser);

		int getUserId = GetUserDetailHelper.getUserId(super.userDao, jobSeekerUser);

		if (getUserId != 0) {

			// mernis approve kayıt bilgileri
			approve.setUserId(getUserId);
			approve.setApprovalDate(new Date(2022, 12, 12));

			jobSeekerMernisApproveDao.save(approve);

			return new SuccessResult("Sistem personeli kayıt edildi");
		} else {
			return new ErrorResult("Sistem personeli kayıt edilemedi");
		}

	}

	@Override
	public DataResult<JobSeekerUser> getByNationalityId(String nationalityId) {

		return new SuccessDataResult<JobSeekerUser>(this.jobSeekerUserDao.findByNationalityIdIs(nationalityId),
				"Tc Kimlik Numarasına göre getirildi");
	}

}
