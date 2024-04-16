package jp.co.ricoh.cotos.commonlib.repository.master;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.master.ProductExtendsParameterMaster;

@Repository
public interface ProductExtendsParameterMasterRepository extends CrudRepository<ProductExtendsParameterMaster, Long> {

	public List<ProductExtendsParameterMaster> findByProductMasterId(Long productMasterId);

}
