package jp.co.ricoh.cotos.commonlib.repository.accounting;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.accounting.AccountingTermDetail;

@Repository
public interface AccountingTermDetailRepository extends CrudRepository<AccountingTermDetail, Long> {

	public List<AccountingTermDetail> findByContractDetailId(Long contractDetailId);

}
