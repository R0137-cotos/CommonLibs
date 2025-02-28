package jp.co.ricoh.cotos.commonlib.repository.contract;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.contract.ManagedContractEquipmentStatus;

@Repository
public interface ManagedContractEquipmentStatusRepository extends CrudRepository<ManagedContractEquipmentStatus, Long> {
	public List<ManagedContractEquipmentStatus> findByContractId(long contractId);
}
