package jp.co.ricoh.cotos.commonlib.repository.common;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.common.MvIsysoneEquipmentInfo;

@Repository
public interface MvIsysoneEquipmentInfoRepository extends CrudRepository<MvIsysoneEquipmentInfo, String> {

	@Query(value = "SELECT * FROM MV_ISYSONE_EQUIPMENT_INFO WHERE KSYURGCD = :KSYURGCD AND KIKINO = :KIKINO", nativeQuery = true)
	public List<MvIsysoneEquipmentInfo> findByKsyurgcdAndKikino(@Param("KSYURGCD") String ksyurgcd, @Param("KIKINO") String kikino);
}
