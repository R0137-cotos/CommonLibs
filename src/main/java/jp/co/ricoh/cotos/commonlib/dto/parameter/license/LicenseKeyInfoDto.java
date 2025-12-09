package jp.co.ricoh.cotos.commonlib.dto.parameter.license;

import java.util.Date;

import jakarta.persistence.Lob;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.DtoBase;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.LicenseStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class LicenseKeyInfoDto extends DtoBase {

	/**
	 * ライセンスサービスID
	 */
	@Size(max = 255)
	@Schema(description = "ライセンスサービスID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String licenseServiceId;

	/**
	 * ライセンスキー
	 */
	@Size(max = 255)
	@Schema(description = "ライセンスキー", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String licenseKey;

	/**
	 * ライセンス状態
	 */
	@Schema(description = "ライセンス状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未確定(\"0\"), 有効(\"1\"), 解約(\"2\")")
	private LicenseStatus licenseStatus;

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
	 * 数量
	 */
	@Max(99999)
	@Min(-99999)
	@Schema(description = "数量", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[-99999,99999]")
	private Integer quantity;

	/**
	 * 拡張項目
	 */
	@Schema(description = "拡張項目", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Lob
	private String extendsParameter;

	/**
	 * ライセンス区分マスタID
	 */
	@Min(0)
	@Schema(description = "ライセンス区分マスタID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private long licenseDivMasterId;

}
