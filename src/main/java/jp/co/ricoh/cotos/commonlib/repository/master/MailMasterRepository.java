package jp.co.ricoh.cotos.commonlib.repository.master;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.master.MailMaster;

@Repository
public interface MailMasterRepository extends CrudRepository<MailMaster, Long> {

	public MailMaster findByMailTypeDiv(String mailTypeDiv);
}
