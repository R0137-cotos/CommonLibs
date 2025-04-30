package jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas;

import java.util.Date;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.DtoBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * CASライセンス管理情報DTO
 * @author z00se03039
 *
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class CasLicenseManagementInfoDto extends DtoBase {

	/**
	 * 契約ID
	 */
	@Min(0)
	@ApiModelProperty(value = "契約ID", required = false, position = 1, allowableValues = "range[0,9223372036854775807]")
	private long contractId;

	/**
	 * RJ管理番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "RJ管理番号", required = true, position = 2, allowableValues = "range[0,25]")
	private String rjManageNumber;

	/**
	 * 契約番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "契約番号", required = true, position = 3, allowableValues = "range[0,25]")
	private String contractNumber;

	/**
	 * 契約番号枝番
	 */
	@Min(0)
	@Max(99)
	@ApiModelProperty(value = "契約番号枝番", required = true, position = 4, allowableValues = "range[0,99]")
	private int contractBranchNumber;

	/**
	 * 最終契約同期日付
	 */
	@ApiModelProperty(value = "最終契約同期日付", required = false, position = 5)
	@Temporal(TemporalType.DATE)
	private Date lastContractSyncDate;
	
	/**
	 * MVBアカウント
	 * CasLicenseBasicInfoDtoとの結合用フィールド
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "MVBアカウント", required = false, position = 6, allowableValues = "range[0,25]")
	private String mvbAccount;
}
