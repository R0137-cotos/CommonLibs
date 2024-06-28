package jp.co.ricoh.cotos.commonlib.repository.master;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.master.MailCsvMaster;

@Repository
public interface MailCsvMasterRepository extends CrudRepository<MailCsvMaster, Long> {
	public List<MailCsvMaster> findByIdIn(String[] ids);

}
