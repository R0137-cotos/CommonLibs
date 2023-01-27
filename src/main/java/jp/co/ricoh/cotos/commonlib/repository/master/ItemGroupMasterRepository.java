package jp.co.ricoh.cotos.commonlib.repository.master;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.master.ItemGroupMaster;

@Repository
public interface ItemGroupMasterRepository extends CrudRepository<ItemGroupMaster, Long> {

	public List<ItemGroupMaster> findByItemGroupDiv(String itemGroupDiv);

	public List<ItemGroupMaster> findByItemGroupDivIn(List<String> itemGroupDivList);

	public List<ItemGroupMaster> findByCategory(String category);

	public List<ItemGroupMaster> findByCategoryIn(List<String> categoryList);
}
