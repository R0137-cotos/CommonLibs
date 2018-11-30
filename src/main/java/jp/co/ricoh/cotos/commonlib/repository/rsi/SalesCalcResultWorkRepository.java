package jp.co.ricoh.cotos.commonlib.repository.rsi;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.ricoh.cotos.commonlib.entity.rsi.SalesCalcResultWork;
import jp.co.ricoh.cotos.commonlib.entity.rsi.SalesCalcResultWork.CotosProcessingFlg;

@Repository
public interface SalesCalcResultWorkRepository extends JpaRepository<SalesCalcResultWork, Long> {
	
	public List<SalesCalcResultWork> deleteByCotosProcessingFlgNotIn(List<CotosProcessingFlg> cotosProcessingFlg);
}
