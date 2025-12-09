package jp.co.ricoh.cotos.commonlib.repository.arrangement;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.arrangement.ArrangementPicWorkerEmp;
import jp.co.ricoh.cotos.commonlib.entity.arrangement.ArrangementWork;

@Repository
public interface ArrangementPicWorkerEmpRepository extends CrudRepository<ArrangementPicWorkerEmp, Long> {
	public ArrangementPicWorkerEmp findByArrangementWork(ArrangementWork arrangementWork);
}
