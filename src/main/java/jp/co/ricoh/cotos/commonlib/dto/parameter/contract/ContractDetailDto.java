package jp.co.ricoh.cotos.commonlib.dto.parameter.contract;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.DtoBase;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.DetailStatus;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.InitialAccountSalesStatus;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractDetail.AbsConExternalBillingStatus;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractDetail.AbsConInsideTransStatus;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractDetail.FfmAcceptanceLinkingStatus;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractDetail.FfmInsideTransStatus;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractDetail.RunningAccountSalesStatus;
import jp.co.ricoh.cotos.commonlib.entity.estimation.EstimationDetail.IncreaseDecreaseDiv;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class ContractDetailDto extends DtoBase {

	/**
	 * 状態
	 */
	@NotNull
	@Schema(description = "状態", required = true, allowableValues = "NONE(\"1\"), ADD(\"2\"), DELETE(\"3\"), UPDATE(\"4\")", example = "")
	private DetailStatus state;

	/**
	 * 変更前数量
	 */
	@Max(99999)
	@Min(-99999)
	@Schema(description = "変更前数量", required = false, allowableValues = "range[-99999,99999]")
	private Integer beforeQuantity;

	/**
	 * 数量
	 */
	@Min(-99999)
	@Max(99999)
	@Schema(description = "数量", required = true, allowableValues = "range[-99999,99999]")
	private int quantity;

	/**
	 * 単価
	 */
	@NotNull
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@Schema(description = "単価", required = true, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal unitPrice;

	/**
	 * 金額
	 */
	@NotNull
	@Digits(integer = 19, fraction = 2)
	@Schema(description = "金額", required = true, allowableValues = "range[-9999999999999999999.99,9999999999999999999.99]")
	private BigDecimal amountSummary;

	/**
	 * 摘要
	 */
	@Size(max = 255)
	@Schema(description = "摘要", required = false, allowableValues = "range[0,255]")
	private String detailAbstract;

	/**
	 * 拡張項目
	 */
	@Schema(description = "拡張項目", required = false)
	@Lob
	private String extendsParameter;

	/**
	 * イニシャル売上計上処理状態
	 */
	@Schema(description = "イニシャル売上計上処理状態", required = false, allowableValues = "未計上(\"0\"), 計上済み(\"1\"), 処理不要(\"2\"), 処理不可(\"3\")")
	private InitialAccountSalesStatus initialAccountSalesStatus;

	/**
	 * イニシャル売上計上処理日
	 */
	@Schema(description = "イニシャル売上計上処理日", required = false)
	@Temporal(TemporalType.DATE)
	private Date initialAccountSalesDate;

	/**
	 * 注文番号
	 */
	@Size(max = 255)
	@Schema(description = "注文番号", required = false, allowableValues = "range[0,255]")
	private String orderNo;

	/**
	 * FFM内部振替処理状態
	 */
	@Schema(description = "FFM内部振替処理状態", required = false, allowableValues = "未処理(\"0\"), CSV作成済み(\"1\"), 連携済み(\"2\"), 対象外(\"3\")")
	private FfmInsideTransStatus ffmInsideTransStatus;

	/**
	 * FFM内部振替連携日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "FFM内部振替連携日", required = false)
	private Date ffmInsideLinkDate;

	/**
	 * 統合契約内部振替処理状態
	 */
	@Schema(description = "統合契約内部振替処理状態", required = false, allowableValues = "未処理(\"0\"), 連携済み(\"1\"), 対象外(\"4\")")
	private AbsConInsideTransStatus absConInsideTransStatus;

	/**
	 * 統合契約内部振替連携日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "統合契約内部振替連携日", required = false)
	private Date absConInsideLinkDate;

	/**
	 * ランニング売上計上処理状態
	 */
	@Schema(description = "ランニング売上計上処理状態", required = false, allowableValues = "正常(\"0\"), 処理エラー(\"1\"), 処理不要(\"2\")")
	private RunningAccountSalesStatus runningAccountSalesStatus;

	/**
	 * ランニング売上計上処理日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "ランニング売上計上処理日", required = false)
	private Date runningAccountSalesDate;

	@Valid
	@NotNull
	@OneToOne(mappedBy = "contractDetail")
	@Schema(description = "品種(契約用)", required = true)
	private ItemContractDto itemContract;

	/**
	 * 契約期間
	 */
	@Size(max = 255)
	@Schema(description = "契約期間", required = false, allowableValues = "range[0,255]")
	private String contractSpan;

	/**
	 * 品種追加フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "品種追加フラグ", required = false, allowableValues = "range[0,9]")
	private Integer itemAddFlg;

	/**
	 * FFM検収連携状態
	 */
	@Schema(description = "FFM検収連携状態", required = false, allowableValues = "未作成(\"0\"), 作成済み(\"1\"), 作成エラー(\"2\")")
	private FfmAcceptanceLinkingStatus ffmAcceptanceLinkingStatus;

	/**
	 * 統合契約外部請求処理状態
	 */
	@Schema(description = "統合契約外部請求処理状態", required = false, allowableValues = "未処理(\"0\"), CSV作成済み(\"1\"), 対象外(\"2\")")
	private AbsConExternalBillingStatus absConExternalBillingStatus;

	/**
	 * 統合契約外部請求連携日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "統合契約外部請求連携日", required = false)
	private Date absConExternalBillingDate;

	/**
	 * 契約数
	 */
	@Min(-99999)
	@Max(99999)
	@Schema(description = "契約数", required = false, allowableValues = "range[-99999,99999]")
	private Integer contractAmount;

	/**
	 * 増減区分
	 */
	@Schema(description = "増減区分", required = false, allowableValues = "増数(\"1\"), 減数(\"2\")", example = "1")
	private IncreaseDecreaseDiv increaseDecreaseDiv;

}
