package jp.co.ricoh.cotos.commonlib.migrate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.migrate.entity.BasicContents;

@Repository
public interface BasicContentsRepository extends CrudRepository<BasicContents, Long> {

	public BasicContents findFirstByBplatsRequestNoAndMakerManageNo(String bplatsRequestNo, String makerManageNo);

	@Query(value = "select b.* from basic_contents b, cotos_management_info c "//
			+ "where b.id = c.basic_contents_id "//
			+ "and trunc(b.contract_start_date) <= :contractStartDate "//
			+ "and c.cotos_processing_flg = :cotosProcessingFlg "//
			+ "order by b.id ", nativeQuery = true)
	public List<BasicContents> findByContractStartDateAndCotosProcessingFlg(@Param("contractStartDate") String contractStartDate, @Param("cotosProcessingFlg") String cotosProcessingFlg);

	public List<BasicContents> findByMakerManageNo(String makerMmanageNo);

}
