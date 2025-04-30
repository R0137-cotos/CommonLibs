package jp.co.ricoh.cotos.commonlib.entity.contract;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.ContractType;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.LifecycleStatus;
import lombok.Data;

/**
 * 有効契約期間履歴VIEW
 */
@Entity
@Data
@Table(name = "v_valid_contract_period_history")
public class VValidContractPeriodHistory {

	@Id
	private long id;

	/**
	 * RJ管理番号
	 */
	@ApiModelProperty(value = "RJ管理番号")
	private String rjManageNumber;

	/**
	 * 契約ID
	 */
	@ApiModelProperty(value = "契約ID")
	private long contractId;

	/**
	 * 期間開始日
	 */
	@ApiModelProperty(value = "期間開始日")
	@Temporal(TemporalType.DATE)
	private Date contractDateStart;

	/**
	 * 期間終了日
	 */
	@ApiModelProperty(value = "期間終了日")
	@Temporal(TemporalType.DATE)
	private Date contractDateEnd;

	/**
	 * 契約種別
	 */
	@ApiModelProperty(value = "契約種別", allowableValues = "新規(\"1\"), 契約変更(\"2\"), 情報変更(\"3\"), 契約更新(\"4\")")
	private ContractType contractType;

	/**
	 * ライフサイクル状態
	 */
	@ApiModelProperty(value = "ライフサイクル状態(作成時不要)", allowableValues = "作成中(\"1\"), 作成完了(\"2\"), キャンセル手続き中(\"3\"), 破棄(\"4\"), 予定日待ち(\"5\"), 締結中(\"6\"), 解約手続き中(\"7\"), 解約予定日待ち(\"8\"), 解約(\"9\"), 旧契約(\"10\")", example = "1")
	private LifecycleStatus lifecycleStatus;

	/**
	 * 商品種類区分
	 */
	@ApiModelProperty(value = "商品種類区分")
	private String productClassDiv;
}