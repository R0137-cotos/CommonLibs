package jp.co.ricoh.cotos.commonlib.dto.json.contract;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumType.ContractTypeDetails;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.AddMailDeleteFlg;
import lombok.Data;

/**
 * 商品（契約用）拡張項目DTO（BPSS）
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductContractExtendsParameterBpssDto {

	/**
	 * 契約種別詳細
	 */
	private ContractTypeDetails contractTypeDetails;

	/**
	 * 回線種別区分
	 */
	private String lineTypeDiv;

	/**
	 * 回線種別名
	 */
	private String lineTypeName;

	/**
	 * RtoR固有項目
	 */
	@JsonProperty("rtorSpecial")
	private ProductContractRtorSpecialBpssDto productContractRtorSpecialBpssDto;

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
