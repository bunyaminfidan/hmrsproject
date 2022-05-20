package javaCamp.hmrs.core.utilities.helpers;

import java.util.UUID;

public class RandomUUIDCodeHelper {
	
	public static String randomUuidCreate() {
		
		UUID verifyCode = UUID.randomUUID();
		
		return verifyCode.toString();
		
	}

}
