package javaCamp.hmrs.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import javaCamp.hmrs.entites.concretes.JobAdvertisement;

public interface JobAdvertisementDao extends JpaRepository<JobAdvertisement, Integer> {

}
