package javaCamp.hmrs.core.utilities.helpers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebsiteCheckHelper {
	

	public static boolean verify(String website) {
        Pattern valid =
                Pattern.compile("((http|https)://)(www.)?[a-zA-Z0-9@:%._\\+~#?&//=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%._\\+~#?&//=]*)", Pattern.CASE_INSENSITIVE);


        Matcher matcher = valid.matcher(website);
        return matcher.find();
		
	}

}
