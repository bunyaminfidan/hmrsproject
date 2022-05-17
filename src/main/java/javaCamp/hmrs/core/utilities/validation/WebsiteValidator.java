package javaCamp.hmrs.core.utilities.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javaCamp.hmrs.core.utilities.results.ErrorResult;
import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.core.utilities.results.SuccessResult;

public class WebsiteValidator {

	public static Result valid(String website) {

		if (website == "") {

			return new ErrorResult("Website alanı boş olamaz");
		}

		
		//Websitesi fotmatını kontrol ediyor. "https://www.abc.com"
		Pattern valid = Pattern.compile(
				"((http|https)://)(www.)?[a-zA-Z0-9@:%._\\+~#?&//=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%._\\+~#?&//=]*)",
				Pattern.CASE_INSENSITIVE);

		Matcher matcher = valid.matcher(website);
		boolean result = matcher.find();
		if (!result) {
			return new ErrorResult("Website formatı yanlış");
		}

		return new SuccessResult();

	}

}
