package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 非営業日カレンダーマスタ
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "non_business_day_calendar_master")
public class NonBusinessDayCalendarMaster extends EntityBaseMaster {

	@Id
	@Schema(description = "非営業日", requiredMode = Schema.RequiredMode.REQUIRED)
	private Date nonBusinessDay;

	@Schema(description = "ベンダー略称", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String vendorShortName;
}
