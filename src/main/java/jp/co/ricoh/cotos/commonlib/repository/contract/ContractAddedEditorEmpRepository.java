package jp.co.ricoh.cotos.commonlib.repository.contract;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.contract.ContractAddedEditorEmp;

@Repository
public interface ContractAddedEditorEmpRepository extends CrudRepository<ContractAddedEditorEmp, Long> {

	public List<ContractAddedEditorEmp> findByContractId(long contractId);

}
