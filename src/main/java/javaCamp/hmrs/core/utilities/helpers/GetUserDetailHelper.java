package javaCamp.hmrs.core.utilities.helpers;

import javaCamp.hmrs.dataAccess.abstracts.JobSeekerUserDao;
import javaCamp.hmrs.dataAccess.abstracts.SystemUserDao;
import javaCamp.hmrs.dataAccess.abstracts.UserDao;
import javaCamp.hmrs.entites.concretes.JobSeekerUser;
import javaCamp.hmrs.entites.concretes.SystemUser;
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

	public static boolean isEmailRegistered(UserDao userDao, String email) {

		User getUser = userDao.findByEmail(email);

		if (getUser != null) {

			return true;
		}

		else {
			return false;
		}

	}

	public static boolean getSystemUserByNationalityId(SystemUserDao systemUserDao, String nationalityId) {

		SystemUser systemUser = systemUserDao.findByNationalityIdIs(nationalityId);

		if (systemUser != null)

			return true;

		else
			return false;

	}
	
	public static boolean getJobSeekerUserByNationalityId(JobSeekerUserDao jobSeekerUserDao, String nationalityId) {

		JobSeekerUser jobSeekerUser = jobSeekerUserDao.findByNationalityIdIs(nationalityId);

		if (jobSeekerUser != null)

			return true;

		else
			return false;

	}

}
