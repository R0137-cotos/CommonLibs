package jp.co.ricoh.cotos.commonlib.repository.common;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.common.VMailAddressContractList;

@Repository
public interface VMailAddressContractListRepository extends CrudRepository<VMailAddressContractList, Long> {

	@Query(value = "SELECT * FROM V_MAIL_ADDRESS_CONTRACT_LIST WHERE TRANSACTION_ID = :TRANSACTIONID ", nativeQuery = true)
	public List<VMailAddressContractList> findByTranId(@Param("TRANSACTIONID") long tranId);

	@Query(value = "SELECT MAIL_ADDRESS FROM V_MAIL_ADDRESS_CONTRACT_LIST WHERE TABLE_TYPE = :TABLETYPE AND TRANSACTION_ID = :TRANSACTIONID ", nativeQuery = true)
	public List<String> findByTableAndTranId(@Param("TABLETYPE") String tableType, @Param("TRANSACTIONID") long tranId);
}
