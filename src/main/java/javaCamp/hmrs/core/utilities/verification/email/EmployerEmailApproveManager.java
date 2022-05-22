package javaCamp.hmrs.core.utilities.verification.email;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javaCamp.hmrs.core.utilities.results.DataResult;
import javaCamp.hmrs.core.utilities.results.ErrorDataResult;
import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.core.utilities.results.SuccessDataResult;
import javaCamp.hmrs.dataAccess.abstracts.BaseEmailApproveDao;
import javaCamp.hmrs.dataAccess.abstracts.EmployerEmailApproveDao;
import javaCamp.hmrs.entites.concretes.BaseEmailApprove;
import javaCamp.hmrs.entites.concretes.EmployerEmailApprove;
import javaCamp.hmrs.entites.concretes.JobSeekerEmailApprove;

@Service
public class EmployerEmailApproveManager extends EmailVerificationManager implements EmployerEmailApproveService {

	private EmployerEmailApproveDao employerEmailApproveDao;

	public EmployerEmailApproveManager(BaseEmailApproveDao baseEmailApproveDao,
			EmployerEmailApproveDao employerEmailApproveDao) {
		super(baseEmailApproveDao);
		this.employerEmailApproveDao = employerEmailApproveDao;
		// TODO Auto-generated constructor stub
	}

	@Override
	public DataResult<EmployerEmailApprove> getApproveByVerifyCode(String verifyCode) {
		return new SuccessDataResult<EmployerEmailApprove>(employerEmailApproveDao.findByVerifyCode(verifyCode));

	}

	@Override
	public Result verifyemail(String code) {
		if (this.getApproveByVerifyCode(code).getData() != null) {

			EmployerEmailApprove approve = this.getApproveByVerifyCode(code).getData();

			approve.setApproved(true);
			approve.setApprovalDate(LocalDate.now());

			employerEmailApproveDao.save(approve);
			return new SuccessDataResult<EmployerEmailApprove>("Email doğrolama başarılı");

		} else {
			return new ErrorDataResult<EmployerEmailApprove>(null, "Email doğrulanamadı");
		}

	}

}
