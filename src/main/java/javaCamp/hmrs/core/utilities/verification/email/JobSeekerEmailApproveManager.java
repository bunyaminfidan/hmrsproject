package javaCamp.hmrs.core.utilities.verification.email;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javaCamp.hmrs.core.utilities.results.DataResult;
import javaCamp.hmrs.core.utilities.results.ErrorDataResult;
import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.core.utilities.results.SuccessDataResult;
import javaCamp.hmrs.core.utilities.results.SuccessResult;
import javaCamp.hmrs.dataAccess.abstracts.BaseEmailApproveDao;
import javaCamp.hmrs.dataAccess.abstracts.JobSeekerEmailApproveDao;
import javaCamp.hmrs.entites.concretes.BaseEmailApprove;
import javaCamp.hmrs.entites.concretes.EmployerEmailApprove;
import javaCamp.hmrs.entites.concretes.JobSeekerEmailApprove;
import javaCamp.hmrs.entites.concretes.User;

@Service
public class JobSeekerEmailApproveManager extends EmailVerificationManager implements JobSeekerEmailApproveService {

	private JobSeekerEmailApproveDao jobSeekerEmailApproveDao;

	@Autowired
	public JobSeekerEmailApproveManager(BaseEmailApproveDao baseEmailApproveDao,
			JobSeekerEmailApproveDao jobSeekerEmailApproveDao) {
		super(baseEmailApproveDao);
		this.jobSeekerEmailApproveDao = jobSeekerEmailApproveDao;
	}

	public JobSeekerEmailApproveManager(BaseEmailApproveDao baseEmailApproveDao) {
		super(baseEmailApproveDao);
		// TODO Auto-generated constructor stub
	}

	@Override
	public DataResult<JobSeekerEmailApprove> getApproveByVerifyCode(String verifyCode) {

		return new SuccessDataResult<JobSeekerEmailApprove>(jobSeekerEmailApproveDao.findByVerifyCode(verifyCode));
	}

	@Override
	public Result verifyemail(String code) {

		if (this.getApproveByVerifyCode(code).getData() != null) {

			JobSeekerEmailApprove approve = this.getApproveByVerifyCode(code).getData();

			approve.setApproved(true);
			approve.setApprovalDate(LocalDate.now());

			jobSeekerEmailApproveDao.save(approve);
			return new SuccessDataResult<JobSeekerEmailApprove>("Email doğrolama başarılı");

		} else {
			return new ErrorDataResult<EmployerEmailApprove>(null, "Email doğrulanamadı");
		}

	}
}
