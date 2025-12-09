package jp.co.ricoh.cotos.commonlib.dto.result;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Version;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.RequestCreateStatus;
import jp.co.ricoh.cotos.commonlib.entity.license.LicenseDetail.HardSoftDiv;
import jp.co.ricoh.cotos.commonlib.entity.license.LicenseDetail.InfoDiv;
import jp.co.ricoh.cotos.commonlib.entity.license.LicenseDetail.RequestProcessDiv;
import lombok.Data;

@Entity
@Data
public class LicenseDetailResult {

	/**
	 * ライセンス明細ID
	 */
	@Id
	@Schema(description = "ライセンス明細ID(作成時不要)", required = true, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * ライセンス明細version
	 */
	@Version
	@Schema(description = "ライセンス明細version(作成時不要)", required = true, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long version;

	/**
	 * ライセンス区分マスタID
	 */
	@Schema(description = "ライセンス区分マスタID", required = true, allowableValues = "range[0,9223372036854775807]")
	private long licenseDivMasterId;

	/**
	 * シーケンスNo
	 */
	@Schema(description = "シーケンスNo", required = true, allowableValues = "range[0,9223372036854775807]")
	private long seqNumber;

	/**
	 * 情報区分
	 */
	@Schema(description = "情報区分", required = false, allowableValues = "ハード(\"1\"), 減数(\"2\")")
	private InfoDiv infoDiv;

	/**
	 * 品種マスタID
	 */
	@Schema(description = "品種マスタID", required = false, allowableValues = "range[0,9223372036854775807]")
	private Long itemMasterId;

	/**
	 * 商品マスタID
	 */
	@Schema(description = "商品マスタID", required = false, allowableValues = "range[0,9223372036854775807]")
	private Long productMasterId;

	/**
	 * ハード・ソフト区分
	 */
	@Schema(description = "ハード・ソフト区分", required = false, allowableValues = "ハード(\"1\"), ライセンス(\"2\")")
	private HardSoftDiv hardSoftDiv;

	/**
	 * 機種コード
	 */
	@Schema(description = "機種コード", required = false, allowableValues = "range[0,255]")
	private String equipmentCode;

	/**
	 * 数量
	 */
	@Schema(description = "数量", required = false, allowableValues = "range[0,99999]")
	private Integer quantity;

	/**
	 * 取込フラグ
	 */
	@Schema(description = "取込フラグ", required = false, allowableValues = "range[0,9]")
	private Integer captureFlg;

	/**
	 * 取込日時
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "取込日時", required = false)
	private Date captureAt;

	/**
	 * 拡張項目
	 */
	@Schema(description = "拡張項目", required = false)
	private String extendsParameter;

	/**
	 * 品種名
	 */
	@Schema(description = "品種名", required = false, allowableValues = "range[0,255]")
	private String itemName;

	/**
	 * ライセンスサービスID
	 */
	@Schema(description = "ライセンスサービスID", required = true, allowableValues = "range[0,255]")
	private String licenseServiceId;

	/**
	 * ライセンスサービス名
	 */
	@Schema(description = "ライセンスサービス名", required = true, allowableValues = "range[0,255]")
	private String licenseServiceName;

	/**
	 * 増減数量
	 */
	@Schema(description = "増減数量", required = false, allowableValues = "range[-99999,99999]")
	private Integer changeQuantity;

	/**
	 * リクエスト処理区分
	 */
	@Schema(description = "リクエスト処理区分", required = false, allowableValues = "即時(\"1\"), 月次(\"2\")")
	private RequestProcessDiv requestProcessDiv;

	/**
	 * リクエスト作成状態
	 */
	@Schema(description = "リクエスト作成状態", required = false, allowableValues = "未作成(\"0\"), 作成済(\"1\"), 対象外(\"2\")")
	private RequestCreateStatus requestCreateStatus;

	/**
	 * リクエスト作成日時
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "リクエスト作成日時", required = false)
	private Date requestCreateDate;

	/**
	 * ライセンス開始日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "ライセンス開始日", required = false)
	private Date licenseTermStart;

	/**
	 * ライセンス終了日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "ライセンス終了日", required = false)
	private Date licenseTermEnd;
}
