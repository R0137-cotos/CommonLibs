package jp.co.ricoh.cotos.commonlib.repository.contract;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.contract.Contract;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractEquipmentAdditionInfo;

@Repository
public interface ContractEquipmentAdditionInfoRepository extends CrudRepository<ContractEquipmentAdditionInfo, Long> {
	public ContractEquipmentAdditionInfo findByContractAndAccumulationContractEquipmentId(Contract contract, long accumulationContractEquipmentId);
}
