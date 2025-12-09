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

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "MS_価格改定対象WORKID", required = true, allowableValues = "range[0,9223372036854775807]")
	private long id;

	/**
	 * RJ管理番号
	 */
	@Size(max = 255)
	@Schema(description = "RJ管理番号", required = false, allowableValues = "range[0,255]")
	private String rjManageNumber;

	/**
	 * 品種マスタID
	 */
	@Min(0)
	@Schema(description = "品種マスタID", required = true, allowableValues = "range[0,9999999999999999999]")
	private Long itemMasterId;

	/**
	 * ライセンス終了日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "ライセンス終了日", required = false)
	private Date licenseEndDate;

	/**
	 * 最新価格改定日マスタID
	 */
	@Schema(description = "最新価格改定日マスタID", required = false, allowableValues = "range[0,9999999999999999999]")
	private Long latestPriceRevisionDateMasterId;
}
