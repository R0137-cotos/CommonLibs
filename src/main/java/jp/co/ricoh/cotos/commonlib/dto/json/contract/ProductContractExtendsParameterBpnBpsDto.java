package jp.co.ricoh.cotos.commonlib.dto.json.contract;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumType.ContractTypeDetails;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.AddMailDeleteFlg;
import lombok.Data;

/**
 * 商品（契約用）拡張項目DTO（BPN BPS）
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductContractExtendsParameterBpnBpsDto {
	/**
	 * 契約種別詳細
	 */
	private ContractTypeDetails contractTypeDetails;

	/**
	 * スポーク拠点数
	 */
	private Integer spokeNumber;

	/**
	 * 管理拠点RJ管理番号
	 */
	private String baseRjManageNumber;

	/**
	 * IT補助金初回課金開始日
	 */
	private String iTSubsidyFirstBillingStartDate;

	/**
	 * RtoR固有項目
	 */
	@JsonProperty("rtorSpecial")
	private ProductContractRtorSpecialBpnBpsDto productContractRtorSpecialBpnBpsDto;

	/**
	 * McAfee固有項目
	 */
	@JsonProperty("mcAfeeSpecial")
	private ProductContractMcAfeeSpecialDto productContractMcAfeeSpecialDto;

	/**
	 * 追加メール削除フラグ
	 */
	private AddMailDeleteFlg addMailDeleteFlg;

	/**
	 * 追加メール残数
	 */
	private Integer addMailRemainingNumber;

	/**
	 * 追加メールリスト
	 */
	@JsonProperty("addMail")
	private List<ProductContractAddMailDto> addMailList;

	/**
	 * 移行用項目
	 */
	@JsonProperty("migrationParameter")
	private ProductContractMigrationParameterDto productContractMigrationParameterDto;
}
