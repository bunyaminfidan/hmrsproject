package javaCamp.hmrs.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import javaCamp.hmrs.entites.concretes.EmployerUser;

public interface EmployerUserDao extends JpaRepository<EmployerUser, Integer> {

	

}
