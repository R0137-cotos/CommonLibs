package jp.co.ricoh.cotos.commonlib.repository.accounting;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import jp.co.ricoh.cotos.commonlib.entity.accounting.Accounting;

public interface AccountingRepository extends CrudRepository<Accounting, Long> {

	public List<Accounting> findByCostTypeAndFfmFlag(String costType, int ffmFlag);

}
