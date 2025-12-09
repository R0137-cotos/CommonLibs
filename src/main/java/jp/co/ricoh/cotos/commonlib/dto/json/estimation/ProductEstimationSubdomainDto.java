package jp.co.ricoh.cotos.commonlib.dto.json.estimation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.MailAttachedFileFlg;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.MailFlg;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.SubEditFlg;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.WebAddMenuDiv;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.WebFlg;
import lombok.Data;

/**
 * MWODの商品（見積用）.拡張項目 サブドメインDTO
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductEstimationSubdomainDto {

	/**
	 * サブドメインNo
	 */
	private String subNo;

	/**
	 * サブドメイン変更フラグ
	 */
	private SubEditFlg subEditFlg;

	/**
	 * サブドメインWebフラグ
	 */
	private WebFlg subWebFlg;

	/**
	 * サブドメインWeb追加メニュー区分
	 */
	private WebAddMenuDiv subWebAddMenuDiv;

	/**
	 * Webホスティング 追加容量数量(10GB) 
	 */
	private Integer subWebAddCapacityQuantity;

	/**
	 * サブドメインMailフラグ
	 */
	private MailFlg subMailFlg;

	/**
	 * Mailホスティング 添付ファイル暗号化フラグ
	 */
	private MailAttachedFileFlg subMailAttachedFileFlg;

	/**
	 * Mailホスティング 追加容量数量(10GB) 
	 */
	private Integer subMailAddCapacityQuantity;

	/**
	 * Mailホスティング アカウント追加 
	 */
	private Integer subMailAddAccountQuantity;
}
