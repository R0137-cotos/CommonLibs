package jp.co.ricoh.cotos.commonlib.repository.accounting;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.accounting.Accounting;

@Repository
public interface AccountingRepository extends CrudRepository<Accounting, Long> {

	public List<Accounting> findByCostTypeAndFfmFlag(String costType, int ffmFlag);

	@Modifying
	@Query("update Accounting ac set "//
			+ "ac.ffmFlag = 1"//
			+ "where ac.costType = :costType "//
			+ "and ac.ffmFlag = :ffmFlag")
	public int updateFfmFlagByCostTypeAndFfmFlag(@Param("costType") String costType, @Param("ffmFlag") int ffmFlag);

}
