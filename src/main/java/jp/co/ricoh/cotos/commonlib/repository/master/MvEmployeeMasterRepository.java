package jp.co.ricoh.cotos.commonlib.repository.master;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import jp.co.ricoh.cotos.commonlib.entity.master.MvEmployeeMaster;

@Repository
public interface MvEmployeeMasterRepository extends CrudRepository<MvEmployeeMaster, String> {
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public MvEmployeeMaster findByMomEmployeeId(String momEmployeeId);
}
