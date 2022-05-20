package javaCamp.hmrs.dataAccess.abstracts;


import org.springframework.data.jpa.repository.JpaRepository;

import javaCamp.hmrs.entites.concretes.User;

public interface UserDao extends JpaRepository<User, Integer> {

	User findByEmail(String email);
	

}
