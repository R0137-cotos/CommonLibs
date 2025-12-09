package jp.co.ricoh.cotos.commonlib.repository.arrangement;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.arrangement.ArrangementWorkCheckResult;

@Repository
public interface ArrangementWorkCheckResultRepository extends CrudRepository<ArrangementWorkCheckResult, Long> {

	List<ArrangementWorkCheckResult> findByArrangementWorkIdAndCheckMatterCode(Long arrangementWorkId, String checkMatterCode);
}
