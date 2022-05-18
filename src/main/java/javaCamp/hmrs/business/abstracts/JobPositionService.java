package javaCamp.hmrs.business.abstracts;

import java.util.List;

import javaCamp.hmrs.core.utilities.results.DataResult;
import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.entites.concretes.JobPosition;
import javaCamp.hmrs.entites.concretes.User;

public interface JobPositionService {

	DataResult<List<JobPosition>> getAll();
	
	Result add(JobPosition jobPosition );
	
	DataResult<JobPosition> getByName(String name);
	
}
