package jp.co.ricoh.cotos.commonlib.repository.externallinkage;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.externallinkage.MobileEquipment;

@Repository
public interface MobileEquipmentRepository extends CrudRepository<MobileEquipment, Long> {

	public List<MobileEquipment> findByContractId(long contractId);
}
