package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 祝日マスタ
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "public_holiday_master")
public class PublicHolidayMaster extends EntityBaseMaster {

	@Id
	@Temporal(TemporalType.DATE)
	@Schema(description = "祝日日付", requiredMode = Schema.RequiredMode.REQUIRED)
	private Date holidayDate;

	@Schema(description = "祝日名称", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String holidayName;
}
