package jp.co.ricoh.cotos.commonlib.repository.master;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.master.ArrangementWorkAuthControlMaster;
import jp.co.ricoh.cotos.commonlib.entity.master.AuthPatternMaster;

@Repository
public interface ArrangementWorkAuthControlMasterRepository extends CrudRepository<ArrangementWorkAuthControlMaster, Long> {

	List<ArrangementWorkAuthControlMaster> findByAuthPatternMasterIn(List<AuthPatternMaster> authPatternMasterList);

	List<ArrangementWorkAuthControlMaster> findByAuthPatternMasterIsNull();
}
