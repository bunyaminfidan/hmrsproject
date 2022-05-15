package javaCamp.hmrs.core.utilities.helpers;

public class EmailIsWebsiteDomainCheckHelper {

	public static boolean verify(String email, String website) {

		String[] splitMail = email.split("@", 2);

		if (website.contains(splitMail[1])) {
			return true;
		} else {
			return false;
		}

	}

}
