package jp.co.ricoh.cotos.commonlib.dto.parameter.license;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
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
	@Parameter(description = "ファイル種別管理マスタID", required = false)
	@Schema(description = "ファイル種別管理マスタID", required = false)
	private long fileKindManagementMasterId;

	/**
	 * 基準月
	 */
	@JsonFormat(pattern = "yyyy/MM", timezone = "Asia/Tokyo")
	@Parameter(description = "基準月", required = false)
	@Schema(description = "基準月", required = false)
	private Date referenceMonth;

	/**
	 * 出力対象
	 */
	@Parameter(description = "出力対象", required = false)
	@Schema(description = "出力対象", required = false, allowableValues = "全件(\"1\"), 全件_解約を除く(\"2\"), 出力対象月のみ(\"3\")")
	private CsvOutputTargetType csvOutputTargetType;

	/**
	 * 再出力
	 */
	@Parameter(description = "再出力", required = false)
	@Schema(description = "再出力", required = false)
	private int reoutputFlg;

	/**
	 * 出力開始日
	 */
	@JsonFormat(pattern = "yyyy/MM/dd", timezone = "Asia/Tokyo")
	@Parameter(description = "出力開始日", required = false)
	@Schema(description = "出力開始日", required = false)
	private Date outputDateFrom;

	/**
	 * 出力終了日
	 */
	@JsonFormat(pattern = "yyyy/MM/dd", timezone = "Asia/Tokyo")
	@Parameter(description = "出力終了日", required = false)
	@Schema(description = "出力終了日", required = false)
	private Date outputDateTo;

}
