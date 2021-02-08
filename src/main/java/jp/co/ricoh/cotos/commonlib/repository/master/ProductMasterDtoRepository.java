package jp.co.ricoh.cotos.commonlib.repository.master;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.dto.parameter.master.ProductMasterDto;

@Repository
public interface ProductMasterDtoRepository extends CrudRepository<ProductMasterDto, Long> {

	public List<ProductMasterDto> findByAppIdInOrderByIdAsc(List<String> appId);

	public List<ProductMasterDto> findByAppIdNotInOrderByIdAsc(List<String> appId);

	public List<ProductMasterDto> findByIdInAndAppIdInOrderByIdAsc(List<Long> id, List<String> appId);

	public List<ProductMasterDto> findByIdInAndAppIdNotInOrderByIdAsc(List<Long> id, List<String> appId);
}