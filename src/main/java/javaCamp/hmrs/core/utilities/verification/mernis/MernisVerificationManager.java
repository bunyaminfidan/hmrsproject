package javaCamp.hmrs.core.utilities.verification.mernis;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.core.utilities.results.SuccessResult;
import javaCamp.hmrs.dataAccess.abstracts.JobSeekerMernisApproveDao;
import javaCamp.hmrs.entites.concretes.JobSeekerMernisApprove;
import javaCamp.hmrs.entites.concretes.User;

@Service
public class MernisVerificationManager implements MernisVerificationService {
	
	private JobSeekerMernisApproveDao jobSeekerMernisApproveDao;
	JobSeekerMernisApprove jobSeekerMernisApprove = new JobSeekerMernisApprove();

	public MernisVerificationManager(JobSeekerMernisApproveDao jobSeekerMernisApproveDao) {
		super();
		this.jobSeekerMernisApproveDao = jobSeekerMernisApproveDao;
	}

	@Override
	public boolean verify() {

		return true;
	}

	@Override
	public Result add(User user) {


		jobSeekerMernisApprove.setUserId(user.getId());
		jobSeekerMernisApprove.setApprovalDate(LocalDate.now());
		jobSeekerMernisApprove.setApproved(true);
		jobSeekerMernisApproveDao.save(jobSeekerMernisApprove);
		
		return new SuccessResult("Mernis bilgileri kayıt edildi");
	}

}
