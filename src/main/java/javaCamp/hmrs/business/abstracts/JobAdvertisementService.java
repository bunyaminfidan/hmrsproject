package javaCamp.hmrs.business.abstracts;

import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.entites.concretes.JobAdvertisement;

public interface JobAdvertisementService {
	
	Result add(JobAdvertisement jobAdvertisement);

}
