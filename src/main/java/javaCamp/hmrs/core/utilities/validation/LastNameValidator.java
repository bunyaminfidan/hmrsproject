package javaCamp.hmrs.core.utilities.validation;

import javaCamp.hmrs.core.utilities.results.ErrorResult;
import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.core.utilities.results.SuccessResult;

public class LastNameValidator {
	
	public static Result valid (String lastName) {

		if (lastName == "") {

			return new ErrorResult("Soyisim boş olamaz");
		}

		return new SuccessResult();

	}

}
