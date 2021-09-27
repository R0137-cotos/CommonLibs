package jp.co.ricoh.cotos.commonlib.repository.communication;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.EnumType.ServiceCategory;
import jp.co.ricoh.cotos.commonlib.entity.communication.BounceMailRecord;

@Repository
public interface BounceMailRecordRepository extends CrudRepository<BounceMailRecord, Long> {
	public List<BounceMailRecord> findByContractIdAndNXContractIdAndSentAt(String contractId, String nXContractId, Date sentAt);

	public List<BounceMailRecord> findByDocNumberAndContractBranchNumber(String docNumber, Integer contractBranchNumber);

	public List<BounceMailRecord> findByCotosSupportCompletedAndMailControlMasterIdAndServiceCategoryAndTargetDocKeyOrderById(Integer cotosSupportCompleted, Long mailControlMasterId, ServiceCategory serviceCategory, String targetDocKey);
}
