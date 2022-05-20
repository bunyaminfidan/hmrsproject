package javaCamp.hmrs.core.utilities.verification.mernis;

import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.entites.concretes.User;

public interface MernisVerificationService {
	
	public boolean verify();
	
	Result add(User user);
	


}
