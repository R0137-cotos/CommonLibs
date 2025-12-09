package jp.co.ricoh.cotos.commonlib.repository.master;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.master.FileOperationRelationProductMaster;

@Repository
public interface FileOperationRelationProductMasterRepository extends CrudRepository<FileOperationRelationProductMaster, Long> {

	public List<FileOperationRelationProductMaster> findByMenuManagementMasterIdAndMenuDetailsManagementMasterIdOrderById(long menuManagementMasterId, long menuDetailsManagementMasterId);

	public List<FileOperationRelationProductMaster> findByMenuManagementMasterIdAndMenuDetailsManagementMasterIdAndLicenseDivMasterIdOrderById(long menuManagementMasterId, long menuDetailsManagementMasterId, long licenseDivMasterid);
}
