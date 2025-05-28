package jp.co.ricoh.cotos.commonlib.repository.master;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.master.ModelAbbreviationMaster;

@Repository
public interface ModelAbbreviationMasterRepository extends CrudRepository<ModelAbbreviationMaster, Long> {
	@Query(value = "SELECT * FROM model_abbreviation_master WHERE n_model_code = :nModelCode ORDER BY id ASC", nativeQuery = true)
	public List<ModelAbbreviationMaster> findByNModelCodeOrderByIdAsc(@Param("nModelCode") String nModelCode);

}
