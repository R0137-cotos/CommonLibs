package jp.co.ricoh.cotos.commonlib.repository.accounting;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.EnumType.OsoProcessingStatus;
import jp.co.ricoh.cotos.commonlib.entity.accounting.OsoRequestDetailData;
import jp.co.ricoh.cotos.commonlib.entity.common.OsoRequestDetailDataAbstractEntity.ProcessingDiv;

@Repository
public interface OsoRequestDetailDataRepository extends CrudRepository<OsoRequestDetailData, Long> {

	public List<OsoRequestDetailData> findByProcessingDivAndOsoManageNumberAndItemCodeAndProcessingStatus(ProcessingDiv processingDiv, String osoManageNumber, String itemCode, OsoProcessingStatus processingStatus);

	public List<OsoRequestDetailData> findByOsoManageNumberAndItemCodeAndProcessingStatusOrderByIdDesc(String osoManageNumber, String itemCode, OsoProcessingStatus processingStatus);

	public List<OsoRequestDetailData> findByContractDetailId(Long contractDetailId);
}
