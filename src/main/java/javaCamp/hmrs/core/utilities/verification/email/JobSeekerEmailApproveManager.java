package javaCamp.hmrs.core.utilities.verification.email;

import org.springframework.stereotype.Service;

import javaCamp.hmrs.core.utilities.results.DataResult;
import javaCamp.hmrs.core.utilities.results.SuccessDataResult;
import javaCamp.hmrs.core.utilities.results.SuccessResult;
import javaCamp.hmrs.dataAccess.abstracts.BaseEmailApproveDao;
import javaCamp.hmrs.dataAccess.abstracts.JobSeekerEmailApproveDao;
import javaCamp.hmrs.entites.concretes.BaseEmailApprove;
import javaCamp.hmrs.entites.concretes.JobSeekerEmailApprove;

@Service
public class JobSeekerEmailApproveManager extends EmailVerificationManager implements JobSeekerEmailApproveService {

	private JobSeekerEmailApproveDao jobSeekerEmailApproveDao;

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
	public DataResult<BaseEmailApprove> getApproveByVerifyCode(String verifyCode) {

		return new SuccessDataResult<BaseEmailApprove>(jobSeekerEmailApproveDao.findByEmail(verifyCode),
				"Doğrulama koduna göre iş veren getirildi");
	}

	@Override
	public DataResult<JobSeekerEmailApprove> verifyCode(String code ) {

		if (this.getApproveByVerifyCode(jobSeekerEmailApprove.getEmail()).isSuccess()) {

			jobSeekerEmailApprove.setApproved(true);

			jobSeekerEmailApproveDao.save(jobSeekerEmailApprove);

		}
		return new SuccessDataResult<JobSeekerEmailApprove>("Başarılı");
	}
}
