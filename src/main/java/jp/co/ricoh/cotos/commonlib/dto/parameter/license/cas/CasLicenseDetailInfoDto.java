package jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas;

import java.util.Date;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(value = "サブスクリプションID", required = true, position = 1, allowableValues = "range[0,25]")
	private String subscriptionId;

	/**
	 * サービスプランID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "サービスプランID", required = true, position = 2, allowableValues = "range[0,25]")
	private String servicePlanId;

	/**
	 * ライセンス状態
	 */
	@ApiModelProperty(value = "ライセンス状態", required = false, position = 3, allowableValues = "未確定(\"0\"), 有効(\"1\"), 解約(\"2\")")
	private CasLicenseStatus licenseStatus;

	/**
	 * ライセンス開始日
	 */
	@ApiModelProperty(value = "ライセンス開始日", required = false, position = 4)
	@Temporal(TemporalType.DATE)
	private Date licenseTermStart;

	/**
	 * ライセンス終了日
	 */
	@ApiModelProperty(value = "ライセンス終了日", required = false, position = 5)
	@Temporal(TemporalType.DATE)
	private Date licenseTermEnd;

	/**
	 * 数量
	 */
	@Min(0)
	@Max(99999)
	@ApiModelProperty(value = "数量", required = true, position = 6, allowableValues = "range[0,99999]")
	private int quantity;

	/**
	 * MVBアカウント
	 * CasLicenseBasicInfoDtoとの結合用フィールド
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "MVBアカウント", required = false, position = 7, allowableValues = "range[0,25]")
	private String mvbAccount;
}
