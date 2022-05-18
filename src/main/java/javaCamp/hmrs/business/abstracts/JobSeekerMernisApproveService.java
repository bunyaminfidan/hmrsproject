package javaCamp.hmrs.business.abstracts;

import javaCamp.hmrs.core.utilities.results.DataResult;
import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.entites.concretes.Approve;
import javaCamp.hmrs.entites.concretes.JobSeekerMernisApprove;

public interface JobSeekerMernisApproveService extends ApproveService {
	
	Result add(JobSeekerMernisApprove jobSeekerMernisApprove );

	DataResult<Approve> getByUserId(int userId);

}
