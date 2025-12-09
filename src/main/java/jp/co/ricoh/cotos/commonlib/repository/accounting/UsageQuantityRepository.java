package jp.co.ricoh.cotos.commonlib.repository.accounting;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.accounting.UsageQuantity;

@Repository
public interface UsageQuantityRepository extends CrudRepository<UsageQuantity, Long> {
	public List<UsageQuantity> findByContractIdAndContractDetailId(Long contractId, Long contractDetailId);

	public List<UsageQuantity> findByRjManageNumberAndItemCode(String rjManageNumber, String itemCode);

	public List<UsageQuantity> findByUsageDate(Date date);
}
