package javaCamp.hmrs.core.utilities.validation;

import javaCamp.hmrs.core.utilities.results.ErrorResult;
import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.core.utilities.results.SuccessResult;

public class EmailIsWebsiteDomainValidator {


	public static Result valid(String email, String website) {

		String[] splitMail = email.split("@", 2);

		if (!website.contains(splitMail[1])) {
			return new ErrorResult("Email, şirket web sitesi domaininde olmalıdır.");
		}
		return new SuccessResult();

	}

}
