package javaCamp.hmrs.core.utilities.validation;

import javaCamp.hmrs.core.utilities.results.ErrorResult;
import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.core.utilities.results.SuccessResult;

public class FirstNameValidator {
	
	public static Result valid (String firstName) {

		if (firstName == "") {

			return new ErrorResult("İsim boş olamaz");
		}

		return new SuccessResult();

	}

}
