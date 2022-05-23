package javaCamp.hmrs.business.abstracts;

import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.entites.concretes.BaseIndividualUser;

public interface BaseIndividualUserService extends UserService {

	Result add(BaseIndividualUser baseIndividualUser, String passwordAgain);

	Result verifyemail(String code);

}
