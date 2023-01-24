package jp.co.ricoh.cotos.commonlib.repository.externallinkage;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.externallinkage.HandoverMobileEquipment;

@Repository
public interface HandoverMobileEquipmentRepository extends CrudRepository<HandoverMobileEquipment, Long> {

	public List<HandoverMobileEquipment> findByContractIdAndDisengagementFlg(long contractId, int disengagementFlg);

	public List<HandoverMobileEquipment> findByHandoverContractId(long contractId);
}
