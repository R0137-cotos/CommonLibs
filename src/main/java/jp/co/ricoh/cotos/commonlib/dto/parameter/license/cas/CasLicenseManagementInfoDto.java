package jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas;

import java.util.Date;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "契約ID", required = false, allowableValues = "range[0,9223372036854775807]")
	private long contractId;

	/**
	 * RJ管理番号
	 */
	@Size(max = 255)
	@Schema(description = "RJ管理番号", required = true, allowableValues = "range[0,25]")
	private String rjManageNumber;

	/**
	 * 契約番号
	 */
	@Size(max = 255)
	@Schema(description = "契約番号", required = true, allowableValues = "range[0,25]")
	private String contractNumber;

	/**
	 * 契約番号枝番
	 */
	@Min(0)
	@Max(99)
	@Schema(description = "契約番号枝番", required = true, allowableValues = "range[0,99]")
	private int contractBranchNumber;

	/**
	 * 最終契約同期日付
	 */
	@Schema(description = "最終契約同期日付", required = false)
	@Temporal(TemporalType.DATE)
	private Date lastContractSyncDate;
	
	/**
	 * MVBアカウント
	 * CasLicenseBasicInfoDtoとの結合用フィールド
	 */
	@Size(max = 255)
	@Schema(description = "MVBアカウント", required = false, allowableValues = "range[0,25]")
	private String mvbAccount;
}
