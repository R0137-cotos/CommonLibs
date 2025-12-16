package jp.co.ricoh.cotos.commonlib.entity.externallinkage;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.contract.PenaltyDetailContract.PenaltyAccountSalesStatus;
import jp.co.ricoh.cotos.commonlib.entity.contract.PenaltyDetailContract.SalesToType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 引継ぎ用違約金明細(契約用)を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "handover_penalty_detail_contract")
public class HandoverPenaltyDetailContract extends EntityBase {

	/**
	 * 引継ぎ用違約金明細ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "handover_penalty_detail_contract_seq")
	@SequenceGenerator(name = "handover_penalty_detail_contract_seq", sequenceName = "handover_penalty_detail_contract_seq", allocationSize = 1)
	@Schema(description = "引継ぎ用違約金明細ID(作成時不要)", required = true, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 契約ID
	 */
	@Min(0)
	@Schema(description = "契約ID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private long contractId;

	/**
	 * 品種マスタID
	 */
	@Min(0)
	@Schema(description = "品種マスタID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private long itemMasterId;

	/**
	 * 品種名
	 */
	@Size(max = 255)
	@Schema(description = "品種名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String itemName;

	/**
	 * リコー品種コード
	 */
	@Size(max = 255)
	@NotNull
	@Schema(description = "リコー品種コード", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String ricohItemCode;

	/**
	 * 品種区分
	 */
	@Size(max = 255)
	@Schema(description = "品種区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String itemType;

	/**
	 * 違約金単価
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@Schema(description = "違約金単価", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal penaltyUnitPrice;

	/**
	 * 数量
	 */
	@Max(99999)
	@Min(0)
	@Schema(description = "数量", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,99999]")
	private Integer quantity;

	/**
	 * 違約金金額
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@Schema(description = "違約金金額", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal penaltyAmountSummary;

	/**
	 * 計上先区分
	 */
	@Schema(description = "計上先区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "エンドユーザ(\"1\"), 課所止め(\"2\")", example = "1")
	private SalesToType salesToType;

	/**
	 * 削除フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "削除フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer deleteFlg;

	/**
	 * 違約金売上計上処理状態
	 */
	@Schema(description = "違約金売上計上処理状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未計上(\"0\"), 計上済み(\"1\"), 処理不要(\"2\"), 処理不可(\"3\")", example = "1")
	private PenaltyAccountSalesStatus penaltyAccountSalesStatus;

	/**
	 * 違約金売上計上処理日
	 */
	@Schema(description = "違約金売上計上処理日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.DATE)
	private Date penaltyAccountSalesDate;

	/**
	 * 引継ぎ元契約ID
	 */
	@Min(0)
	@Schema(description = "引継ぎ元契約ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private Long handoverContractId;

	/**
	 * 引継ぎ反映フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "引継ぎ反映フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer handoverMappedFlg;
}
