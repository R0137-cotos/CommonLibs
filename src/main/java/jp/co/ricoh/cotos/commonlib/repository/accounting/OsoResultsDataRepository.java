package jp.co.ricoh.cotos.commonlib.repository.accounting;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.EnumType.OsoProcessingStatus;
import jp.co.ricoh.cotos.commonlib.entity.accounting.OsoResultsData;

@Repository
public interface OsoResultsDataRepository extends CrudRepository<OsoResultsData, Long> {
	public List<OsoResultsData> findByOsoManageNumberAndItemCodeAndProcessingStatusOrderByIdDesc(String osoManageNumber, String itemCode, OsoProcessingStatus processingStatus);
}
