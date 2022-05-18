package javaCamp.hmrs.core.utilities.validation;

import javaCamp.hmrs.core.utilities.results.ErrorResult;
import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.core.utilities.results.SuccessResult;
import javaCamp.hmrs.entites.concretes.JobSeekerUser;
import javaCamp.hmrs.entites.concretes.SystemUser;

public class BaseIndividualValidator {

	public static Result checkValuesSystemUser(SystemUser systemUser) {

		Result firstNameValid = FirstNameValidator.valid(systemUser.getFirstName());
		Result lastNameValid = LastNameValidator.valid(systemUser.getLastName());
		Result nationalityIdValid = NationalityIdValidator.valid(systemUser.getNationalityId());

		if (systemUser.getDateOfBirth() == null)
			return new ErrorResult("Dogum tarihi boş olamaz");

		if (!firstNameValid.isSuccess())
			return new ErrorResult(firstNameValid.getMessage());

		if (!lastNameValid.isSuccess())
			return new ErrorResult(lastNameValid.getMessage());

		if (!nationalityIdValid.isSuccess())
			return new ErrorResult(nationalityIdValid.getMessage());

		return new SuccessResult();
	}

	public static Result checkValuesJobSeekerUser(JobSeekerUser jobSeekerUser) {

		Result firstNameValid = FirstNameValidator.valid(jobSeekerUser.getFirstName());
		Result lastNameValid = LastNameValidator.valid(jobSeekerUser.getLastName());
		Result nationalityIdValid = NationalityIdValidator.valid(jobSeekerUser.getNationalityId());

		if (jobSeekerUser.getDateOfBirth() == null)
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
