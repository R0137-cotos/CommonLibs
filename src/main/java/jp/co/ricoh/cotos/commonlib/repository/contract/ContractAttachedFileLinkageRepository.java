package jp.co.ricoh.cotos.commonlib.repository.contract;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.contract.ContractAttachedFileLinkage;

@Repository
public interface ContractAttachedFileLinkageRepository extends CrudRepository<ContractAttachedFileLinkage, Long> {

}
