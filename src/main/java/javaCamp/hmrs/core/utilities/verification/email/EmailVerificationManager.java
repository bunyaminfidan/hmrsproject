package javaCamp.hmrs.core.utilities.verification.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.core.utilities.results.SuccessResult;
import javaCamp.hmrs.dataAccess.abstracts.ApproveDao;
import javaCamp.hmrs.entites.concretes.User;


@Service
public class EmailVerificationManager implements EmailVerificationService {
	
	
	
	
	

	

	@Override
	public boolean verify() {

		return true;
	}

	@Override
	public Result sendEmail(User user) {
		return null;
	}

}
