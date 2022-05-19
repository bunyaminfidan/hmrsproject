package javaCamp.hmrs.core.utilities.validation;

import javaCamp.hmrs.core.utilities.results.ErrorResult;
import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.core.utilities.results.SuccessResult;
import javaCamp.hmrs.entites.concretes.EmployerUser;

public class BaseCompanyUserValidator {

	public static Result checkValues(EmployerUser employerUser) {

		Result companyNameValid = CompanyNameValidator.valid(employerUser.getCompanyName());
		Result phoneNumberValid = PhoneNumberValidator.valid(employerUser.getPhoneNumber());
		Result websiteValid = WebsiteValidator.valid(employerUser.getWebsite());
		Result emailIsWebsiteDomain = EmailIsWebsiteDomainValidator.valid(employerUser.getEmail(),
				employerUser.getWebsite());

		if (!companyNameValid.isSuccess())
			return new ErrorResult(companyNameValid.getMessage());

		if (!phoneNumberValid.isSuccess())
			return new ErrorResult(phoneNumberValid.getMessage());

		if (!websiteValid.isSuccess())
			return new ErrorResult(websiteValid.getMessage());

		if (!emailIsWebsiteDomain.isSuccess())
			return new ErrorResult(emailIsWebsiteDomain.getMessage());

		return new SuccessResult();
	}

}
