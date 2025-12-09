package jp.co.ricoh.cotos.commonlib.dto.parameter.contract.detail.bplats;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Lob;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.DetailStatus;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.InitialAccountSalesStatus;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractDetail.AbsConInsideTransStatus;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractDetail.FfmAcceptanceLinkingStatus;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractDetail.FfmInsideTransStatus;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractDetail.RunningAccountSalesStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 契約明細を表す契約一覧情報詳細取得API用DTO
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class ContractDetailForFindAllDetailsBplatsDto extends EntityBase {

	@Schema(description = "契約明細ID(作成時不要)", allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 契約
	 */
	@JsonIgnore
	@Schema(description = "契約", requiredMode = Schema.RequiredMode.REQUIRED)
	private Contract contract;

	/**
	 * 状態
	 */
	@Schema(description = "状態", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "NONE(\"1\"), ADD(\"2\"), DELETE(\"3\"), UPDATE(\"4\")", example = "")
	private DetailStatus state;

	/**
	 * 変更前数量
	 */
	@Schema(description = "変更前数量", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,99999]")
	private Integer beforeQuantity;

	/**
	 * 数量
	 */
	@Schema(description = "数量", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,99999]")
	private int quantity;

	/**
	 * 単価
	 */
	@Schema(description = "単価", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal unitPrice;

	/**
	 * 金額
	 */
	@Schema(description = "金額", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal amountSummary;

	/**
	 * 摘要
	 */
	@Schema(description = "摘要", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String detailAbstract;

	/**
	 * 拡張項目
	 */
	@Schema(description = "拡張項目", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Lob
	private String extendsParameter;

	/**
	 * イニシャル売上計上処理状態
	 */
	@Schema(description = "イニシャル売上計上処理状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未処理(\"0\"), CSV作成済み(\"1\"), 連携済み(\"2\"), 対象外(\"3\")")
	private InitialAccountSalesStatus initialAccountSalesStatus;

	/**
	 * イニシャル売上計上処理日
	 */
	@Schema(description = "イニシャル売上計上処理日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.DATE)
	private Date initialAccountSalesDate;

	/**
	 * 品種(契約用)
	 */
	@Schema(description = "品種(契約用)")
	private ItemContractDetailForFindAllDetailsBplatsDto itemContract;

	/**
	 * 注文番号
	 */
	@Schema(description = "注文番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String orderNo;

	/**
	 * FFM内部振替処理状態
	 */
	@Schema(description = "FFM内部振替処理状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未処理中(\"0\"), 作成完了(\"1\"), 連携済み(\"2\"), 対象外(\"3\")")
	private FfmInsideTransStatus ffmInsideTransStatus;

	/**
	 * FFM内部振替連携日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "FFM内部振替連携日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date ffmInsideLinkDate;

	/**
	 * 統合契約内部振替処理状態
	 */
	@Schema(description = "統合契約内部振替処理状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未処理(\"0\"), 連携済み(\"1\"), 対象外(\"4\")")
	private AbsConInsideTransStatus absConInsideTransStatus;

	/**
	 * 統合契約内部振替連携日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "統合契約内部振替連携日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date absConInsideLinkDate;

	/**
	 * ランニング売上計上処理状態
	 */
	@Schema(description = "ランニング売上計上処理状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "正常(\"0\"), 処理エラー(\"1\"), 処理不要(\"2\")")
	private RunningAccountSalesStatus runningAccountSalesStatus;

	/**
	 * ランニング売上計上処理日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "ランニング売上計上処理日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date runningAccountSalesDate;

	/**
	 * FFM検収連携状態
	 */
	@Schema(description = "FFM検収連携状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未作成(\"0\"), 作成済み(\"1\"), 作成エラー(\"2\"), 対象外(\"3\")")
	private FfmAcceptanceLinkingStatus ffmAcceptanceLinkingStatus;

	/**
	 * 契約期間
	 */
	@Schema(description = "契約期間", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String contractSpan;

	/**
	 * 品種追加フラグ
	 */
	@Schema(description = "品種追加フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer itemAddFlg;

	/**
	 * 契約数
	 */
	@Schema(description = "変更前数量", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,99999]")
	private Integer contractAmount;
}