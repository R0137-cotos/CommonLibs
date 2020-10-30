package jp.co.ricoh.cotos.commonlib.dto.parameter.license;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

/**
 * ライセンス関連のCSVを出力する条件のキー項目クラスを表します。
 */
@Data
public class LicenseCsvOutputParameter {

	/**
	 * 出力ファイル
	 */
	@ApiParam(value = "出力ファイル", required = false)
	@ApiModelProperty(value = "出力ファイル", required = false, position = 1)
	private String outputFileType;

	/**
	 * 契約状態
	 */
	@ApiParam(value = "契約状態", required = false)
	@ApiModelProperty(value = "契約状態<br />" //
			+ "状態遷移上のライフサイクル状態を表す。", //
			required = false, position = 2)
	private String lifecycleStatus;

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
	@ApiModelProperty(value = "出力対象", required = false, position = 4)
	private String outputTargetDiv;

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
