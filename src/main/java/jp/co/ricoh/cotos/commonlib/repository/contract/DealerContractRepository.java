package jp.co.ricoh.cotos.commonlib.repository.contract;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.contract.DealerContract;

@Repository
public interface DealerContractRepository extends CrudRepository<DealerContract, Long> {

	public List<DealerContract> findByContractId(Long contractId);

}
