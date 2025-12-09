package jp.co.ricoh.cotos.commonlib.repository.accounting;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.accounting.OsoRequestData;

@Repository
public interface OsoRequestDataRepository extends CrudRepository<OsoRequestData, Long> {
	@Query(value = "SELECT * FROM OSO_REQUEST_DATA WHERE DATA_DIV = :DATA_DIV AND REQUEST_MANAGE_NUMBER = :REQUEST_MANAGE_NUMBER AND PROCESSING_STATUS = :PROCESSING_STATUS", nativeQuery = true)
	public List<OsoRequestData> findByDataDivAndRequestManageNumberAndProcessingStatus(@Param("DATA_DIV") String dataDiv, @Param("REQUEST_MANAGE_NUMBER") String requestManageNumber, @Param("PROCESSING_STATUS") String processingStatus);
}
