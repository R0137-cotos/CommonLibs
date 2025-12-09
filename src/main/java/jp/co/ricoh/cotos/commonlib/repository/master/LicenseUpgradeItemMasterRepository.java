package jp.co.ricoh.cotos.commonlib.repository.master;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import jp.co.ricoh.cotos.commonlib.entity.master.LicenseUpgradeItemMaster;

public interface LicenseUpgradeItemMasterRepository extends CrudRepository<LicenseUpgradeItemMaster, Long> {

	public List<LicenseUpgradeItemMaster> findByProductMasterId(long productMasterId);

}
