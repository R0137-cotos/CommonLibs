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

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "ライセンス明細洗い替え履歴ID(作成時不要)", allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * ライセンス明細ID
	 */
	@Column(nullable = false)
	@Min(0)
	@Schema(description = "ライセンス明細ID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private long licenseDetailId;

	/**
	 * ライセンス情報ID
	 */
	@Column(nullable = false)
	@Min(0)
	@Schema(description = "ライセンス情報ID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private long licenseInfoId;

	/**
	 * ライセンス区分マスタID
	 */
	@Column(nullable = false)
	@Min(0)
	@Schema(description = "ライセンス区分マスタID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private long licenseDivMasterId;

	/**
	 * シーケンスNo
	 */
	@Column(nullable = false)
	@Min(0)
	@Schema(description = "シーケンスNo", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private long seqNumber;

	/**
	 * 情報区分
	 */
	@Schema(description = "情報区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "新規(\"1\"), 減数(\"2\"), 増数(\"3\"), 情報変更(\"4\"), 解約(\"5\"), 乗換(\"6\"), 乗換え増数(\"7\"), 乗換え減数(\"8\"), 支払周期変更(\"9\")")
	private InfoDiv infoDiv;

	/**
	 * 品種マスタID
	 */
	@Min(0)
	@Schema(description = "品種マスタID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private Long itemMasterId;

	/**
	 * 商品マスタID
	 */
	@Min(0)
	@Schema(description = "商品マスタID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private Long productMasterId;

	/**
	 * ハード・ソフト区分
	 */
	@Schema(description = "ハード・ソフト区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "ハード(\"1\"), ライセンス(\"2\")")
	private HardSoftDiv hardSoftDiv;

	/**
	 * 機種コード
	 */
	@Size(max = 255)
	@Schema(description = "機種コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String equipmentCode;

	/**
	 * 数量
	 */
	@Max(99999)
	@Min(-99999)
	@Schema(description = "数量", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[-99999,99999]")
	private Integer quantity;

	/**
	 * 取込フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "取込フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer captureFlg;

	/**
	 * 取込日時
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "取込日時", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date captureAt;

	/**
	 * 拡張項目
	 */
	@Schema(description = "拡張項目", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Lob
	private String extendsParameter;

	/**
	 * ライセンスサービスID
	 */
	@Size(max = 255)
	@Schema(description = "ライセンスサービスID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String licenseServiceId;

	/**
	 * ライセンスサービス名
	 */
	@Size(max = 255)
	@Schema(description = "ライセンスサービス名", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String licenseServiceName;

	/**
	 * 増減数量
	 */
	@Max(99999)
	@Min(-99999)
	@Schema(description = "増減数量", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[-99999,99999]")
	private Integer changeQuantity;

	/**
	 * リクエスト処理区分
	 */
	@Schema(description = "リクエスト処理区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "即時(\"1\"), 月次(\"2\")")
	private RequestProcessDiv requestProcessDiv;

	/**
	 * リクエスト作成状態
	 */
	@Schema(description = "リクエスト作成状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未作成(\"0\"), 作成済(\"1\"), 対象外(\"2\")")
	private RequestCreateStatus requestCreateStatus;

	/**
	 * リクエスト作成日時
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "リクエスト作成日時", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date requestCreateDate;

	/**
	 * ライセンス開始日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "ライセンス開始日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date licenseTermStart;

	/**
	 * ライセンス終了日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "ライセンス終了日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date licenseTermEnd;

	/**
	 * 洗替日
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "洗替日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date refreshedAt;

}
