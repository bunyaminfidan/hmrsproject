package javaCamp.hmrs.core.utilities.validation;

import java.util.Date;

import javaCamp.hmrs.core.utilities.results.ErrorResult;
import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.core.utilities.results.SuccessResult;
import javaCamp.hmrs.entites.concretes.JobSeekerUser;
import javaCamp.hmrs.entites.concretes.SystemUser;

public class BaseIndividualValidator {

	public static Result checkValuesIndividualUser(String firstName, String lastName, String natioanlityId,
			Date dateOfBirth) {

		Result firstNameValid = FirstNameValidator.valid(firstName);
		Result lastNameValid = LastNameValidator.valid(lastName);
		Result nationalityIdValid = NationalityIdValidator.valid(natioanlityId);

		if (dateOfBirth == null)
			return new ErrorResult("Dogum tarihi boş olamaz");

		if (!firstNameValid.isSuccess())
			return new ErrorResult(firstNameValid.getMessage());

		if (!lastNameValid.isSuccess())
			return new ErrorResult(lastNameValid.getMessage());

		if (!nationalityIdValid.isSuccess())
			return new ErrorResult(nationalityIdValid.getMessage());

		return new SuccessResult();
	}

}
