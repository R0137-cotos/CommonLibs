package jp.co.ricoh.cotos.commonlib.repository.master;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.master.CheckAlertMaster;

@Repository
public interface CheckAlertMasterRepository extends CrudRepository<CheckAlertMaster, Long> {
	@Query(value = "select cm.* from check_alert_master cm"//
			+ " left outer join check_alert_target_master tm on tm.check_alert_master_id = cm.id"//
			+ " where  cm.service_category = :SERVICE_CATEGORY"//
			+ " and (cm.common_check_flg = 1 or "//
			+ "         tm.target_master_id = :TARGET_MASTER_ID)", nativeQuery = true)
	public List<CheckAlertMaster> findByDomainAndTargetMasterId(@Param("SERVICE_CATEGORY") String serviceCategory, @Param("TARGET_MASTER_ID") Long targetMasterId);

}
