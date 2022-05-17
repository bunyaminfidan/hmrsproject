package javaCamp.hmrs.core.utilities.validation;

import java.sql.Date;
import java.time.LocalDateTime;

import javaCamp.hmrs.core.utilities.results.ErrorResult;
import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.core.utilities.results.SuccessResult;

public class DateOfBirthValidator {
	
	public static Result valid (Date dateOfBirth) {
		
		if (dateOfBirth.toString() == "") {
			return new ErrorResult("Doğum tarihi boş olamaz");
		}

		return new SuccessResult();

	}

}
