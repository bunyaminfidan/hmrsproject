package javaCamp.hmrs.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import javaCamp.hmrs.entites.concretes.BaseEmailApprove;

public interface BaseEmailApproveDao extends JpaRepository<BaseEmailApprove, Integer> {

}
