package javaCamp.hmrs.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import javaCamp.hmrs.entites.concretes.SystemManagerEmployerUserApprove;

public interface SystemManagerEmployerUserApproveDao extends JpaRepository<SystemManagerEmployerUserApprove, Integer> {

	SystemManagerEmployerUserApprove findByUserId(int userId);
}
