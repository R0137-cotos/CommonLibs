package jp.co.ricoh.cotos.commonlib.repository.common;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.common.FileImportManagement;

@Repository
public interface FileImportManagementRepository extends CrudRepository<FileImportManagement, Long> {
	public List<FileImportManagement> findByFileKindManagementIdAndImportDateOrderByIdDesc(long fileKindManagementId, Date importDate);

	public List<FileImportManagement> findByFileKindManagementIdOrderByImportDateDescIdDesc(long fileKindManagementId);
}
