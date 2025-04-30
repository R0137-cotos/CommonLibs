package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(value = "祝日日付", required = true, position = 1)
	private Date holidayDate;

	@ApiModelProperty(value = "祝日名称", required = false, position = 2)
	private String holidayName;
}
