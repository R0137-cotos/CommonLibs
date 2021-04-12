package jp.co.ricoh.cotos.commonlib.repository.master;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import jp.co.ricoh.cotos.commonlib.entity.master.EmpGrpManagementMaster;

public interface EmpGrpManagementMasterRepository extends CrudRepository<EmpGrpManagementMaster, Long> {

	public List<EmpGrpManagementMaster> findByGroupCode(String groupCode);

}
