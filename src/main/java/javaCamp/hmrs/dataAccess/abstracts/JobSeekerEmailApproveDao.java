package javaCamp.hmrs.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import javaCamp.hmrs.entites.concretes.JobSeekerEmailApprove;

public interface JobSeekerEmailApproveDao extends JpaRepository<JobSeekerEmailApprove, Integer>{
	
	JobSeekerEmailApprove findByEmail(String email);

}
