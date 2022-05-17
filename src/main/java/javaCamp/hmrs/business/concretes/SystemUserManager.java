package javaCamp.hmrs.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javaCamp.hmrs.business.abstracts.SystemUserService;
import javaCamp.hmrs.core.utilities.results.DataResult;
import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.core.utilities.results.SuccessDataResult;
import javaCamp.hmrs.dataAccess.abstracts.SystemUserDao;
import javaCamp.hmrs.dataAccess.abstracts.UserDao;
import javaCamp.hmrs.entites.concretes.SystemUser;

@Service
public class SystemUserManager  extends UserManager implements SystemUserService{
	
	private SystemUserDao systemUserDao;
	
	@Autowired
	public SystemUserManager(UserDao userDao, SystemUserDao systemUserDao) {
		super(userDao);
		this.systemUserDao= systemUserDao;
		// TODO Auto-generated constructor stub
	}

	@Override
	public DataResult<List<SystemUser>> getAll() {
		return new SuccessDataResult<>(this.systemUserDao.findAll(), "Sistem personelleri getirildi");
	}

	@Override
	public Result add(SystemUser systemUser, String passwordAgain) {
		// TODO Auto-generated method stub
		return null;
	}

}
