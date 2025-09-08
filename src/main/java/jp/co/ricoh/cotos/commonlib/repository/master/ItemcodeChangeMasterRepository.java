package jp.co.ricoh.cotos.commonlib.repository.master;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.master.ItemcodeChangeMaster;

@Repository
public interface ItemcodeChangeMasterRepository extends CrudRepository<ItemcodeChangeMaster, Long> {
	public List<ItemcodeChangeMaster> findByOldMasterId(Long oldMasterId);

}
