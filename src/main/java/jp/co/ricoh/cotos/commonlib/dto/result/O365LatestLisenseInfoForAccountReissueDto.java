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
	 * ライセンス情報.ID
	 */
	@Id
	@ApiModelProperty(value = "ライセンス情報ID", required = true, position = 1)
	private String id;

	/**
	 * ライセンス情報.RJ管理番号
	 */
	@ApiModelProperty(value = "RJ管理番号", required = true, position = 2)
	private String rjManageNumber;

	/**
	 * 商品（契約用）.拡張項目.ドメイン名
	 */
	@ApiModelProperty(value = "サブドメイン名", required = true, position = 3)
	private String subDomainName;

	/**
	 * 顧客（契約用）.顧客名
	 */
	@ApiModelProperty(value = "お客様名", required = true, position = 4)
	private String customerName;
}
