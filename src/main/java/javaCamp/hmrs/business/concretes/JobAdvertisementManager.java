package javaCamp.hmrs.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javaCamp.hmrs.business.abstracts.JobAdvertisementService;
import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.core.utilities.results.SuccessResult;
import javaCamp.hmrs.dataAccess.abstracts.JobAdvertisementDao;
import javaCamp.hmrs.entites.concretes.JobAdvertisement;

@Service
public class JobAdvertisementManager implements JobAdvertisementService {

	private JobAdvertisementDao jobAdvertisementDao;

	@Autowired
	public JobAdvertisementManager(JobAdvertisementDao jobAdvertisementDao) {
		super();
		this.jobAdvertisementDao = jobAdvertisementDao;
	}

	@Override
	public Result add(JobAdvertisement jobAdvertisement) {

		this.jobAdvertisementDao.save(jobAdvertisement);
		return new SuccessResult("İş ilanı kayıt edildi");
	}

}
