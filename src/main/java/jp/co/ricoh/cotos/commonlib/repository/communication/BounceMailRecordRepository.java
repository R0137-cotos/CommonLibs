package jp.co.ricoh.cotos.commonlib.repository.communication;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.EnumType.ServiceCategory;
import jp.co.ricoh.cotos.commonlib.entity.communication.BounceMailRecord;

@Repository
public interface BounceMailRecordRepository extends CrudRepository<BounceMailRecord, Long> {
	public List<BounceMailRecord> findByContractIdAndNXContractIdAndSentAt(String contractId, String nXContractId, Date sentAt);

	public List<BounceMailRecord> findByDocNumberAndContractBranchNumber(String docNumber, Integer contractBranchNumber);

	public List<BounceMailRecord> findByCotosSupportCompletedAndMailControlMasterIdAndServiceCategoryAndTargetDocKeyOrderById(Integer cotosSupportCompleted, Long mailControlMasterId, ServiceCategory serviceCategory, String targetDocKey);

	@Query(value = "SELECT * FROM BOUNCE_MAIL_RECORD WHERE COTOS_SUPPORT_COMPLETED IS NULL AND MAIL_CONTROL_MASTER_ID = :MAIL_CONTROL_MASTER_ID AND SERVICE_CATEGORY = :SERVICE_CATEGORY AND TARGET_DOC_KEY = :TARGET_DOC_KEY", nativeQuery = true)
	public List<BounceMailRecord> findByMailControlMasterIdAndServiceCategoryAndTargetDocKeyOrderById(@Param("MAIL_CONTROL_MASTER_ID") Long mailControlMasterId, @Param("SERVICE_CATEGORY") ServiceCategory serviceCategory, @Param("TARGET_DOC_KEY") String targetDocKey);
}
