package javaCamp.hmrs.core.utilities.validation;

import javaCamp.hmrs.core.utilities.results.ErrorResult;
import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.core.utilities.results.SuccessResult;

public class CompanyNameValidator {

	public static Result valid (String companyName) {

		if (companyName == "") {

			return new ErrorResult("Şirket adı boş olamaz");
		}

		return new SuccessResult();

	}

}
