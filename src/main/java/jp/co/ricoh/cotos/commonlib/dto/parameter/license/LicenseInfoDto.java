package jp.co.ricoh.cotos.commonlib.dto.parameter.license;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(value = "メールアドレス", required = true, position = 3, allowableValues = "range[0,255]")
	private String mailAddress;

	/**
	 * RMA契約番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "RMA契約番号", required = false, position = 4, allowableValues = "range[0,255]")
	private String rmaContractNumber;

	/**
	 * RMA契約開始日
	 */
	@JsonFormat(pattern = "yyyy/MM/dd", timezone = "Asia/Tokyo")
	@ApiModelProperty(value = "RMA契約開始日", required = false, position = 5)
	private Date rmaContractStart;

	/**
	 * RMA契約終了日
	 */
	@JsonFormat(pattern = "yyyy/MM/dd", timezone = "Asia/Tokyo")
	@ApiModelProperty(value = "RMA契約終了日", required = false, position = 6)
	private Date rmaContractEnd;

	/**
	 * オーガニゼーションID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "オーガニゼーションID", required = false, position = 7, allowableValues = "range[0,255]")
	private String organizationId;

	/**
	 * ライセンスアカウント
	 */
	@ApiModelProperty(value = "ライセンスアカウント", required = false, position = 8)
	@Valid
	private LicenseAccountDto licenseAccount;

}
