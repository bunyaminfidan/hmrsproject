package javaCamp.hmrs.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javaCamp.hmrs.business.abstracts.EmployerUserService;
import javaCamp.hmrs.core.utilities.results.DataResult;
import javaCamp.hmrs.core.utilities.results.ErrorResult;
import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.core.utilities.results.SuccessDataResult;
import javaCamp.hmrs.core.utilities.results.SuccessResult;
import javaCamp.hmrs.dataAccess.abstracts.EmployerUserDao;
import javaCamp.hmrs.dataAccess.abstracts.UserDao;
import javaCamp.hmrs.entites.concretes.EmployerUser;
import javaCamp.hmrs.entites.concretes.User;

@Service
@Component
@Qualifier("EmployerUserManager")
@Primary
public class EmployerUserManager extends UserManager implements EmployerUserService {

	private EmployerUserDao employerUserDao;

	@Autowired
	public EmployerUserManager(UserDao userDao, EmployerUserDao employerUserDao) {
		super(userDao);
		this.employerUserDao = employerUserDao;
	}

	@Override
	public Result add(EmployerUser employerUser) {

		List<User> users = this.userDao.findByEmail(employerUser.getEmail());

		if (!users.isEmpty()) {
			return new ErrorResult("Email sistemde kayıtlı");

		} else {

			this.employerUserDao.save(employerUser);
			return new SuccessResult();
		}

	}

	@Override
	public DataResult<List<EmployerUser>> getAll() {
		// TODO Auto-generated method stub
		return new SuccessDataResult<>(this.employerUserDao.findAll(), "İş verenler getirildi");
	}

}
