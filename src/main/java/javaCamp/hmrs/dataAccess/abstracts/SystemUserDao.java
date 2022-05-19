package javaCamp.hmrs.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import javaCamp.hmrs.entites.concretes.SystemUser;
import javaCamp.hmrs.entites.concretes.User;

public interface SystemUserDao extends JpaRepository<SystemUser, Integer> {

	SystemUser findByNationalityIdIs(String nationalityId);

}
