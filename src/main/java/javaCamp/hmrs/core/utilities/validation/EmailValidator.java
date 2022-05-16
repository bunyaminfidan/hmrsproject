package javaCamp.hmrs.core.utilities.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javaCamp.hmrs.core.utilities.results.ErrorResult;
import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.core.utilities.results.SuccessResult;

public class EmailValidator {

	public static Result valid(String email) {

		if (email == "") {

			return new ErrorResult("Email boş olamaz");
		}

		Pattern validEmailAdressRegex = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
				Pattern.CASE_INSENSITIVE);

		Matcher matcher = validEmailAdressRegex.matcher(email);

		boolean result = matcher.find();
		if (!result) {
			return new ErrorResult("Girilen email yanlış formattadır");
		}

		return new SuccessResult();

	}

}
