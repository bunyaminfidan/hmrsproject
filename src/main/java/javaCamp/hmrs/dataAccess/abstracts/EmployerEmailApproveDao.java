package javaCamp.hmrs.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import javaCamp.hmrs.entites.concretes.EmployerEmailApprove;

public interface EmployerEmailApproveDao extends JpaRepository<EmployerEmailApprove, Integer> {
	
	EmployerEmailApprove findByVerifyCode(String verifyCode);

}
