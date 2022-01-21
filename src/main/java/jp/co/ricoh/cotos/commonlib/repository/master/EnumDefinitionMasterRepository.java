package jp.co.ricoh.cotos.commonlib.repository.master;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.master.EnumDefinitionMaster;

@Repository
public interface EnumDefinitionMasterRepository extends CrudRepository<EnumDefinitionMaster, Long> {

}
