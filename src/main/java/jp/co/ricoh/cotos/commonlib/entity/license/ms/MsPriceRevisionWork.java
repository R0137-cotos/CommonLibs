package jp.co.ricoh.cotos.commonlib.entity.license.ms;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * MS_価格改定対象WORK
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "ms_price_revision_work")
public class MsPriceRevisionWork extends EntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ms_price_revision_work_seq")
	@SequenceGenerator(name = "ms_price_revision_work_seq", sequenceName = "ms_price_revision_work_seq", allocationSize = 1)
	@ApiModelProperty(value = "MS_価格改定対象WORKID", required = true, position = 1, allowableValues = "range[0,9223372036854775807]")
	private long id;

	/**
	 * RJ管理番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "RJ管理番号", required = false, position = 2, allowableValues = "range[0,255]")
	private String rjManageNumber;

	/**
	 * 品種マスタID
	 */
	@Min(0)
	@ApiModelProperty(value = "品種マスタID", required = true, position = 3, allowableValues = "range[0,9999999999999999999]")
	private Long itemMasterId;

	/**
	 * ライセンス終了日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "ライセンス終了日", required = false, position = 4)
	private Date licenseEndDate;

	/**
	 * 最新価格改定日マスタID
	 */
	@ApiModelProperty(value = "最新価格改定日マスタID", required = false, position = 5, allowableValues = "range[0,9999999999999999999]")
	private Long latestPriceRevisionDateMasterId;
}
