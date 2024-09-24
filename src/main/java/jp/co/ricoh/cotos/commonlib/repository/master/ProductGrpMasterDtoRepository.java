package jp.co.ricoh.cotos.commonlib.repository.master;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.master.ProductGrpMasterDto;

@Repository
public interface ProductGrpMasterDtoRepository extends CrudRepository<ProductGrpMasterDto, Long> {
	public List<ProductGrpMasterDto> findByIdIn(List<Long> productGrpMasterIdList);
}
