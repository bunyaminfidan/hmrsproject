package javaCamp.hmrs.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import javaCamp.hmrs.entites.concretes.BaseIndividualUser;


public interface BaseIndividualUserDao  extends JpaRepository<BaseIndividualUser, Integer>{
	


}
