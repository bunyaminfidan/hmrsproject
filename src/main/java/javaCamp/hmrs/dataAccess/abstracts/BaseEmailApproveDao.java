package javaCamp.hmrs.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import javaCamp.hmrs.entites.concretes.BaseEmailApprove;
import javaCamp.hmrs.entites.concretes.User;

public interface BaseEmailApproveDao extends JpaRepository<BaseEmailApprove, Integer>{
	

	


}
