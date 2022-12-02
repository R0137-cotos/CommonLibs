package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class LaitReportCsvDto {

	/**
	 * ファイル種別管理マスタID
	 */
	@ApiParam(value = "ファイル種別管理マスタID", required = false)
	@ApiModelProperty(value = "ファイル種別管理マスタID", required = false, position = 1)
	private long fileKindManagementMasterId;

	/**
	 * 基準月
	 */
	@JsonFormat(pattern = "yyyy/MM", timezone = "Asia/Tokyo")
	@ApiParam(value = "基準月", required = false)
	@ApiModelProperty(value = "基準月", required = false, position = 2)
	private Date referenceMonth;

}
