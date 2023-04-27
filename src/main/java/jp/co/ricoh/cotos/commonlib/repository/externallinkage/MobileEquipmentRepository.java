package jp.co.ricoh.cotos.commonlib.repository.externallinkage;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.externallinkage.MobileEquipment;

@Repository
public interface MobileEquipmentRepository extends CrudRepository<MobileEquipment, Long> {

	public List<MobileEquipment> findByContractId(long contractId);

	public List<MobileEquipment> findByContractIdAndDisengagementFlg(long contractId, int disengagementFlg);

	public List<MobileEquipment> findByEstimationIdAndDisengagementFlg(long estimationId, int disengagementFlg);
}
