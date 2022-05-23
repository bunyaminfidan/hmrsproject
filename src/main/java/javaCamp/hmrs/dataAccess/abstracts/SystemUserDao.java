package javaCamp.hmrs.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import javaCamp.hmrs.entites.concretes.SystemUser;

public interface SystemUserDao extends JpaRepository<SystemUser, Integer> {

	SystemUser findByNationalityIdIs(String nationalityId);

}
