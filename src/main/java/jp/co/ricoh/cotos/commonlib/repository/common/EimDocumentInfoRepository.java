package jp.co.ricoh.cotos.commonlib.repository.common;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.EnumType.EimLinkedStatus;
import jp.co.ricoh.cotos.commonlib.entity.common.EimDocumentInfo;

@Repository
public interface EimDocumentInfoRepository extends CrudRepository<EimDocumentInfo, Long> {

	public List<EimDocumentInfo> findByKeiyakNoAndEimLinkedStatusAndOldDocumentFlg(String keiyakNo, EimLinkedStatus eimLinkedStatus, boolean oldDocumentFlg);
	
	public List<EimDocumentInfo> findByContractIdAndEimLinkedStatusAndOldDocumentFlg(long contractId, EimLinkedStatus eimLinkedStatus, boolean oldDocumentFlg);
}
