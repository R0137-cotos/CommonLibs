package jp.co.ricoh.cotos.commonlib.entity.common;

import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.ServiceCategory;
import lombok.Data;

/**
 * メールアドレス情報を表すEntity
 */
@Entity
@Data
@Table(name = "v_mail_address_list")
public class VMailAddressList {

	@Description(value = "テーブル区分")
	public enum TableType {

		担当SA("1"), 担当編集者("2"), 担当CE("3"), 販売店("4"), 顧客("5"), 担当作業者("6"), 導入CE("7"), 保守CE("8"), 大塚商会連絡用アドレス("101"), OR年額_大塚商会連絡用アドレス("102"), OR年額_OR営本グループアドレス("103"), EDW_年額更新時期案内_不着連絡用メールアドレス("104"), 脱RITOS更新案内_BBC宛先用メールアドレス("105"), O365事務局_BCC宛先用メールアドレス("106"), 異動情報取込手配リマインド_TO宛先用メールアドレス("107"), 異動情報取込手配リマインド_BCC宛先用メールアドレス("108"), SIM用固定メールアドレス("300"), 担当SA_追加編集者("1002"), 担当SA_導入担当CE_追加編集者("9"), 商品担当区グループアドレス("10"), 担当SA上長("1003"), 承認者_代理承認者("APPROVER"), 承認依頼者("APPROVAL_REQUESTER"), 担当SA_販売店("11"), 販売店_更新案内チェックあり("12"), 追加編集者_担当SA上長_導入担当CE_("13"), SSec不達連絡用メールアドレス("14"), 担当作業者_担当CE("15"), 担当SA_追加編集者_リコーひかり業務("400"), 受付導入保守CE("16");

		private final String text;

		private TableType(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static TableType fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}

	}

	@Id
	@ApiModelProperty(value = "トランザクションID", required = true, position = 1, allowableValues = "range[0,9999999999999999999]")
	private long id;
	/**
	 * ドメイン区分
	 */
	@ApiModelProperty(value = "ドメイン区分", required = true, allowableValues = "見積(\"1\"), 契約(\"2\"), 手配(\"3\")", example = "1", position = 2)
	private ServiceCategory domainType;

	/**
	 * テーブル区分
	 */
	@ApiModelProperty(value = "テーブル区分", required = true, position = 3, allowableValues = "担当SA(\"1\"), 担当編集者(\"2\"), 担当CE(\"3\"), 販売店(\"4\"), 顧客(\"5\"), 担当作業者(\"6\"), 導入CE(\"7\"), 保守CE(\"8\"), 大塚商会連絡用アドレス(\"101\"), OR年額_大塚商会連絡用アドレス(\"102\"), OR年額_OR営本グループアドレス(\"103\"), EDW_年額更新時期案内_不着連絡用メールアドレス(\"104\"), 脱RITOS更新案内_BBC宛先用メールアドレス(\"105\"), O365事務局_BCC宛先用メールアドレス(\"106\"), 異動情報取込手配リマインド_TO宛先用メールアドレス(\"107\"), 異動情報取込手配リマインド_BCC宛先用メールアドレス(\"108\"), SIM用固定メールアドレス(\"300\"), 担当SA_追加編集者(\"1002\"), 担当SA_導入担当CE_追加編集者(\"9\"), 商品担当区グループアドレス(\"10\"), 担当SA上長(\"1003\"), 承認者_代理承認者(\"APPROVER\"), SSec不達連絡用メールアドレス(\"14\"), 担当作業者_担当CE(\"15\"), 担当SA_追加編集者_リコーひかり業務(\"400\"), 受付導入保守CE(\"16\")", example = "1")
	private TableType tableType;

	/**
	 * トランザクションID
	 */
	@ApiModelProperty(value = "トランザクションID", required = true, position = 4, allowableValues = "range[0,9999999999999999999]")
	private long transactionId;

	/**
	 * メールアドレス
	 */
	@ApiModelProperty(value = "メールアドレス", required = false, position = 5, allowableValues = "range[0,255]")
	private String mailAddress;
}
