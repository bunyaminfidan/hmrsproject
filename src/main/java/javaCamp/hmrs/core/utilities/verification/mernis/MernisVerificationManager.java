package javaCamp.hmrs.core.utilities.verification.mernis;

import org.springframework.stereotype.Service;

import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.entites.concretes.User;

@Service
public class MernisVerificationManager implements MernisVerificationServiceAdapter {

	@Override
	public boolean verify() {

		return true;
	}

}
