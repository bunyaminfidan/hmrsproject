package javaCamp.hmrs.core.utilities.verification.email;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.core.utilities.results.SuccessResult;


@Service
@Component
@Qualifier("EmailVerificationManager")
public class EmailVerificationManager implements EmailVerificationService {

	@Override
	public Result verify() {

		return new SuccessResult();
	}

}
