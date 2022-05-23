package javaCamp.hmrs.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;

import javaCamp.hmrs.business.abstracts.ApproveService;
import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.core.utilities.results.SuccessResult;
import javaCamp.hmrs.dataAccess.abstracts.ApproveDao;
import javaCamp.hmrs.entites.concretes.Approve;

public class ApproveManager implements ApproveService {

	ApproveDao approveDao;

	@Autowired
	public ApproveManager(ApproveDao approveDao) {
		super();
		this.approveDao = approveDao;
	}

	@Override
	public Result add(Approve approve) {
		this.approveDao.save(approve);
		return new SuccessResult("Doğrulama işlemi tamamlandı");
	}

//	@Override
//	public DataResult<Approve> getByUserId(int userId) {
//		return new SuccessDataResult<>(approveDao.findByUserId(userId), "Kullanıcı dogrulamaları getirildi");
//	}

}
