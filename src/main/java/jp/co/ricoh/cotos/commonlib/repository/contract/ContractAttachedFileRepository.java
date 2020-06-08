package jp.co.ricoh.cotos.commonlib.repository.contract;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.contract.ContractAttachedFile;

@Repository
public interface ContractAttachedFileRepository extends CrudRepository<ContractAttachedFile, Long> {
	public List<ContractAttachedFile> findByContractId(Long contractId);
}
