package jp.co.ricoh.cotos.commonlib.repository.master;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.master.VMailAddressEstimationList;

@Repository
public interface VMailAddressEstimationListRepository extends CrudRepository<VMailAddressEstimationList, Long> {

	@Query(value = "SELECT * FROM V_MAIL_ADDRESS_ESTIMATION_LIST WHERE TRANSACTION_ID = :TRANSACTIONID ", nativeQuery = true)
	public List<VMailAddressEstimationList> findByTranId(@Param("TRANSACTIONID") long tranId);

	@Query(value = "SELECT MAIL_ADDRESS FROM V_MAIL_ADDRESS_ESTIMATION_LIST WHERE TABLE_TYPE = :TABLETYPE AND TRANSACTION_ID = :TRANSACTIONID ", nativeQuery = true)
	public List<String> findByTableAndTranId(@Param("TABLETYPE") String tableType, @Param("TRANSACTIONID") long tranId);
}
