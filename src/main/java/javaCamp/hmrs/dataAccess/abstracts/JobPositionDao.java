package javaCamp.hmrs.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import javaCamp.hmrs.entites.concretes.JobPosition;
import javaCamp.hmrs.entites.concretes.User;

public interface JobPositionDao extends JpaRepository<JobPosition, Integer> {

	JobPosition findByName (String name);
}
