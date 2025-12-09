package jp.co.ricoh.cotos.commonlib.repository.master;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.master.CheckAlertTargetMaster;

@Repository
public interface CheckAlertTargetMasterRepository extends CrudRepository<CheckAlertTargetMaster, Long> {

}
