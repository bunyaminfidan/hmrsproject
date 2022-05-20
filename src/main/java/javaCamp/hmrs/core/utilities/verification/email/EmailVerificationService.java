package javaCamp.hmrs.core.utilities.verification.email;

import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.entites.concretes.User;

public interface EmailVerificationService {
	
	public boolean verify();
	
	Result sendEmail(User user);

}
