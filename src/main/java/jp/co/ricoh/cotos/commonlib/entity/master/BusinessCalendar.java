package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 業務カレンダー
 */
@Entity
@Data
@Table(name = "business_calendar")
public class BusinessCalendar {

	@Id
	@Schema(description = "カレンダー日付", requiredMode = Schema.RequiredMode.REQUIRED)
	private Date calendarDate;

	/**
	 * 休日フラグ
	 */
	@Column(nullable = false)
	@Schema(description = "休日フラグ", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,1]")
	private int businessHoliday;

	/**
	 * 営業日付
	 */
	@Column(nullable = false)
	@Schema(description = "営業日付", requiredMode = Schema.RequiredMode.REQUIRED)
	private Date businessDay;

}
