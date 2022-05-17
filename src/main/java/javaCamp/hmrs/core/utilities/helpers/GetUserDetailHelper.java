package javaCamp.hmrs.core.utilities.helpers;

import javaCamp.hmrs.dataAccess.abstracts.UserDao;
import javaCamp.hmrs.entites.concretes.User;

public class GetUserDetailHelper {

	public static int getUserId(UserDao userDao, User user) {

		User getUser = userDao.findByEmail(user.getEmail());

		if (getUser != null) {

			return getUser.getId();
		}

		else {
			return 0;
		}

	}

}
