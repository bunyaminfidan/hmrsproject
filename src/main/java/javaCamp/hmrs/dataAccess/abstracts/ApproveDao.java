package javaCamp.hmrs.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import javaCamp.hmrs.entites.concretes.Approve;

public interface ApproveDao extends JpaRepository<Approve, Integer> {

	Approve findByUserId(int userId);

}
