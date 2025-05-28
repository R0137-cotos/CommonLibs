package jp.co.ricoh.cotos.commonlib.entity.license;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.RequestCreateStatus;
import jp.co.ricoh.cotos.commonlib.entity.license.LicenseDetail.HardSoftDiv;
import jp.co.ricoh.cotos.commonlib.entity.license.LicenseDetail.InfoDiv;
import jp.co.ricoh.cotos.commonlib.entity.license.LicenseDetail.RequestProcessDiv;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ライセンス明細洗い替え履歴を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "license_detail_refresh_his")
public class LicenseDetailRefreshHis extends EntityBase {

	/**
	 * ライセンス明細洗い替え履歴ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "license_detail_refresh_his_seq")
	@SequenceGenerator(name = "license_detail_refresh_his_seq", sequenceName = "license_detail_refresh_his_seq", allocationSize = 1)
	@ApiModelProperty(value = "ライセンス明細洗い替え履歴ID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * ライセンス明細ID
	 */
	@Column(nullable = false)
	@Min(0)
	@ApiModelProperty(value = "ライセンス明細ID", required = true, position = 2, allowableValues = "range[0,9223372036854775807]")
	private long licenseDetailId;

	/**
	 * ライセンス情報ID
	 */
	@Column(nullable = false)
	@Min(0)
	@ApiModelProperty(value = "ライセンス情報ID", required = true, position = 3, allowableValues = "range[0,9223372036854775807]")
	private long licenseInfoId;

	/**
	 * ライセンス区分マスタID
	 */
	@Column(nullable = false)
	@Min(0)
	@ApiModelProperty(value = "ライセンス区分マスタID", required = true, position = 4, allowableValues = "range[0,9223372036854775807]")
	private long licenseDivMasterId;

	/**
	 * シーケンスNo
	 */
	@Column(nullable = false)
	@Min(0)
	@ApiModelProperty(value = "シーケンスNo", required = true, position = 5, allowableValues = "range[0,9223372036854775807]")
	private long seqNumber;

	/**
	 * 情報区分
	 */
	@ApiModelProperty(value = "情報区分", required = false, allowableValues = "新規(\"1\"), 減数(\"2\"), 増数(\"3\"), 情報変更(\"4\"), 解約(\"5\"), 乗換(\"6\"), 乗換え増数(\"7\"), 乗換え減数(\"8\"), 支払周期変更(\"9\")", position = 6)
	private InfoDiv infoDiv;

	/**
	 * 品種マスタID
	 */
	@Min(0)
	@ApiModelProperty(value = "品種マスタID", required = false, position = 7, allowableValues = "range[0,9223372036854775807]")
	private Long itemMasterId;

	/**
	 * 商品マスタID
	 */
	@Min(0)
	@ApiModelProperty(value = "商品マスタID", required = false, position = 8, allowableValues = "range[0,9223372036854775807]")
	private Long productMasterId;

	/**
	 * ハード・ソフト区分
	 */
	@ApiModelProperty(value = "ハード・ソフト区分", required = false, allowableValues = "ハード(\"1\"), ライセンス(\"2\")", position = 9)
	private HardSoftDiv hardSoftDiv;

	/**
	 * 機種コード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "機種コード", required = false, position = 10, allowableValues = "range[0,255]")
	private String equipmentCode;

	/**
	 * 数量
	 */
	@Max(99999)
	@Min(-99999)
	@ApiModelProperty(value = "数量", required = false, position = 11, allowableValues = "range[-99999,99999]")
	private Integer quantity;

	/**
	 * 取込フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "取込フラグ", required = false, position = 12, allowableValues = "range[0,9]")
	private Integer captureFlg;

	/**
	 * 取込日時
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@ApiModelProperty(value = "取込日時", required = false, position = 13)
	private Date captureAt;

	/**
	 * 拡張項目
	 */
	@ApiModelProperty(value = "拡張項目", required = false, position = 14)
	@Lob
	private String extendsParameter;

	/**
	 * ライセンスサービスID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "ライセンスサービスID", required = true, position = 15, allowableValues = "range[0,255]")
	private String licenseServiceId;

	/**
	 * ライセンスサービス名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "ライセンスサービス名", required = true, position = 16, allowableValues = "range[0,255]")
	private String licenseServiceName;

	/**
	 * 増減数量
	 */
	@Max(99999)
	@Min(-99999)
	@ApiModelProperty(value = "増減数量", required = false, position = 17, allowableValues = "range[-99999,99999]")
	private Integer changeQuantity;

	/**
	 * リクエスト処理区分
	 */
	@ApiModelProperty(value = "リクエスト処理区分", required = false, allowableValues = "即時(\"1\"), 月次(\"2\")", position = 18)
	private RequestProcessDiv requestProcessDiv;

	/**
	 * リクエスト作成状態
	 */
	@ApiModelProperty(value = "リクエスト作成状態", required = false, allowableValues = "未作成(\"0\"), 作成済(\"1\"), 対象外(\"2\")", position = 19)
	private RequestCreateStatus requestCreateStatus;

	/**
	 * リクエスト作成日時
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@ApiModelProperty(value = "リクエスト作成日時", required = false, position = 20)
	private Date requestCreateDate;

	/**
	 * ライセンス開始日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "ライセンス開始日", required = false, position = 21)
	private Date licenseTermStart;

	/**
	 * ライセンス終了日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "ライセンス終了日", required = false, position = 22)
	private Date licenseTermEnd;

	/**
	 * 洗替日
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@ApiModelProperty(value = "洗替日", required = false, position = 23)
	private Date refreshedAt;

}
