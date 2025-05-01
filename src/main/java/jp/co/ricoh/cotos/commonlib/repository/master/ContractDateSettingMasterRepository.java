package jp.co.ricoh.cotos.commonlib.repository.master;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.ContractType;
import jp.co.ricoh.cotos.commonlib.entity.master.ContractDateSettingMaster;

@Repository
public interface ContractDateSettingMasterRepository extends CrudRepository<ContractDateSettingMaster, Long> {

	@Query(value = "select * from ContractDateSettingMaster where product_master_id = productMasterId and (contract_type = contractType or contract_type is null) and arrangement_work_type_master_id = arrangementWorkTypeMasterId", nativeQuery = true)
	public List<ContractDateSettingMaster> findByProductMasterIdAndContractTypeAndArrangementWorkTypeMasterId(@Param("productMasterId") long productMasterId, @Param("contractType") ContractType contractType, @Param("arrangementWorkTypeMasterId") Long arrangementWorkTypeMasterId);
}