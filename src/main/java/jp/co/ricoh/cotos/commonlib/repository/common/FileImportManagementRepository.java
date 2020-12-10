package jp.co.ricoh.cotos.commonlib.repository.common;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.common.FileImportManagement;

@Repository
public interface FileImportManagementRepository extends CrudRepository<FileImportManagement, Long> {
	public List<FileImportManagement> findByFileKindManagementMasterIdAndImportDateOrderByIdDesc(long fileKindManagementMasterId, Date importDate);

	public List<FileImportManagement> findByFileKindManagementMasterIdOrderByImportDateDescIdDesc(long fileKindManagementMasterId);
}
