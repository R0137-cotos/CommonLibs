package jp.co.ricoh.cotos.commonlib.repository.master;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import jp.co.ricoh.cotos.commonlib.entity.master.PriceRevisionDateMaster;

public interface PriceRevisionDateMasterRepository extends CrudRepository<PriceRevisionDateMaster, Long> {

	public List<PriceRevisionDateMaster> findByProductMasterId(Long productMasterId);
}
