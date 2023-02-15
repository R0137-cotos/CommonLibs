package jp.co.ricoh.cotos.commonlib.dto.json.estimation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumType.EstimationTypeDetails;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.HostingRtorFlg;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.MailRtorFlg;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.OtherCorpProviderFlg;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.RtorFlg;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.SajitsutenFlg;
import lombok.Data;

/**
 * 商品（見積用）拡張項目CPQ商品固有戻り値DTO（BPN・BPS）
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CpqReturnValueBpnBpsDto {

	/**
	 * 見積種別詳細
	 */
	private EstimationTypeDetails estimationTypeDetails;

	/**
	 * ホスティングRtoRフラグ
	 */
	private HostingRtorFlg hostingRtorFlg;

	/**
	 * メールアドレスRtoRフラグ
	 */
	private MailRtorFlg mailRtorFlg;

	/**
	 * RtoRフラグ
	 */
	private RtorFlg rtorFlg;

	/**
	 * 他社プロバイダーフラグ
	 */
	private OtherCorpProviderFlg otherCorpProviderFlg;

	/**
	 * サ実店フラグ
	 */
	private SajitsutenFlg sajitsutenFlg;

	/**
	 * スポーク拠点数
	 */
	private Integer spokeNumber;
}
