package javaCamp.hmrs.core.utilities.validation;

import javaCamp.hmrs.core.utilities.results.ErrorResult;
import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.core.utilities.results.SuccessResult;

public class NationalityIdValidator {

	public static Result valid(String nationalityId) {

		if (nationalityId == "") {

			return new ErrorResult("Tc no boş olamaz");
		} else if (nationalityId.length() >= 12) {

			return new ErrorResult("Tc no 11 haneden büyük olamaz");
		} else if (nationalityId.length() <= 10) {

			return new ErrorResult("Tc no 11 haneden küçük olamaz");
		}

		return new SuccessResult();

	}

}
