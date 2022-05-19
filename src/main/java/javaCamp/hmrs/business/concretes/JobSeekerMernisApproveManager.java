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





}
