package jp.co.ricoh.cotos.commonlib.dto.json.contract;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumType.AccountFixFlg;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumType.ContractTypeDetails;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumType.NewExistingAccountType;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.AddMailDeleteFlg;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.OtherCorpProviderFlg;
import lombok.Data;

/**
 * 商品（契約用）拡張項目DTO（NSP）
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductContractExtendsParameterNspDto {

	@Description(value = "トレンド連携有無フラグ")
	public enum TrendLinkageFlg {

		なし("0"), あり("1");

		private final String text;

		private TrendLinkageFlg(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static TrendLinkageFlg fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * 契約種別詳細
	 */
	private ContractTypeDetails contractTypeDetails;

	/**
	 * トレンド連携有無フラグ
	 */
	private TrendLinkageFlg trendLinkageFlg;

	/**
	 * 新規/既存アカウント区分
	 */
	private NewExistingAccountType newExistingAccountType;

	/**
	 * MVBアカウント
	 */
	private String mvbAccount;

	/**
	 * インストールURL
	 */
	private String installUrl;

	/**
	 * アカウント確定フラグ
	 */
	private AccountFixFlg accountFixFlg;

	/**
	 * 他社プロバイダーフラグ
	 */
	private OtherCorpProviderFlg otherCorpProviderFlg;

	/**
	 * 回線種別区分
	 */
	private String lineTypeDiv;

	/**
	 * 回線種別名
	 */
	private String lineTypeName;

	/**
	 * 追加メール削除フラグ
	 */
	private AddMailDeleteFlg addMailDeleteFlg;

	/**
	 * 追加メール残数
	 */
	private Integer addMailRemainingNumber;

	/**
	 * PC台数
	 */
	private Integer pcAmount;

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
