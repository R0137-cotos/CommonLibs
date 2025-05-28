package jp.co.ricoh.cotos.commonlib.dto.parameter.license;

import java.util.Date;

import jakarta.persistence.Lob;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(value = "ライセンスサービスID", required = true, position = 3, allowableValues = "range[0,255]")
	private String licenseServiceId;

	/**
	 * ライセンスキー
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "ライセンスキー", required = false, position = 4, allowableValues = "range[0,255]")
	private String licenseKey;

	/**
	 * ライセンス状態
	 */
	@ApiModelProperty(value = "ライセンス状態", required = false, position = 5, allowableValues = "未確定(\"0\"), 有効(\"1\"), 解約(\"2\")")
	private LicenseStatus licenseStatus;

	/**
	 * ライセンス開始日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "ライセンス開始日", required = false, position = 6)
	private Date licenseTermStart;

	/**
	 * ライセンス終了日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "ライセンス終了日", required = false, position = 7)
	private Date licenseTermEnd;

	/**
	 * 数量
	 */
	@Max(99999)
	@Min(-99999)
	@ApiModelProperty(value = "数量", required = false, position = 8, allowableValues = "range[-99999,99999]")
	private Integer quantity;

	/**
	 * 拡張項目
	 */
	@ApiModelProperty(value = "拡張項目", required = false, position = 9)
	@Lob
	private String extendsParameter;

	/**
	 * ライセンス区分マスタID
	 */
	@Min(0)
	@ApiModelProperty(value = "ライセンス区分マスタID", required = false, position = 10, allowableValues = "range[0,9223372036854775807]")
	private long licenseDivMasterId;

}
