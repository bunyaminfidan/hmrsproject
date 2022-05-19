package javaCamp.hmrs.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import javaCamp.hmrs.entites.concretes.JobSeekerMernisApprove;

public interface JobSeekerMernisApproveDao extends JpaRepository<JobSeekerMernisApprove, Integer> {

}
