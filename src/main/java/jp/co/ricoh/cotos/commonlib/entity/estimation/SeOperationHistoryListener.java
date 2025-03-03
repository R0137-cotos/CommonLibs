package jp.co.ricoh.cotos.commonlib.entity.estimation;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.persistence.PrePersist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.ricoh.cotos.commonlib.logic.date.DateUtil;

@Component
public class SeOperationHistoryListener {

	@Autowired
	DateUtil dateUtil;

	/**
	 * 有効期限From、有効期限Toを自動的に付与する。
	 *
	 * @param SeOperationHistory
	 */
	@PrePersist
	public void automaticSetExpiration(SeOperationHistory seOperationHistory) {

		// 有効期限From
		LocalDate localDate = dateUtil.getSystemDate();
		Date parsedExpirationFrom = dateUtil.convertLocalDate2Date(localDate);
		// 有効期限To
		LocalDate localDateNextMonth = localDate.plus(1, ChronoUnit.MONTHS);
		Date parsedExpirationTo = dateUtil.convertLocalDate2Date(localDateNextMonth);

		seOperationHistory.setExpirationFrom(parsedExpirationFrom);
		seOperationHistory.setExpirationTo(parsedExpirationTo);
	}
}
