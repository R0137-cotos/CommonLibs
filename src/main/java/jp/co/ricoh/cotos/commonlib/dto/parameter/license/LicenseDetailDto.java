package jp.co.ricoh.cotos.commonlib.dto.parameter.license;

import java.util.Date;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "取込フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer captureFlg;

	/**
	 * 取込日時
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "取込日時", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date captureAt;

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
	 * 情報区分
	 */
	@Schema(description = "情報区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "新規(\"1\"), 減数(\"2\"), 増数(\"3\"), 情報変更(\"4\"), 解約(\"5\"), 乗換(\"6\"), 乗換え増数(\"7\"), 乗換え減数(\"8\"), 支払周期変更(\"9\")")
	private InfoDiv infoDiv;

}
