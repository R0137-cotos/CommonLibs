package jp.co.ricoh.cotos.commonlib.repository.common;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.common.FileImportManagement;

@Repository
public interface FileImportManagementRepository extends CrudRepository<FileImportManagement, Long> {
	public List<FileImportManagement> findByFileKindManagementMasterIdAndImportDateOrderByIdDesc(long fileKindManagementMasterId, Date importDate);

	public List<FileImportManagement> findByFileKindManagementMasterIdOrderByImportDateDescIdDesc(long fileKindManagementMasterId);

	@Query(value = "SELECT fim.* FROM FILE_IMPORT_MANAGEMENT fim " //
			+ "INNER JOIN FILE_KIND_MANAGEMENT_MASTER fkmm " //
			+ "ON fim.FILE_KIND_MANAGEMENT_MASTER_ID = fkmm.ID " //
			+ "WHERE fkmm.FILE_KIND_TYPE_DIV IN(:fileKindTypeDiv ) " //
			+ "AND fim.IMPORT_DATE BETWEEN :importDateStart AND :importDateEnd ", nativeQuery = true)
	public List<FileImportManagement> fileKindNameImport(@Param("fileKindTypeDiv") List<String> fileKindTypeDiv, @Param("importDateStart") Date importDateStart, @Param("importDateEnd") Date importDateEnd);
}
