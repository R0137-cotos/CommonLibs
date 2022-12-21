package jp.co.ricoh.cotos.commonlib.dto.parameter.contract.detail.bplats;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
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

	@ApiModelProperty(value = "契約明細ID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 契約
	 */
	@JsonIgnore
	@ApiModelProperty(value = "契約", required = true, position = 2)
	private Contract contract;

	/**
	 * 状態
	 */
	@ApiModelProperty(value = "状態", required = true, allowableValues = "NONE(\"1\"), ADD(\"2\"), DELETE(\"3\"), UPDATE(\"4\")", example = "", position = 3)
	private DetailStatus state;

	/**
	 * 変更前数量
	 */
	@ApiModelProperty(value = "変更前数量", required = false, position = 4, allowableValues = "range[0,99999]")
	private Integer beforeQuantity;

	/**
	 * 数量
	 */
	@ApiModelProperty(value = "数量", required = true, position = 5, allowableValues = "range[0,99999]")
	private int quantity;

	/**
	 * 単価
	 */
	@ApiModelProperty(value = "単価", required = true, position = 6, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal unitPrice;

	/**
	 * 金額
	 */
	@ApiModelProperty(value = "金額", required = true, position = 7, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal amountSummary;

	/**
	 * 摘要
	 */
	@ApiModelProperty(value = "摘要", required = false, position = 8, allowableValues = "range[0,255]")
	private String detailAbstract;

	/**
	 * 拡張項目
	 */
	@ApiModelProperty(value = "拡張項目", required = false, position = 9)
	@Lob
	private String extendsParameter;

	/**
	 * イニシャル売上計上処理状態
	 */
	@ApiModelProperty(value = "イニシャル売上計上処理状態", required = false, allowableValues = "未処理(\"0\"), CSV作成済み(\"1\"), 連携済み(\"2\"), 対象外(\"3\")", position = 10)
	private InitialAccountSalesStatus initialAccountSalesStatus;

	/**
	 * イニシャル売上計上処理日
	 */
	@ApiModelProperty(value = "イニシャル売上計上処理日", required = false, position = 11)
	@Temporal(TemporalType.DATE)
	private Date initialAccountSalesDate;

	/**
	 * 品種(契約用)
	 */
	@ApiModelProperty(value = "品種(契約用)", required = false, position = 12)
	private ItemContractDetailForFindAllDetailsBplatsDto itemContract;

	/**
	 * 注文番号
	 */
	@ApiModelProperty(value = "注文番号", required = false, position = 13, allowableValues = "range[0,255]")
	private String orderNo;

	/**
	 * FFM内部振替処理状態
	 */
	@ApiModelProperty(value = "FFM内部振替処理状態", required = false, allowableValues = "未処理中(\"0\"), 作成完了(\"1\"), 連携済み(\"2\"), 対象外(\"3\")", position = 14)
	private FfmInsideTransStatus ffmInsideTransStatus;

	/**
	 * FFM内部振替連携日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "FFM内部振替連携日", required = false, position = 15)
	private Date ffmInsideLinkDate;

	/**
	 * 統合契約内部振替処理状態
	 */
	@ApiModelProperty(value = "統合契約内部振替処理状態", required = false, allowableValues = "未処理(\"0\"), 連携済み(\"1\"), 対象外(\"4\")", position = 16)
	private AbsConInsideTransStatus absConInsideTransStatus;

	/**
	 * 統合契約内部振替連携日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "統合契約内部振替連携日", required = false, position = 17)
	private Date absConInsideLinkDate;

	/**
	 * ランニング売上計上処理状態
	 */
	@ApiModelProperty(value = "ランニング売上計上処理状態", required = false, allowableValues = "正常(\"0\"), 処理エラー(\"1\"), 処理不要(\"2\")", position = 18)
	private RunningAccountSalesStatus runningAccountSalesStatus;

	/**
	 * ランニング売上計上処理日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "ランニング売上計上処理日", required = false, position = 19)
	private Date runningAccountSalesDate;

	/**
	 * FFM検収連携状態
	 */
	@ApiModelProperty(value = "FFM検収連携状態", required = false, allowableValues = "未作成(\"0\"), 作成済み(\"1\"), 作成エラー(\"2\"), 対象外(\"3\")", position = 20)
	private FfmAcceptanceLinkingStatus ffmAcceptanceLinkingStatus;

	/**
	 * 契約期間
	 */
	@ApiModelProperty(value = "契約期間", required = false, position = 21, allowableValues = "range[0,255]")
	private String contractSpan;

	/**
	 * 品種追加フラグ
	 */
	@ApiModelProperty(value = "品種追加フラグ", required = false, position = 22, allowableValues = "range[0,9]")
	private Integer itemAddFlg;

	/**
	 * 契約数
	 */
	@ApiModelProperty(value = "変更前数量", required = false, position = 23, allowableValues = "range[0,99999]")
	private Integer contractAmount;
}