package jp.co.ricoh.cotos.commonlib.entity.estimation;

import java.util.Date;

import javax.persistence.PrePersist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.ricoh.cotos.commonlib.repository.estimation.SeOperationHistoryRepository;

@Component
public class SeOperationHistoryListener {

	private static SeOperationHistoryRepository seOperationHistoryRepository;

	@Autowired
	public void setEstimationRepository(SeOperationHistoryRepository seOperationHistoryRepository) {
		SeOperationHistoryListener.seOperationHistoryRepository = seOperationHistoryRepository;
	}

	/**
	 * 見積情報をSE対応履歴に紐づけます。
	 *
	 * @param seOperationHistory
	 */
	@PrePersist
	public void appendsSeOperationHistoryFields(SeOperationHistory seOperationHistory) {
		seOperationHistory.setCreatedAt(new Date());

	}

}
