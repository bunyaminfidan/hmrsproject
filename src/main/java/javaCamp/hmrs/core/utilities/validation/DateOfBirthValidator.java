package javaCamp.hmrs.core.utilities.validation;

import java.time.LocalDate;

import javaCamp.hmrs.core.utilities.results.ErrorResult;
import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.core.utilities.results.SuccessResult;

public class DateOfBirthValidator {

	public static Result valid(LocalDate dateOfBirth) {

		if (dateOfBirth.toString() == "") {
			return new ErrorResult("Doğum tarihi boş olamaz");
		}

		return new SuccessResult();

	}

}
