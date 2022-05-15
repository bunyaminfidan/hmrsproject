package javaCamp.hmrs.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javaCamp.hmrs.business.abstracts.EmployerUserService;
import javaCamp.hmrs.core.utilities.helpers.EmailIsWebsiteDomainCheckHelper;
import javaCamp.hmrs.core.utilities.helpers.GetUserDetailHelper;
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
	public DataResult<List<EmployerUser>> getAll() {
		// TODO Auto-generated method stub
		return new SuccessDataResult<>(this.employerUserDao.findAll(), "İş verenler getirildi");
	}

	@Override
	public Result add(EmployerUser employerUser, String passwordAgain) {

		System.out.println("employerUser "+employerUser.getPassword() + " passwordAgain" + passwordAgain);

//		if (employerUser.getPassword() == passwordAgain)
//			return new ErrorResult("Girilen şifreler aynı değil");

		
		
		if (!super.add(employerUser).isSuccess())
			return new ErrorResult("İşveren sistemde zaten kayıtlı");

		int getUserId = GetUserDetailHelper.getUserId(userDao, employerUser);
		if (getUserId != 0) {

			employerUser.setId(getUserId);
			this.employerUserDao.save(employerUser);
			return new SuccessResult("İşveren kayıt edildi");

		} else {
			return new ErrorResult("İşveren kayıt edilemedi");
		}

	}

}
