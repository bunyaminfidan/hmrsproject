package javaCamp.hmrs.business.abstracts;

import java.util.List;

import javaCamp.hmrs.core.utilities.results.DataResult;
import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.entites.concretes.JobSeekerUser;

public interface JobSeekerUserService extends BaseIndividualUserService {
	
	
	DataResult<List<JobSeekerUser>> getAll();
	
	Result add(JobSeekerUser jobSeekerUser, String passwordAgain);
	
	DataResult<JobSeekerUser>  getByNationalityId(String nationalityId);

}
