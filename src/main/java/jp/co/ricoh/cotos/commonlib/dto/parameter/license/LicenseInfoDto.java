package jp.co.ricoh.cotos.commonlib.dto.parameter.license;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "RMA契約開始日", required = false, position = 5)
	private Date rmaContractStart;

	/**
	 * RMA契約終了日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "RMA契約終了日", required = false, position = 6)
	private Date rmaContractEnd;
}
