package jp.co.ricoh.cotos.commonlib.repository.accounting;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.EnumType.OsoProcessingStatus;
import jp.co.ricoh.cotos.commonlib.entity.accounting.OsoRequestData;
import jp.co.ricoh.cotos.commonlib.entity.common.OsoRequestDataAbstractEntity.DataDiv;

@Repository
public interface OsoRequestDataRepository extends CrudRepository<OsoRequestData, Long> {
	public List<OsoRequestData> findByDataDivAndRequestManageNumberAndProcessingStatus(DataDiv dataDiv, String requestManageNumber, OsoProcessingStatus processingStatus);
}
