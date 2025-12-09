package jp.co.ricoh.cotos.commonlib.dto.json.estimation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.DeleteFlg;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.NotEditFlg;
import lombok.Data;

/**
 * 商品（見積用）拡張項目 追加メールDTO
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductEstimationAddMailDto {

	/**
	 * No
	 */
	private Integer addMailNo;

	/**
	 * メールアドレス
	 */
	private String mailAddress;

	/**
	 * 削除フラグ
	 */
	private DeleteFlg deleteFlg;

	/**
	 * 編集不可フラグ
	 */
	private NotEditFlg notEditFlg;
}