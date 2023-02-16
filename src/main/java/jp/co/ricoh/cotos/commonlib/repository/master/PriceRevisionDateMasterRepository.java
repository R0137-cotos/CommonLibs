package jp.co.ricoh.cotos.commonlib.repository.master;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import jp.co.ricoh.cotos.commonlib.entity.master.PriceRevisionDateMaster;

public interface PriceRevisionDateMasterRepository extends CrudRepository<PriceRevisionDateMaster, Long> {

	@Query(value = "SELECT * FROM (SELECT * FROM PRICE_REVISION_DATE_MASTER WHERE PRODUCT_MASTER_ID = :productMasterId AND PRICE_REVISION_DATE < :priceRevisionDate ORDER BY ID DESC) WHERE ROWNUM = 1", nativeQuery = true)
	public List<PriceRevisionDateMaster> findByProductMasterIdAndPriceRevisionDate(@Param("productMasterId") Long productMasterId, @Param("priceRevisionDate") Date priceRevisionDate);
}
