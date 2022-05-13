package javaCamp.hmrs.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import javaCamp.hmrs.entites.concretes.User;

public interface UserDao extends JpaRepository<User, Integer> {

	List<User> findByEmail(String email);
	
	
}
