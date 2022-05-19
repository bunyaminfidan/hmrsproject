package javaCamp.hmrs.core.utilities.validation;

import javaCamp.hmrs.core.utilities.results.ErrorResult;
import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.core.utilities.results.SuccessResult;

public class JobPositionValidator {

	public static Result valid(String jobPosition) {

		if (jobPosition == "") {

			return new ErrorResult("İş pozisyonu boş olamaz");
		}

		return new SuccessResult();

	}

}
