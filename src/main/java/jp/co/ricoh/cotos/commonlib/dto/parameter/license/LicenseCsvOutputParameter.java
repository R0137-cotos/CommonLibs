package jp.co.ricoh.cotos.commonlib.dto.parameter.license;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.CsvOutputTargetType;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.LifecycleStatus;
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
	 * 契約状態
	 */
	@ApiParam(value = "契約状態", required = false)
	@ApiModelProperty(value = "契約状態<br />" //
			+ "状態遷移上のライフサイクル状態を表す。", //
			required = false, allowableValues = "作成中(\"1\"), 作成完了(\"2\"), キャンセル手続き中(\"3\"), " //
					+ "破棄(\"4\"), 予定日待ち(\"5\"), 締結中(\"6\"), 解約手続き中(\"7\"), 解約予定日待ち(\"8\"), 解約(\"9\"), 旧契約(\"10\")", position = 2)
	private LifecycleStatus lifecycleStatus;

	/**
	 * 基準月
	 */
	@ApiParam(value = "基準月", required = false)
	@ApiModelProperty(value = "基準月", required = false, position = 3)
	private Date referenceMonth;

	/**
	 * 出力対象
	 */
	@ApiParam(value = "出力対象", required = false)
	@ApiModelProperty(value = "出力対象", required = false, allowableValues = "全件(\"1\"), 全件_解約を除く(\"2\"), 出力対象月のみ(\"3\")", position = 4)
	private CsvOutputTargetType csvOutputTargetType;

	/**
	 * 出力日
	 */
	@ApiParam(value = "出力日", required = false)
	@ApiModelProperty(value = "出力日", required = false, position = 5)
	private Date outputDate;

	/**
	 * 再出力
	 */
	@ApiParam(value = "再出力", required = false)
	@ApiModelProperty(value = "再出力", required = false, position = 6)
	private int reoutputFlg;
}
