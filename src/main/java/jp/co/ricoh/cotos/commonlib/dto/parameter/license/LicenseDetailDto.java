package jp.co.ricoh.cotos.commonlib.dto.parameter.license;

import java.util.Date;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.DtoBase;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.RequestCreateStatus;
import jp.co.ricoh.cotos.commonlib.entity.license.LicenseDetail.InfoDiv;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class LicenseDetailDto extends DtoBase {

	/**
	 * 取込フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "取込フラグ", required = false, position = 3, allowableValues = "range[0,9]")
	private Integer captureFlg;

	/**
	 * 取込日時
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@ApiModelProperty(value = "取込日時", required = false, position = 4)
	private Date captureAt;

	/**
	 * リクエスト作成状態
	 */
	@ApiModelProperty(value = "リクエスト作成状態", required = false, allowableValues = "未作成(\"0\"), 作成済(\"1\"), 対象外(\"2\")", position = 5)
	private RequestCreateStatus requestCreateStatus;

	/**
	 * リクエスト作成日時
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@ApiModelProperty(value = "リクエスト作成日時", required = false, position = 6)
	private Date requestCreateDate;

	/**
	 * ライセンス開始日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "ライセンス開始日", required = false, position = 7)
	private Date licenseTermStart;

	/**
	 * ライセンス終了日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "ライセンス終了日", required = false, position = 8)
	private Date licenseTermEnd;

	/**
	 * 情報区分
	 */
	@ApiModelProperty(value = "情報区分", required = false, allowableValues = "新規(\"1\"), 減数(\"2\"), 増数(\"3\"), 情報変更(\"4\"), 解約(\"5\"), 乗換(\"6\"), 乗換え増数(\"7\"), 乗換え減数(\"8\"), 支払周期変更(\"9\")", position = 9)
	private InfoDiv infoDiv;

}
