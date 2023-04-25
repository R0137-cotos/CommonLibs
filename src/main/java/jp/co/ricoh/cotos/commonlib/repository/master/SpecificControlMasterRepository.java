package jp.co.ricoh.cotos.commonlib.repository.master;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.master.SpecificControlMaster;

@Repository
public interface SpecificControlMasterRepository extends CrudRepository<SpecificControlMaster, Long> {

	public List<SpecificControlMaster> findByIdIn(List<Long> specificControlMasterIdList);

	public List<SpecificControlMaster> findBySpecificItemGroupMasterIdAndSpecificControlDivIn(Long specificItemGroupMasterId, List<String> specificControlDivList);
}
