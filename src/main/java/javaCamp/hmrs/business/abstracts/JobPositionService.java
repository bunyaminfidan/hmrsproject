package javaCamp.hmrs.business.abstracts;

import java.util.List;

import javaCamp.hmrs.core.utilities.results.DataResult;
import javaCamp.hmrs.entites.concretes.JobPosition;

public interface JobPositionService {

	DataResult<List<JobPosition>> getAll();
	
}
