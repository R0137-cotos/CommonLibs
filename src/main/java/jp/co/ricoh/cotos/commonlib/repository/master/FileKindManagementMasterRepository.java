package jp.co.ricoh.cotos.commonlib.repository.master;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.master.FileKindManagementMaster;
import jp.co.ricoh.cotos.commonlib.entity.master.FileKindManagementMaster.ImportExportType;
import jp.co.ricoh.cotos.commonlib.entity.master.FileOperationRelationProductMaster;

@Repository
public interface FileKindManagementMasterRepository extends CrudRepository<FileKindManagementMaster, Long> {
	public List<FileKindManagementMaster> findByFileOperationRelationProductMasterAndImportExportTypeOrderById(FileOperationRelationProductMaster fileOperationRelationProductMaster, ImportExportType importExportType);
}
