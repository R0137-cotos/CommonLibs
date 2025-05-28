package jp.co.ricoh.cotos.commonlib.entity.estimation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.PrePersist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.ricoh.cotos.commonlib.db.DBUtil;
import jp.co.ricoh.cotos.commonlib.entity.contract.GeneratedNumber;
import jp.co.ricoh.cotos.commonlib.entity.master.ProductGrpIdentifierMaster;
import jp.co.ricoh.cotos.commonlib.entity.master.ProductGrpMaster;
import jp.co.ricoh.cotos.commonlib.repository.master.ProductGrpMasterRepository;

@Component
public class EstimationListener {
	private static final String ID_PREFIX = "CE";

	private static DBUtil dbUtil;
	private static ProductGrpMasterRepository productGrpMasterRepository;

	@Autowired
	public void setDBUtil(DBUtil dbUtil) {
		EstimationListener.dbUtil = dbUtil;
	}

	@Autowired
	public void setProductMasterRepository(ProductGrpMasterRepository productGrpMasterRepository) {
		EstimationListener.productGrpMasterRepository = productGrpMasterRepository;
	}

	/**
	 * 見積番号、RJ管理番号を付与する。
	 *
	 * @param entity
	 */
	@PrePersist
	public void appendsEstimationNumber(Estimation entity) {

		/**
		 * 見積番号
		 */
		if (null == entity.getEstimationNumber()) {
			long sequence = dbUtil.loadSingleFromSQLFile("sql/nextEstimationNumberSequence.sql", GeneratedNumber.class).getGeneratedNumber();
			entity.setEstimationNumber(ID_PREFIX + new SimpleDateFormat("yyyyMMdd").format(new Date()) + String.format("%05d", sequence));
		}

		/**
		 * RJ管理番号
		 * 商品グループマスタ.商品グループ識別子 + 7桁の連番
		 */
		ProductGrpMaster productGrpMaster = productGrpMasterRepository.findById(entity.getProductGrpMasterId()).orElse(null);
		if (null == entity.getRjManageNumber() && null != productGrpMaster) {
			ProductGrpIdentifierMaster productGrpIdentifierMaster = productGrpMaster.getProductGrpIdentifierMaster();
			if (null == productGrpIdentifierMaster) return;
			String productGrpIdentifier = productGrpIdentifierMaster.getProductGrpIdentifier();
			String sequenceName = productGrpIdentifierMaster.getSequenceName();
			Map<String, Object> param = new HashMap<>();
			param.put("sequenceName", sequenceName);
			long sequence = dbUtil.loadSingleFromSQLFile("sql/nextRjManageNumberSequence.sql", GeneratedNumber.class, param)
					.getGeneratedNumber();
			entity.setRjManageNumber(productGrpIdentifier + String.format("%07d", sequence));
		}
	}
}
