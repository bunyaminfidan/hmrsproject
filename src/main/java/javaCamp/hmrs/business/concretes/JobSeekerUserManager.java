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

@Service
public class JobSeekerUserManager extends BaseIndividualUserManager implements JobSeekerUserService {

	private JobSeekerUserDao jobSeekerUserDao;
	
	@Qualifier("emailVerificationManager")
	private EmailVerificationService emailVerificationService;
	
	
	public JobSeekerUserManager(UserDao userDao, BaseIndividualUserDao baseIndividualUserDao,
			MernisVerificationService mernisVerificationService,
			JobSeekerUserDao jobSeekerUserDao,
			@Qualifier("emailVerificationManager")	EmailVerificationService emailVerificationService) {
		super(userDao, baseIndividualUserDao, mernisVerificationService);
		this.jobSeekerUserDao = jobSeekerUserDao;
		this.emailVerificationService = emailVerificationService;

	}

	@Override
	public Result add(BaseIndividualUser baseIndividualUser, String passwordAgain) {
		
	
		if (GetUserDetailHelper.getJobSeekerUserByNationalityId(jobSeekerUserDao, baseIndividualUser.getNationalityId()))
			return new ErrorResult("Tc Kimlik Numarası sistemde kayıtlı");
		
		
		//mernisVerificationService.add(jobSeekerUser);
		
		
		
		
		 super.add(baseIndividualUser, passwordAgain);
		 
		 
		 
		 

		
	}
	
	
	@Override
	public DataResult<List<JobSeekerUser>> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataResult<JobSeekerUser> getByNationalityId(String nationalityId) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
