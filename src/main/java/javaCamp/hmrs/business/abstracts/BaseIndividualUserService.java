package javaCamp.hmrs.business.abstracts;

import java.util.List;

import javaCamp.hmrs.core.utilities.results.DataResult;
import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.entites.concretes.BaseIndividualUser;

public interface BaseIndividualUserService {

	Result add(BaseIndividualUser baseIndividualUser, String passwordAgain);
	
	Result verifyemail(String code );
	
}
