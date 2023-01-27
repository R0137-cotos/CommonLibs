package jp.co.ricoh.cotos.commonlib.repository.master;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.master.ElementMaster;

@Repository
public interface ElementMasterRepository extends CrudRepository<ElementMaster, Long> {

	public List<ElementMaster> findByItemMasterId(long itemMasterId);

	public List<ElementMaster> findByItemMasterIdAndOrderBadCheckFlg(long itemMasterId, Integer orderBadCheckFlg);

	public List<ElementMaster> findByItemMasterIdIn(List<Long> itemMasterIdList);

	public List<ElementMaster> findByItemMasterIdInAndOrderBadCheckFlg(List<Long> itemMasterIdList, Integer orderBadCheckFlg);
}
