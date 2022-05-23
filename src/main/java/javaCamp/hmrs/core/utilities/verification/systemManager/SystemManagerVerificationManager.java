package javaCamp.hmrs.core.utilities.verification.systemManager;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javaCamp.hmrs.core.utilities.results.DataResult;
import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.core.utilities.results.SuccessDataResult;
import javaCamp.hmrs.core.utilities.results.SuccessResult;
import javaCamp.hmrs.dataAccess.abstracts.SystemManagerEmployerUserApproveDao;
import javaCamp.hmrs.entites.concretes.SystemManagerEmployerUserApprove;

@Service
public class SystemManagerVerificationManager implements SystemManagerVerificationService {

	private SystemManagerEmployerUserApproveDao systemManagerEmployerUserApproveDao;

	@Autowired
	public SystemManagerVerificationManager(SystemManagerEmployerUserApproveDao systemManagerEmployerUserApproveDao) {
		super();
		this.systemManagerEmployerUserApproveDao = systemManagerEmployerUserApproveDao;
	}

	@Override
	public Result add(int userId, int systemManagerId) {
		SystemManagerEmployerUserApprove systemManagerEmployerUserApprove = new SystemManagerEmployerUserApprove();

		systemManagerEmployerUserApprove = this.getByEmployerUserId(userId).getData();
		
		systemManagerEmployerUserApprove.setApprovalDate(LocalDate.now());
		systemManagerEmployerUserApprove.setApproved(true);
		systemManagerEmployerUserApprove.setApprovedManagerId(systemManagerId);
		this.systemManagerEmployerUserApproveDao.save(systemManagerEmployerUserApprove);

		return new SuccessResult("Sistem yöneticisi iş vereni doğrulama işlemi tamamlandı");
	}

	@Override
	public DataResult<SystemManagerEmployerUserApprove> getByEmployerUserId(int userId) {

		return new SuccessDataResult<SystemManagerEmployerUserApprove>(
				this.systemManagerEmployerUserApproveDao.findByUserId(userId),
				"Sistem yöneticisi için iş veren getirildi");
	}

}
