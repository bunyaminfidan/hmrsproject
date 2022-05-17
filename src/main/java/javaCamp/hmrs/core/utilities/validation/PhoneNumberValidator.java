package javaCamp.hmrs.core.utilities.validation;

import javaCamp.hmrs.core.utilities.results.ErrorResult;
import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.core.utilities.results.SuccessResult;

public class PhoneNumberValidator {
	public static Result valid (String phoneNumber) {

		if (phoneNumber == "") {

			return new ErrorResult("Telefon numarası boş olamaz");
		}

		return new SuccessResult();

	}

}
