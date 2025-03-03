package jp.co.ricoh.cotos.commonlib.entity.estimation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import javax.persistence.PrePersist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.ricoh.cotos.commonlib.logic.dateCalcPattern.DateCalcPatternUtil;
import jp.co.ricoh.cotos.commonlib.repository.estimation.SeOperationHistoryRepository;

@Component
public class SeOperationHistoryListener {

	private static SeOperationHistoryRepository seOperationHistoryRepository;
	private static final String DATE_FORMAT_NEW = "uuuu/MM/dd";

	@Autowired
	public void setSeOperationHistoryRepository(SeOperationHistoryRepository seOperationHistoryRepository) {
		SeOperationHistoryListener.seOperationHistoryRepository = seOperationHistoryRepository;
	}

	@Autowired
	DateCalcPatternUtil dateCalcPatternUtil;

	/**
	 * 有効期限From、有効期限Toを自動的に付与する。
	 *
	 * @param SeOperationHistory
	 */
	@PrePersist
	public void automaticSetExpirationFrom(SeOperationHistory seOperationHistory) {
		// 有効期限From
		LocalDate localDate = LocalDate.now();
		String localDateStr = localDate.format(DateTimeFormatter.ofPattern(DATE_FORMAT_NEW));
		// 有効期限To
		LocalDate localDateNextMonth = localDate.plus(1, ChronoUnit.MONTHS);
		String localDateNextMonthStr = localDateNextMonth.format(DateTimeFormatter.ofPattern(DATE_FORMAT_NEW));
		// 有効期限を Date型 に変換
		java.util.Date parsedTestDateFrom = dateCalcPatternUtil.stringToDateConverter(localDateStr, null);
		java.util.Date parsedTestDateTo = dateCalcPatternUtil.stringToDateConverter(localDateNextMonthStr, null);

		seOperationHistory.setExpirationFrom(parsedTestDateFrom);
		seOperationHistory.setExpirationTo(parsedTestDateTo);

	}
}
