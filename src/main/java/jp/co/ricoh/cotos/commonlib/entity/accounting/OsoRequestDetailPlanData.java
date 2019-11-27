package jp.co.ricoh.cotos.commonlib.entity.accounting;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.OsoProcessingStatus;
import jp.co.ricoh.cotos.commonlib.entity.accounting.OsoRequestDetailData.OsoCostType;
import jp.co.ricoh.cotos.commonlib.entity.accounting.OsoRequestDetailData.ProcessingDiv;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * OSO申込明細予定データ
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "oso_request_detail_plan_data")
public class OsoRequestDetailPlanData extends EntityBase {

	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "oso_request_detail_plan_data_seq")
	@SequenceGenerator(name = "oso_request_detail_plan_data_seq", sequenceName = "oso_request_detail_plan_data_seq", allocationSize = 1)
	@ApiModelProperty(value = "ID", required = true, position = 1, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * OSO申込ID
	 */
	@Column(nullable = false)
	@ApiModelProperty(value = "OSO申込ID", required = true, position = 2, allowableValues = "range[0,9999999999999999999]")
	private long osoRequestId;

	/**
	 * 契約明細ID
	 */
	@Column(nullable = false)
	@ApiModelProperty(value = "契約明細ID", required = true, position = 3, allowableValues = "range[0,9999999999999999999]")
	private long contractDetailId;

	/**
	 * 処理状態
	 */
	@ApiModelProperty(value = "処理状態", required = false, allowableValues = "未処理(\"0\"), 処理済(\"1\"), 処理対象外(\"9\"), 処理エラー(\"E\")", example = "0", position = 4)
	private OsoProcessingStatus processingStatus;

	/**
	 * 処理日時
	 */
	@ApiModelProperty(value = "処理日時", required = false, position = 5)
	@Temporal(TemporalType.TIMESTAMP)
	private Date processingAt;

	/**
	 * メッセージ
	 */
	@Column(name = "message")
	@Size(max = 4000)
	@ApiModelProperty(value = "メッセージ", required = false, position = 6, allowableValues = "range[0,4000]")
	private String osoMessage;

	/**
	 * RTS管理番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "RTS管理番号", required = false, position = 7, allowableValues = "range[0,255]")
	private String rtsManageNumber;

	/**
	 * RTS管理番号枝番
	 */
	@Max(999)
	@Min(0)
	@ApiModelProperty(value = "RTS管理番号枝番", required = false, position = 8, allowableValues = "range[1,999]")
	private Integer rtsManageBranchNumber;

	/**
	 * OSO管理番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "OSO管理番号", required = false, position = 9, allowableValues = "range[0,255]")
	private String osoManageNumber;

	/**
	 * 処理区分
	 */
	@ApiModelProperty(value = "処理区分", required = false, allowableValues = "追加(\"1\"), 変更(\"2\")", example = "1", position = 10)
	private ProcessingDiv processingDiv;

	/**
	 * 品種コード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "品種コード", required = false, position = 11, allowableValues = "range[0,255]")
	private String itemCode;

	/**
	 * 品種名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "品種名", required = false, position = 12, allowableValues = "range[0,255]")
	private String itemName;

	/**
	 * 種別
	 */
	@ApiModelProperty(value = "種別", required = false, allowableValues = "初期費用(\"1\"), 月額(\"2\")", example = "1", position = 13)
	private OsoCostType osoCostType;

	/**
	 * 自動売上
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "自動売上", required = false, position = 14, allowableValues = "range[0,9]")
	private Integer automaticSalesFlg;

	/**
	 * 数量
	 */
	@Max(99999)
	@Min(0)
	@ApiModelProperty(value = "数量", required = false, position = 15, allowableValues = "range[0,99999]")
	private Integer quantity;

	/**
	 * リコー仕切り価格
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@ApiModelProperty(value = "リコー仕切り価格", required = false, position = 16, allowableValues = "range[0.00,99999999999999999.99]")
	private BigDecimal ricohPartitionPrice;

	/**
	 * リコー売上価格
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@ApiModelProperty(value = "リコー売上価格", required = false, position = 17, allowableValues = "range[0.00,99999999999999999.99]")
	private BigDecimal ricohSalesPrice;

	/**
	 * 販社売上価格
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@ApiModelProperty(value = "販社売上価格", required = false, position = 18, allowableValues = "range[0.00,99999999999999999.99]")
	private BigDecimal salesCompanySalesPrice;

	/**
	 * 販売店売上価格
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@ApiModelProperty(value = "販売店売上価格", required = false, position = 19, allowableValues = "range[0.00,99999999999999999.99]")
	private BigDecimal dealerSalesPrice;

	/**
	 * 仕入先コード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "仕入先コード", required = false, position = 20, allowableValues = "range[0,255]")
	private String vendorCode;
}
