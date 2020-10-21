package jp.co.ricoh.cotos.commonlib.dto.parameter.license;

import java.util.Date;

import org.springframework.data.annotation.Id;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.DtoBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class LicenseInfoParameter extends DtoBase {

	/**
	 * ライセンス情報ID
	 */
	@Id
	@ApiParam(value = "ライセンス情報ID", required = true)
	@ApiModelProperty(value = "ライセンス情報ID", required = true, allowableValues = "range[0,19]", position = 1)
	private long licenseInfoId;

	/**
	 * RMA契約番号
	 */
	@ApiParam(value = "RMA契約番号", required = false)
	@ApiModelProperty(value = "RMA契約開始日", required = false, position = 2)
	private String rmaContractNumber;

	/**
	 * RMA契約開始日
	 */
	@ApiParam(value = "RMA契約開始日", required = false)
	@ApiModelProperty(value = "RMA契約開始日<br />" //
			+ "日付フォーマット:yyyy/MM/dd", //
			required = false, position = 3)
	private Date rmaContractStart;

	/**
	 * RMA契約開始日
	 */
	@ApiParam(value = "RMA契約終了日", required = false)
	@ApiModelProperty(value = "RMA契約終了日<br />" //
			+ "日付フォーマット:yyyy/MM/dd", //
			required = false, position = 4)
	private Date rmaContractEnd;

	/**
	 * メールアドレス
	 */
	@ApiParam(value = "メールアドレス", required = false)
	@ApiModelProperty(value = "メールアドレス", required = false, allowableValues = "range[0,255]", position = 5)
	private String mailAddress;
}
