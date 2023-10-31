package jp.co.ricoh.cotos.commonlib.repository.master;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.master.ReportPageMaster;
import jp.co.ricoh.cotos.commonlib.entity.master.ReportTemplateMaster;

@Repository
public interface ReportPageMasterRepository extends CrudRepository<ReportPageMaster, Long> {

	public List<ReportPageMaster> findByReportTemplateMaster(ReportTemplateMaster reportTemplateMaster);

}
