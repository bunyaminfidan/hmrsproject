package javaCamp.hmrs.core.utilities.validation;

import java.time.LocalDate;
import java.util.Date;

import javaCamp.hmrs.core.utilities.results.ErrorResult;
import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.core.utilities.results.SuccessResult;
import javaCamp.hmrs.entites.concretes.JobSeekerUser;
import javaCamp.hmrs.entites.concretes.SystemUser;
import javaCamp.hmrs.entites.concretes.User;
import net.bytebuddy.asm.Advice.Local;

public class BaseIndividualValidator {
	
	public static Result checkValuesUser(User user, String passwordAgain) {

		Result emailVaid = EmailValidator.valid(user.getEmail());
		Result passwordValid = PasswordValidator.valid(user.getPassword(), passwordAgain);

		if (!emailVaid.isSuccess())
			return new ErrorResult(emailVaid.getMessage());

		if (!passwordValid.isSuccess())
			return new ErrorResult(passwordValid.getMessage());

		return new SuccessResult();
	}

	public static Result checkValuesIndividualUser(String firstName, String lastName, String natioanlityId,
			LocalDate dateOfBirth) {

		Result firstNameValid = FirstNameValidator.valid(firstName);
		Result lastNameValid = LastNameValidator.valid(lastName);
		Result nationalityIdValid = NationalityIdValidator.valid(natioanlityId);
		Result dateOfBirthValid = DateOfBirthValidator.valid(dateOfBirth);

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
