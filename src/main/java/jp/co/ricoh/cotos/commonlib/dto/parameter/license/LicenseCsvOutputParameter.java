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
	@Parameter(description = "ファイル種別管理マスタID")
	@Schema(description = "ファイル種別管理マスタID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private long fileKindManagementMasterId;

	/**
	 * 基準月
	 */
	@JsonFormat(pattern = "yyyy/MM", timezone = "Asia/Tokyo")
	@Parameter(description = "基準月")
	@Schema(description = "基準月", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date referenceMonth;

	/**
	 * 出力対象
	 */
	@Parameter(description = "出力対象")
	@Schema(description = "出力対象", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "全件(\"1\"), 全件_解約を除く(\"2\"), 出力対象月のみ(\"3\")")
	private CsvOutputTargetType csvOutputTargetType;

	/**
	 * 再出力
	 */
	@Parameter(description = "再出力")
	@Schema(description = "再出力", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private int reoutputFlg;

	/**
	 * 出力開始日
	 */
	@JsonFormat(pattern = "yyyy/MM/dd", timezone = "Asia/Tokyo")
	@Parameter(description = "出力開始日")
	@Schema(description = "出力開始日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date outputDateFrom;

	/**
	 * 出力終了日
	 */
	@JsonFormat(pattern = "yyyy/MM/dd", timezone = "Asia/Tokyo")
	@Parameter(description = "出力終了日")
	@Schema(description = "出力終了日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date outputDateTo;

}
