package jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas;

import java.util.Date;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.DtoBase;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.CasLicenseStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * CASライセンス明細情報DTO
 * @author z00se03039
 *
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class CasLicenseDetailInfoDto extends DtoBase {

	/**
	 * サブスクリプションID
	 */
	@Size(max = 255)
	@Schema(description = "サブスクリプションID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,25]")
	private String subscriptionId;

	/**
	 * サービスプランID
	 */
	@Size(max = 255)
	@Schema(description = "サービスプランID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,25]")
	private String servicePlanId;

	/**
	 * ライセンス状態
	 */
	@Schema(description = "ライセンス状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未確定(\"0\"), 有効(\"1\"), 解約(\"2\")")
	private CasLicenseStatus licenseStatus;

	/**
	 * ライセンス開始日
	 */
	@Schema(description = "ライセンス開始日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.DATE)
	private Date licenseTermStart;

	/**
	 * ライセンス終了日
	 */
	@Schema(description = "ライセンス終了日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.DATE)
	private Date licenseTermEnd;

	/**
	 * 数量
	 */
	@Min(0)
	@Max(99999)
	@Schema(description = "数量", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,99999]")
	private int quantity;

	/**
	 * MVBアカウント
	 * CasLicenseBasicInfoDtoとの結合用フィールド
	 */
	@Size(max = 255)
	@Schema(description = "MVBアカウント", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,25]")
	private String mvbAccount;
}
