package jp.co.ricoh.cotos.commonlib.repository.master;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import jp.co.ricoh.cotos.commonlib.entity.master.DummyUserMaster;

public interface DummyUserMasterRepository extends CrudRepository<DummyUserMaster, Long> {
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean existsByUserId(String userId);

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public DummyUserMaster findByUserId(String userId);

}
