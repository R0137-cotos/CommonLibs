package jp.co.ricoh.cotos.commonlib.repository.contract;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.contract.PriceRewriteExclusionContract;

@Repository
public interface PriceRewriteExclusionContractRepository extends CrudRepository<PriceRewriteExclusionContract, Long> {

}
