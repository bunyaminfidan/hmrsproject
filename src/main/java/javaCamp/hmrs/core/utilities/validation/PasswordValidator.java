package javaCamp.hmrs.core.utilities.validation;

import javaCamp.hmrs.core.utilities.results.ErrorResult;
import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.core.utilities.results.SuccessResult;

public class PasswordValidator {

	public static Result valid(String password1, String password2) {

		// Girilen şifreleri kontrol eder

		if (password1 == "") {

			return new ErrorResult("Şifre boş olamaz");
		}

		if (password2 == null)
			return new ErrorResult("Şifre tekrarı boş olamaz");

		if (!password1.equals(password2))
			return new ErrorResult("Girilen şifreler aynı değil");

		return new SuccessResult();

	}

}
