package javaCamp.hmrs.core.utilities.helpers;

import javaCamp.hmrs.dataAccess.abstracts.JobPositionDao;
import javaCamp.hmrs.entites.concretes.JobPosition;

public class GetJobPositionHelper {
	
	public static boolean isJobPositionName(JobPositionDao jobPositionDao, String positionName) {

		JobPosition jobPosition  = jobPositionDao.findByName(positionName);

		if (jobPosition != null) {

			return true;
		}

		else {
			return false;
		}

	}

}
