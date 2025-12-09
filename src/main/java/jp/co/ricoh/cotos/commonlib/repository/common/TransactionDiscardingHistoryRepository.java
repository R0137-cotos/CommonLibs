package jp.co.ricoh.cotos.commonlib.repository.common;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.common.TransactionDiscardingHistory;

@Repository
public interface TransactionDiscardingHistoryRepository extends CrudRepository<TransactionDiscardingHistory, Long> {

}
