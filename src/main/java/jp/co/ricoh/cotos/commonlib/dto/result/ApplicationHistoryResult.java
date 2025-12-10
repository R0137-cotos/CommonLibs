package jp.co.ricoh.cotos.commonlib.dto.result;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "RJ管理番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String rjManageNumber;

	/**
	 * サブドメイン名(MSアカウント)
	 */
	@Schema(description = "サブドメイン名(MSアカウント)", required = false, allowableValues = "range[0,255]")
	private String subDomainName;

	/**
	 * 申込明細情報
	 */
	@Schema(description = "申込明細情報", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private List<ApplicationHistoryDto> applicationHistory;
}
