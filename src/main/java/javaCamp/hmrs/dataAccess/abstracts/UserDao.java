package javaCamp.hmrs.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.entites.concretes.User;

public interface UserDao extends JpaRepository<User, Integer> {

	User findByEmail(String email);

}
