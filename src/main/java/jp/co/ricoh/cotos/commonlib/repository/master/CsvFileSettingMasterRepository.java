package jp.co.ricoh.cotos.commonlib.repository.master;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.master.CsvFileSettingMaster;

@Repository
public interface CsvFileSettingMasterRepository extends CrudRepository<CsvFileSettingMaster, Long> {
}
