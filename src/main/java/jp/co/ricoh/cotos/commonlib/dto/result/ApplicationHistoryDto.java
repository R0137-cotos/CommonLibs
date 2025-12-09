package jp.co.ricoh.cotos.commonlib.dto.result;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "注文番号", required = false, allowableValues = "range[0,255]")
	private String ordererNumber;

	/**
	 * COTOS文書番号
	 */
	@Schema(description = "COTOS文書番号", required = false, allowableValues = "range[0,255]")
	private String cotosDocumentNumber;

	/**
	 * 申込日
	 */
	@Schema(description = "申込日", required = false, allowableValues = "range[0,255]")
	private String applicationDate;

	/**
	 * 担当者氏名
	 */
	@Schema(description = "担当者氏名", required = false, allowableValues = "range[0,255]")
	private String picName;

	/**
	 * 担当者メールアドレス
	 */
	@Schema(description = "担当者メールアドレス", required = false, allowableValues = "range[0,255]")
	private String picMailAddress;

	/**
	 * キャンセル用ID
	 */
	@Schema(description = "キャンセル用ID", required = false, allowableValues = "range[0,255]")
	private String cancellationId;

	/**
	 * キャンセル申込日
	 */
	@Schema(description = "キャンセル申込日", required = false, allowableValues = "range[0,255]")
	private String cancellationApplicationDate;

	/**
	 * キャンセル可能フラグ
	 */
	@Schema(description = "キャンセル可能フラグ", required = false, allowableValues = "range[0,255]")
	private String cancellationFlg;

	/**
	 * キャンセル可能期日
	 */
	@Schema(description = "キャンセル可能期日", required = false, allowableValues = "range[0,255]")
	private String cancellationDate;

	/**
	 * 履歴詳細情報
	 */
	@Schema(description = "履歴詳細情報", required = false, allowableValues = "range[0,255]")
	private List<HistoryDetailDto> historyDetail;
}
