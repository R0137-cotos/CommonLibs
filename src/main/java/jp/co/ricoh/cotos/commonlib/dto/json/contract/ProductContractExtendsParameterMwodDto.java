package jp.co.ricoh.cotos.commonlib.dto.json.contract;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumType.ContractTypeDetails;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.AddSubdomainFlg;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.MailAttachedFileFlg;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.MailFlg;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.WebAddMenuDiv;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.WebFlg;
import lombok.Data;

/**
 * 商品（契約用）拡張項目DTO（MWOD）
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductContractExtendsParameterMwodDto {
	/**
	 * 契約種別詳細
	 */
	private ContractTypeDetails contractTypeDetails;

	/**
	 * サブドメイン追加フラグ
	 */
	private AddSubdomainFlg addSubdomainFlg;

	/**
	 * サブドメイン数
	 */
	private Integer subdomainQuantity;

	/**
	 * Webフラグ
	 */
	private WebFlg webFlg;

	/**
	 * Web追加メニュー区分
	 */
	private WebAddMenuDiv webAddMenuDiv;

	/**
	 * Webホスティング 追加容量数量(10GB) 
	 */
	private Integer webAddCapacityQuantity;

	/**
	 * Mailフラグ
	 */
	private MailFlg mailFlg;

	/**
	 * Mailホスティング 添付ファイル暗号化フラグ
	 */
	private MailAttachedFileFlg mailAttachedFileFlg;

	/**
	 * Mailホスティング 追加容量数量(10GB)
	 */
	private Integer mailAddCapacityQuantity;

	/**
	 * Mailホスティング アカウント追加
	 */
	private Integer mailAddAccountQuantity;

	/**
	 * サブドメインリスト
	 */
	private List<ProductContractSubdomainDto> subdomainList;

	/**
	 * RtoR固有項目
	 */
	@JsonProperty("rtorSpecial")
	private ProductContractRtorSpecialMwodDto productContractRtorSpecialMwodDto;

	/**
	 * 移行用項目
	 */
	@JsonProperty("migrationParameter")
	private ProductContractMigrationParameterDto productContractMigrationParameterDto;
}
