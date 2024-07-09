package jp.co.ricoh.cotos.commonlib.entity.common;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.common.VMailAddressList.TableType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
@Data
public class VMailAddressListAbstractEntity {

	@Id
	@ApiModelProperty(value = "トランザクションID", required = true, position = 1, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * テーブル区分
	 */
	@ApiModelProperty(value = "テーブル区分", required = true, position = 3, allowableValues = "担当SA(\"1\"), 担当編集者(\"2\"), 担当CE(\"3\"), 販売店(\"4\"), 顧客(\"5\"), 担当作業者(\"6\"), 導入CE(\"7\"), 保守CE(\"8\"), 大塚商会連絡用アドレス(\"101\"), OR年額_大塚商会連絡用アドレス(\"102\"), OR年額_OR営本グループアドレス(\"103\"), EDW_年額更新時期案内_不着連絡用メールアドレス(\"104\"), 脱RITOS更新案内_BBC宛先用メールアドレス(\"105\"), SIM用固定メールアドレス(\"300\"), 担当SA_追加編集者(\"1002\"), 担当SA_導入担当CE_追加編集者(\"9\"), 商品担当区グループアドレス(\"10\"), 担当SA上長(\"1003\"), 承認者_代理承認者(\"APPROVER\"), 承認依頼者(\"APPROVAL_REQUESTER\"), 追加編集者_担当SA上長_導入担当CE_(\"13\"), SSec不達連絡用メールアドレス(\"14\"), 担当SA_追加編集者_リコーひかり業務(\"400\"), 担当SA_追加編集者_担当CE(\"20\"), 受付CE_導入CE(\"17\"), 受付CE_保守CE(\"18\"), 受付CE_SA上長_追加編集者(\"19\")", example = "1")
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
