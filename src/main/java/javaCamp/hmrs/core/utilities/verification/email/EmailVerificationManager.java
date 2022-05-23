package javaCamp.hmrs.core.utilities.verification.email;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javaCamp.hmrs.core.utilities.helpers.RandomUUIDCodeHelper;
import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.core.utilities.results.SuccessResult;
import javaCamp.hmrs.dataAccess.abstracts.BaseEmailApproveDao;
import javaCamp.hmrs.entites.concretes.BaseEmailApprove;


@Service
public class EmailVerificationManager implements EmailVerificationService {



	private BaseEmailApproveDao baseEmailApproveDao;

	@Autowired
	public EmailVerificationManager(BaseEmailApproveDao baseEmailApproveDao) {
		super();
		this.baseEmailApproveDao = baseEmailApproveDao;
	}

	@Override
	public Result add(BaseEmailApprove baseEmailApprove ) {

		//baseEmailApprove.setUserId(baseEmailApprove.getUserId());
		baseEmailApprove.setVerifyCode(RandomUUIDCodeHelper.randomUuidCreate());
		baseEmailApprove.setApprovalDate(LocalDate.now());
		baseEmailApprove.setApproved(false);

		baseEmailApproveDao.save(baseEmailApprove);
		return new SuccessResult("Doğrulama epostası gönderildi");
	}

	@Override
	public Result verifyemail(String code) {
		// TODO Auto-generated method stub
		return null;
	}







}
