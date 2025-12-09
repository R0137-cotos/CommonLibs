package jp.co.ricoh.cotos.commonlib.dto.json.estimation;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumType.EstimationTypeDetails;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.AddSubdomainFlg;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.DomainRtorFlg;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.HostingRtorFlg;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.MailAttachedFileFlg;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.MailFlg;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.RtorFlg;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.WebAddMenuDiv;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.WebFlg;
import lombok.Data;

/**
 * 商品（見積用）拡張項目CPQ商品固有戻り値DTO（MWOD）
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CpqReturnValueMwodDto {

	/**
	 * 見積種別詳細
	 */
	private EstimationTypeDetails estimationTypeDetails;

	/**
	 * ホスティングRtoRフラグ
	 */
	private HostingRtorFlg hostingRtorFlg;

	/**
	 * ドメインRtoRフラグ
	 */
	private DomainRtorFlg domainRtorFlg;

	/**
	 * RtoRフラグ
	 */
	private RtorFlg rtorFlg;

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
	private List<ProductEstimationSubdomainDto> subdomainList;
}
