package jp.co.ricoh.cotos.commonlib.repository.master;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.master.FileKindManagementMaster;
import jp.co.ricoh.cotos.commonlib.entity.master.FileOperationRelationProductMaster;

@Repository
public interface FileKindManagementMasterRepository extends CrudRepository<FileKindManagementMaster, Long> {
	public List<FileKindManagementMaster> findByFileOperationRelationProductMasterInOrderByIdAsc(List<FileOperationRelationProductMaster> fileOperationRelationProductMasterList);
	
	public List<FileKindManagementMaster> findByFileKindTypeDiv(String fileKindTypeDiv);
}
