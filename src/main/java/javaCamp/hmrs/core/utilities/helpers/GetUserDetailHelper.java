package javaCamp.hmrs.core.utilities.helpers;

import javaCamp.hmrs.dataAccess.abstracts.UserDao;
import javaCamp.hmrs.entites.concretes.User;

public class GetUserDetailHelper {

	public static int getUserId(UserDao userDao, User user) {

		int id;

		User getUser = userDao.findByEmail(user.getEmail());

		if (getUser != null) {

			id = getUser.getId();
			return id;
		}

		else {
			return 0;
		}

	}

}
