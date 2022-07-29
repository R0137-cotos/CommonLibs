package jp.co.ricoh.cotos.commonlib.dto.result;

import org.springframework.data.annotation.Id;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 契約ID・アカウント再発行用情報を取得する為のDTO
 */
@Data
public class O365LatestLisenseInfoForAccountReissueDto {

	/**
	 * ライセンス情報ID
	 */
	@Id
	@ApiModelProperty(value = "ライセンス情報ID", required = false, position = 1)
	private String id;

	/**
	 * RJ管理番号
	 */
	@ApiModelProperty(value = "RJ管理番号", required = true, position = 2)
	private String rjManageNumber;

	/**
	 * サブドメイン名
	 */
	@ApiModelProperty(value = "サブドメイン名", required = true, position = 3)
	private String subDomainName;

	/**
	 * お客様名
	 */
	@ApiModelProperty(value = "お客様名", required = false, position = 4)
	private String customerName;
}
