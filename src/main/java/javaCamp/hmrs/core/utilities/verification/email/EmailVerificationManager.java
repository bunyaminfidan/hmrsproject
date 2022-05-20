package javaCamp.hmrs.core.utilities.verification.email;

import java.time.LocalDate;


import org.springframework.stereotype.Service;
import javaCamp.hmrs.core.utilities.helpers.RandomUUIDCodeHelper;
import javaCamp.hmrs.core.utilities.results.DataResult;
import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.core.utilities.results.SuccessResult;
import javaCamp.hmrs.dataAccess.abstracts.BaseEmailApproveDao;
import javaCamp.hmrs.entites.concretes.BaseEmailApprove;
import javaCamp.hmrs.entites.concretes.JobSeekerEmailApprove;
import javaCamp.hmrs.entites.concretes.User;

@Service
public class EmailVerificationManager implements EmailVerificationService {

	JobSeekerEmailApprove jobSeekerEmailApprove = new JobSeekerEmailApprove();

	private BaseEmailApproveDao baseEmailApproveDao;

	public EmailVerificationManager(BaseEmailApproveDao baseEmailApproveDao) {
		super();
		this.baseEmailApproveDao = baseEmailApproveDao;
	}

	@Override
	public Result add(User user) {

		jobSeekerEmailApprove.setUserId(user.getId());
		jobSeekerEmailApprove.setEmail(RandomUUIDCodeHelper.randomUuidCreate());
		jobSeekerEmailApprove.setApprovalDate(LocalDate.now());
		jobSeekerEmailApprove.setApproved(false);

		baseEmailApproveDao.save(jobSeekerEmailApprove);
		return new SuccessResult("Doğrulama epostası gönderildi");
	}





	@Override
	public DataResult<BaseEmailApprove>  getApproveByVerifyCode(String verifyCode) {
		// TODO Auto-generated method stub
		return null;
	}

}
