package javaCamp.hmrs.core.utilities.verification.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javaCamp.hmrs.core.utilities.results.DataResult;
import javaCamp.hmrs.core.utilities.results.SuccessDataResult;
import javaCamp.hmrs.dataAccess.abstracts.BaseEmailApproveDao;
import javaCamp.hmrs.dataAccess.abstracts.EmployerEmailApproveDao;
import javaCamp.hmrs.entites.concretes.BaseEmailApprove;
import javaCamp.hmrs.entites.concretes.EmployerEmailApprove;

@Service
public class EmployerEmailApproveManager extends EmailVerificationManager implements EmployerEmailApproveService {

	private EmployerEmailApproveDao employerEmailApproveDao;

	@Autowired
	public EmployerEmailApproveManager(BaseEmailApproveDao baseEmailApproveDao,
			EmployerEmailApproveDao employerEmailApproveDao) {
		super(baseEmailApproveDao);
		this.employerEmailApproveDao = employerEmailApproveDao;

	}

//	@Override
//	public DataResult<BaseEmailApprove> getApproveByVerifyCode(String verifyCode) {
//		
//		return new SuccessDataResult<BaseEmailApprove>(employerEmailApproveDao.findByEmail(verifyCode),
//				"Doğrulama koduna göre iş veren getirildi");
//	}

	@Override
	public DataResult<EmployerEmailApprove> verifyCode(EmployerEmailApprove employerEmailApprove) {
		// TODO Auto-generated method stub
		return null;
	}

}
