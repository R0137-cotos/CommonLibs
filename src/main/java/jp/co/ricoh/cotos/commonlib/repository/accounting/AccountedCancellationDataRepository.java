package jp.co.ricoh.cotos.commonlib.repository.accounting;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.accounting.AccountedCancellationData;

@Repository
public interface AccountedCancellationDataRepository extends CrudRepository<AccountedCancellationData, Long> {

}
