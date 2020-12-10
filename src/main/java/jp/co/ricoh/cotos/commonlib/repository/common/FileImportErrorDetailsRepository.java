package jp.co.ricoh.cotos.commonlib.repository.common;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.common.FileImportErrorDetails;

@Repository
public interface FileImportErrorDetailsRepository extends CrudRepository<FileImportErrorDetails, Long> {
	@Query(value = "select * from file_import_error_details "//
			+ "where file_import_management_id = :fileKindManagementMasterId "//
			+ "order by id", nativeQuery = true)
	public List<FileImportErrorDetails> findByFileImportManagementOrderById(@Param("fileKindManagementMasterId") long fileKindManagementMasterId);
}
