package javaCamp.hmrs.core.utilities.helpers;

import javaCamp.hmrs.dataAccess.abstracts.UserDao;
import javaCamp.hmrs.entites.concretes.User;

public class IsEmailRegistered {

	public static boolean userEmailCheck(String email, UserDao userDao) {

		User getUser = userDao.findByEmail(email);

		if (getUser != null) {
			return true;
		} else {
			return false;
		}

	}

}
