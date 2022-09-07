package jp.co.ricoh.cotos.commonlib.dto.result;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 申込明細情報をリスト取得するためのDtoです。<br/>
 * このクラスを使用してDBへの保存を行うことは出来ません。
 */
@Data
public class ApplicationHistoryDto {

	/**
	 * 注文番号
	 */
	@ApiModelProperty(value = "注文番号", required = false, position = 1, allowableValues = "range[0,255]")
	private String ordererNumber;

	/**
	 * COTOS文書番号
	 */
	@ApiModelProperty(value = "COTOS文書番号", required = false, position = 2, allowableValues = "range[0,255]")
	private String cotosDocumentNumber;

	/**
	 * 申込日
	 */
	@ApiModelProperty(value = "申込日", required = false, position = 3, allowableValues = "range[0,255]")
	private String applicationDate;

	/**
	 * 担当者氏名
	 */
	@ApiModelProperty(value = "担当者氏名", required = false, position = 4, allowableValues = "range[0,255]")
	private String picName;

	/**
	 * 担当者メールアドレス
	 */
	@ApiModelProperty(value = "担当者メールアドレス", required = false, position = 5, allowableValues = "range[0,255]")
	private String picMailAddress;

	/**
	 * キャンセル用ID
	 */
	@ApiModelProperty(value = "キャンセル用ID", required = false, position = 6, allowableValues = "range[0,255]")
	private String cancellationId;

	/**
	 * キャンセル申込日
	 */
	@ApiModelProperty(value = "キャンセル申込日", required = false, position = 7, allowableValues = "range[0,255]")
	private String cancellationApplicationDate;

	/**
	 * キャンセル可能フラグ
	 */
	@ApiModelProperty(value = "キャンセル可能フラグ", required = false, position = 8, allowableValues = "range[0,255]")
	private String cancellationFlg;

	/**
	 * キャンセル可能期日
	 */
	@ApiModelProperty(value = "キャンセル可能期日", required = false, position = 9, allowableValues = "range[0,255]")
	private String cancellationDate;

	/**
	 * 履歴詳細情報
	 */
	@ApiModelProperty(value = "履歴詳細情報", required = false, position = 10, allowableValues = "range[0,255]")
	private List<HistoryDetailDto> historyDetail;
}
