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
	@Query(value = "SELECT * FROM bounce_mail_record WHERE contract_id = :contractId AND n_x_contract_id = :nXContractId AND sent_at = :sentAt", nativeQuery = true)
	public List<BounceMailRecord> findByContractIdAndNXContractIdAndSentAt(@Param("contractId") String contractId, @Param("nXContractId") String nXContractId, @Param("sentAt") Date sentAt);

	public List<BounceMailRecord> findByDocNumberAndContractBranchNumber(String docNumber, Integer contractBranchNumber);

	public List<BounceMailRecord> findByCotosSupportCompletedAndMailControlMasterIdAndServiceCategoryAndTargetDocKeyOrderById(Integer cotosSupportCompleted, Long mailControlMasterId, ServiceCategory serviceCategory, String targetDocKey);
}
