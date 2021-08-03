package jp.co.ricoh.cotos.commonlib.repository.common;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.common.VMailAddressArrangementList;

@Repository
public interface VMailAddressArrangementListRepository extends CrudRepository<VMailAddressArrangementList, Long> {

	@Query(value = "SELECT * FROM V_MAIL_ADDRESS_ARRANGEMENT_LIST WHERE TRANSACTION_ID = :TRANSACTIONID ", nativeQuery = true)
	public List<VMailAddressArrangementList> findByTranId(@Param("TRANSACTIONID") long tranId);

	@Query(value = "SELECT MAIL_ADDRESS FROM V_MAIL_ADDRESS_ARRANGEMENT_LIST WHERE TABLE_TYPE = :TABLETYPE AND TRANSACTION_ID = :TRANSACTIONID ", nativeQuery = true)
	public List<String> findByTableAndTranId(@Param("TABLETYPE") String tableType, @Param("TRANSACTIONID") long tranId);
}
