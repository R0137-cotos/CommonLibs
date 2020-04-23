package jp.co.ricoh.cotos.commonlib.repository.master;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.ContractType;
import jp.co.ricoh.cotos.commonlib.entity.master.ArrangementWorkOrderMaster;
import jp.co.ricoh.cotos.commonlib.entity.master.ArrangementWorkTypeMaster;
import jp.co.ricoh.cotos.commonlib.entity.master.ProductMaster;

@Repository
public interface ArrangementWorkOrderMasterRepository extends CrudRepository<ArrangementWorkOrderMaster, Long> {

	public ArrangementWorkOrderMaster findByProductMasterAndContractTypeAndDisengagementFlgAndArrangementWorkTypeMaster(ProductMaster productMaster, ContractType contractType, int disengagementFlg, ArrangementWorkTypeMaster arrangementWorkTypeMaster);
}
