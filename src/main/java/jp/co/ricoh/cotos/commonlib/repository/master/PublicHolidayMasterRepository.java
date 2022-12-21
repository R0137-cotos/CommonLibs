package jp.co.ricoh.cotos.commonlib.repository.master;

import java.util.Date;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.master.PublicHolidayMaster;

@Repository
public interface PublicHolidayMasterRepository extends CrudRepository<PublicHolidayMaster, Date> {

}
