package jp.co.ricoh.cotos.commonlib.dto.parameter.license;

import java.util.Date;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.DtoBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class LicenseInfoDto extends DtoBase {

	/**
	 * メールアドレス
	 */
	@NotNull
	@Size(max = 255)
	@Schema(description = "メールアドレス", required = true, allowableValues = "range[0,255]")
	private String mailAddress;

	/**
	 * RMA契約番号
	 */
	@Size(max = 255)
	@Schema(description = "RMA契約番号", required = false, allowableValues = "range[0,255]")
	private String rmaContractNumber;

	/**
	 * RMA契約開始日
	 */
	@JsonFormat(pattern = "yyyy/MM/dd", timezone = "Asia/Tokyo")
	@Schema(description = "RMA契約開始日", required = false)
	private Date rmaContractStart;

	/**
	 * RMA契約終了日
	 */
	@JsonFormat(pattern = "yyyy/MM/dd", timezone = "Asia/Tokyo")
	@Schema(description = "RMA契約終了日", required = false)
	private Date rmaContractEnd;

	/**
	 * オーガニゼーションID
	 */
	@Size(max = 255)
	@Schema(description = "オーガニゼーションID", required = false, allowableValues = "range[0,255]")
	private String organizationId;

	/**
	 * ライセンスアカウント
	 */
	@Schema(description = "ライセンスアカウント", required = false)
	@Valid
	private LicenseAccountDto licenseAccount;

	/**
	 * ライセンス明細
	 */
	@Schema(description = "ライセンス明細", required = false)
	@Valid
	private List<LicenseDetailDto> licenseDetailList;
}
