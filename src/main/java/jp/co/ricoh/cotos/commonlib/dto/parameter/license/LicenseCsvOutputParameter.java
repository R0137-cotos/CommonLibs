package jp.co.ricoh.cotos.commonlib.dto.parameter.license;

import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.CsvOutputTargetType;
import lombok.Data;

/**
 * ライセンス関連のCSVを出力する条件のキー項目クラスを表します。
 */
@Data
public class LicenseCsvOutputParameter {

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

	/**
	 * 出力対象
	 */
	@ApiParam(value = "出力対象", required = false)
	@ApiModelProperty(value = "出力対象", required = false, allowableValues = "全件(\"1\"), 全件_解約を除く(\"2\"), 出力対象月のみ(\"3\")", position = 3)
	private CsvOutputTargetType csvOutputTargetType;

	/**
	 * 再出力
	 */
	@ApiParam(value = "再出力", required = false)
	@ApiModelProperty(value = "再出力", required = false, position = 5)
	private int reoutputFlg;

	/**
	 * 出力開始日
	 */
	@JsonFormat(pattern = "yyyy/MM/dd", timezone = "Asia/Tokyo")
	@ApiParam(value = "出力開始日", required = false)
	@ApiModelProperty(value = "出力開始日", required = false, position = 6)
	private LocalDate outputDateFrom;

	/**
	 * 出力終了日
	 */
	@JsonFormat(pattern = "yyyy/MM/dd", timezone = "Asia/Tokyo")
	@ApiParam(value = "出力終了日", required = false)
	@ApiModelProperty(value = "出力終了日", required = false, position = 7)
	private LocalDate outputDateTo;

}
