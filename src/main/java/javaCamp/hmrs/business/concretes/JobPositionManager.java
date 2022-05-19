package javaCamp.hmrs.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javaCamp.hmrs.business.abstracts.JobPositionService;
import javaCamp.hmrs.core.utilities.helpers.GetJobPositionHelper;
import javaCamp.hmrs.core.utilities.helpers.GetUserDetailHelper;
import javaCamp.hmrs.core.utilities.results.DataResult;
import javaCamp.hmrs.core.utilities.results.ErrorResult;
import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.core.utilities.results.SuccessDataResult;
import javaCamp.hmrs.core.utilities.results.SuccessResult;
import javaCamp.hmrs.core.utilities.validation.FirstNameValidator;
import javaCamp.hmrs.core.utilities.validation.JobPositionValidator;
import javaCamp.hmrs.dataAccess.abstracts.JobPositionDao;
import javaCamp.hmrs.entites.concretes.JobPosition;
import javaCamp.hmrs.entites.concretes.User;

@Service
public class JobPositionManager implements JobPositionService {

	private JobPositionDao jobPositionDao;

	@Autowired
	public JobPositionManager(JobPositionDao jobPositionDao) {
		super();
		this.jobPositionDao = jobPositionDao;
	}

	@Override
	public Result add(JobPosition jobPosition) {

		Result jobPositionValid = JobPositionValidator.valid(jobPosition.getName());
		if (!jobPositionValid.isSuccess())
			return new ErrorResult(jobPositionValid.getMessage());

		if (GetJobPositionHelper.isJobPositionName(jobPositionDao, jobPosition.getName())) {
			return new ErrorResult("İş pozisyonu sistemde kayıtlı");
		} else {

			this.jobPositionDao.save(jobPosition);
			return new SuccessResult("İş Pozisyonu kayıt edildi");

		}

	}

	@Override
	public DataResult<List<JobPosition>> getAll() {
		// TODO Auto-generated method stub
		return new SuccessDataResult<List<JobPosition>>(this.jobPositionDao.findAll(), "İş pozisyonları listelendi");
	}

	@Override
	public DataResult<JobPosition> getByName(String name) {
		return new SuccessDataResult<JobPosition>(this.jobPositionDao.findByName(name));
	}

}
