package javaCamp.hmrs.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;


import javaCamp.hmrs.entites.concretes.JobSeekerUser;

public interface JobSeekerUserDao extends JpaRepository<JobSeekerUser, Integer> {

	JobSeekerUser findByNationalityId(String nationalityId);
}
