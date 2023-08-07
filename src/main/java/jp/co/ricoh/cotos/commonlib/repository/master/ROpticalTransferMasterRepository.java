package jp.co.ricoh.cotos.commonlib.repository.master;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.master.ROpticalTransferMaster;

@Repository
public interface ROpticalTransferMasterRepository extends CrudRepository<ROpticalTransferMaster, Long> {

}
