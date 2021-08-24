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
	@ApiModelProperty(value = "テーブル区分", required = true, position = 3, allowableValues = "担当SA(\"1\"), 担当編集者(\"2\"), 担当CE(\"3\"), 販売店(\"4\"), 顧客(\"5\"), 担当作業者(\"6\")", example = "1")
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
