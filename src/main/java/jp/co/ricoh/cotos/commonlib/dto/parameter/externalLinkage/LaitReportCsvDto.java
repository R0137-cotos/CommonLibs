package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class LaitReportCsvDto {

	/**
	 * ファイル種別管理マスタID
	 */
	@Parameter(description = "ファイル種別管理マスタID", required = false)
	@Schema(description = "ファイル種別管理マスタID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private long fileKindManagementMasterId;

	/**
	 * 基準月
	 */
	@JsonFormat(pattern = "yyyy/MM", timezone = "Asia/Tokyo")
	@Parameter(description = "基準月", required = false)
	@Schema(description = "基準月", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date referenceMonth;

}
