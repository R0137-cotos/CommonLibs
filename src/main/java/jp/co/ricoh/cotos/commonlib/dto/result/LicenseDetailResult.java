package jp.co.ricoh.cotos.commonlib.dto.result;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.DtoBase;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.RequestCreateStatus;
import jp.co.ricoh.cotos.commonlib.entity.license.LicenseDetail.HardSoftDiv;
import jp.co.ricoh.cotos.commonlib.entity.license.LicenseDetail.InfoDiv;
import jp.co.ricoh.cotos.commonlib.entity.license.LicenseDetail.RequestProcessDiv;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Entity
@Data
public class LicenseDetailResult extends DtoBase {

	/**
	 * ライセンス区分マスタID
	 */
	@ApiModelProperty(value = "ライセンス区分マスタID", required = true, position = 2, allowableValues = "range[0,9223372036854775807]")
	private long licenseDivMasterId;

	/**
	 * シーケンスNo
	 */
	@ApiModelProperty(value = "シーケンスNo", required = true, position = 3, allowableValues = "range[0,9223372036854775807]")
	private long seqNumber;

	/**
	 * 情報区分
	 */
	@ApiModelProperty(value = "情報区分", required = false, allowableValues = "ハード(\"1\"), 減数(\"2\")", position = 4)
	private InfoDiv infoDiv;

	/**
	 * 品種マスタID
	 */
	@ApiModelProperty(value = "品種マスタID", required = false, position = 5, allowableValues = "range[0,9223372036854775807]")
	private Long itemMasterId;

	/**
	 * 商品マスタID
	 */
	@ApiModelProperty(value = "商品マスタID", required = false, position = 6, allowableValues = "range[0,9223372036854775807]")
	private Long productMasterId;

	/**
	 * ハード・ソフト区分
	 */
	@ApiModelProperty(value = "ハード・ソフト区分", required = false, allowableValues = "ハード(\"1\"), ライセンス(\"2\")", position = 7)
	private HardSoftDiv hardSoftDiv;

	/**
	 * 機種コード
	 */
	@ApiModelProperty(value = "機種コード", required = false, position = 8, allowableValues = "range[0,255]")
	private String equipmentCode;

	/**
	 * 数量
	 */
	@ApiModelProperty(value = "数量", required = false, position = 9, allowableValues = "range[0,99999]")
	private Integer quantity;

	/**
	 * 取込フラグ
	 */
	@ApiModelProperty(value = "取込フラグ", required = false, position = 10, allowableValues = "range[0,9]")
	private Integer captureFlg;

	/**
	 * 取込日時
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@ApiModelProperty(value = "取込日時", required = false, position = 11)
	private Date captureAt;

	/**
	 * 拡張項目
	 */
	@ApiModelProperty(value = "拡張項目", required = false, position = 12)
	private String extendsParameter;

	/**
	 * 品種名
	 */
	@ApiModelProperty(value = "品種名", required = false, position = 13, allowableValues = "range[0,255]")
	private String itemName;

	/**
	 * ライセンスサービスID
	 */
	@ApiModelProperty(value = "ライセンスサービスID", required = true, position = 14, allowableValues = "range[0,255]")
	private String licenseServiceId;

	/**
	 * ライセンスサービス名
	 */
	@ApiModelProperty(value = "ライセンスサービス名", required = true, position = 15, allowableValues = "range[0,255]")
	private String licenseServiceName;

	/**
	 * 増減数量
	 */
	@ApiModelProperty(value = "増減数量", required = false, position = 16, allowableValues = "range[-99999,99999]")
	private Integer changeQuantity;

	/**
	 * リクエスト処理区分
	 */
	@ApiModelProperty(value = "リクエスト処理区分", required = false, allowableValues = "即時(\"1\"), 月次(\"2\")", position = 17)
	private RequestProcessDiv requestProcessDiv;

	/**
	 * リクエスト作成状態
	 */
	@ApiModelProperty(value = "リクエスト作成状態", required = false, allowableValues = "未作成(\"0\"), 作成済(\"1\"), 対象外(\"2\")", position = 18)
	private RequestCreateStatus requestCreateStatus;

	/**
	 * リクエスト作成日時
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@ApiModelProperty(value = "リクエスト作成日時", required = false, position = 19)
	private Date requestCreateDate;

	/**
	 * ライセンス開始日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "ライセンス開始日", required = false, position = 20)
	private Date licenseTermStart;

	/**
	 * ライセンス終了日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "ライセンス終了日", required = false, position = 21)
	private Date licenseTermEnd;
}
