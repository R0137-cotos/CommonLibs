package jp.co.ricoh.cotos.commonlib.repository.accounting;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.accounting.AccountingPeriodDetail;

@Repository
public interface AccountingPeriodDetailRepository extends CrudRepository<AccountingPeriodDetail, Long> {

	public List<AccountingPeriodDetail> findByContractId(long contractId);

}
