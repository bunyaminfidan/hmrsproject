package javaCamp.hmrs.business.abstracts;

import java.util.List;

import javaCamp.hmrs.entites.concretes.JobPosition;

public interface JobPositionService {

	List<JobPosition> getAll();
	
}
