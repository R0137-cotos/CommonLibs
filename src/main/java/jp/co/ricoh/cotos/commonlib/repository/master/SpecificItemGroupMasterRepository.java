package jp.co.ricoh.cotos.commonlib.repository.master;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.master.SpecificItemGroupMaster;

@Repository
public interface SpecificItemGroupMasterRepository extends CrudRepository<SpecificItemGroupMaster, Long> {

	public List<SpecificItemGroupMaster> findByIdIn(List<Long> specificItemGroupMasterIdList);

	public List<SpecificItemGroupMaster> findBySpecificItemGroupDiv(String specificItemGroupDiv);
}
