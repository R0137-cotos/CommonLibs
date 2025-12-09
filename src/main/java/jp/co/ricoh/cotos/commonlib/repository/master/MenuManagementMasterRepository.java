package jp.co.ricoh.cotos.commonlib.repository.master;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.master.MenuManagementMaster;

@Repository
public interface MenuManagementMasterRepository extends CrudRepository<MenuManagementMaster, Long> {

}
