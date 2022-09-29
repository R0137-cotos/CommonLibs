package jp.co.ricoh.cotos.commonlib.dto.result;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 申込履歴を返却するためのDtoです。<br/>
 * このクラスを使用してDBへの保存を行うことは出来ません。
 */
@Data
public class ApplicationHistoryResult {

	/**
	 * RJ管理番号
	 */
	@ApiModelProperty(value = "RJ管理番号", required = false, position = 1, allowableValues = "range[0,255]")
	private String rjManageNumber;

	/**
	 * サブドメイン名(MSアカウント)
	 */
	@ApiModelProperty(value = "サブドメイン名(MSアカウント)", required = false, position = 2, allowableValues = "range[0,255]")
	private String subDomainName;

	/**
	 * 申込明細情報
	 */
	@ApiModelProperty(value = "申込明細情報", required = false, position = 3, allowableValues = "range[0,255]")
	private List<ApplicationHistoryDto> applicationHistory;
}
