package javaCamp.hmrs.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javaCamp.hmrs.business.abstracts.JobSeekerMernisApproveService;
import javaCamp.hmrs.core.utilities.results.DataResult;
import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.dataAccess.abstracts.JobSeekerMernisApproveDao;
import javaCamp.hmrs.entites.concretes.Approve;
import javaCamp.hmrs.entites.concretes.JobSeekerMernisApprove;

@Service
public class JobSeekerMernisApproveManager implements JobSeekerMernisApproveService{

	private JobSeekerMernisApproveDao jobSeekerMernisApproveDao;
	
	@Autowired
	public JobSeekerMernisApproveManager(JobSeekerMernisApproveDao jobSeekerMernisApproveDao) {
		super();
		this.jobSeekerMernisApproveDao = jobSeekerMernisApproveDao;
	}


	@Override
	public Result add(Approve approve) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public DataResult<Approve> getByUserId(int userId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Result add(JobSeekerMernisApprove jobSeekerMernisApprove) {
		// TODO Auto-generated method stub
		return null;
	}



}
