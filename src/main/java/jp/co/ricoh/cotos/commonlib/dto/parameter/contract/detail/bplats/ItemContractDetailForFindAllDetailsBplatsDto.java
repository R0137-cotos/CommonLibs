package jp.co.ricoh.cotos.commonlib.dto.parameter.contract.detail.bplats;

import java.math.BigDecimal;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractDetail;
import jp.co.ricoh.cotos.commonlib.entity.master.ItemMaster.CostType;
import jp.co.ricoh.cotos.commonlib.entity.master.ItemMaster.ItemType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 品種を表す契約一覧情報詳細取得API用DTO
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class ItemContractDetailForFindAllDetailsBplatsDto extends EntityBase {
	/**
	 * BplatsWeb販売用オプション識別区分
	 */
	public enum BplatsOptionType {

	基本商品("1"), 専用オプション("2"), 共通オプション("3");

		private final String text;

		private BplatsOptionType(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static BplatsOptionType fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElse(null);
		}
	}

	@ApiModelProperty(value = "ID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 品種マスタID
	 */
	@ApiModelProperty(value = "品種マスタID", required = true, position = 2, allowableValues = "range[0,9223372036854775807]")
	private long itemMasterId;

	/**
	 * 商品マスタID
	 */
	@ApiModelProperty(value = "商品マスタID", required = true, position = 3, allowableValues = "range[0,9223372036854775807]")
	private long productMasterId;

	/**
	 * 品種名
	 */
	@ApiModelProperty(value = "品種名", required = true, position = 4, allowableValues = "range[0,255]")
	private String itemContractName;

	/**
	 * リコー品種コード
	 */
	@ApiModelProperty(value = "リコー品種コード", required = true, position = 5, allowableValues = "range[0,255]")
	private String ricohItemCode;

	/**
	 * 品種区分
	 */
	@ApiModelProperty(value = "品種区分", required = true, allowableValues = "なし(\"0\"), 基本(\"1\"), オプション(\"2\")", example = "1", position = 6)
	private ItemType itemType;

	/**
	 * 費用種別
	 */
	@ApiModelProperty(value = "費用種別", required = true, allowableValues = "初期費(\"1\"), 月額_定額(\"2\"), 年額(\"3\"), 月額_従量(\"4\")", example = "1", position = 7)
	private CostType costType;

	/**
	 * 仕切価格
	 */
	@ApiModelProperty(value = "仕切価格", required = true, position = 8, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal partitionPrice;

	/**
	 * 契約明細
	 */
	@JsonIgnore
	@ApiModelProperty(value = "契約明細", required = true, position = 9)
	private ContractDetail contractDetail;

	/**
	 * 仕入取引先コード
	 */
	@ApiModelProperty(value = "仕入取引先コード", required = false, position = 10, allowableValues = "range[0,255]")
	private String bpCd;

	/**
	 * Ｒ原価
	 */
	@ApiModelProperty(value = "Ｒ原価", required = false, position = 11, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal rCost;

	/**
	 * ＲＪ仕入価格
	 */
	@ApiModelProperty(value = "ＲＪ仕入価格", required = false, position = 12, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal rjPurchasePrice;

	/**
	 * ＲＪ仕切価格
	 */
	@ApiModelProperty(value = "ＲＪ仕切価格", required = false, position = 13, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal rjDividingPrice;

	/**
	 * 母店売価(接点店仕切)
	 */
	@ApiModelProperty(value = "母店売価(接点店仕切)", required = false, position = 14, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal motherStorePrice;

	/**
	 * 消費税区分
	 */
	@ApiModelProperty(value = "消費税区分", required = false, position = 15, allowableValues = "range[0,255]")
	private String taxFlag;

	/**
	 * IFS連携フラグ
	 */
	@ApiModelProperty(value = "IFS連携フラグ", required = true, position = 16, allowableValues = "range[0,9]")
	private Integer ifsLinkageFlg;

	/**
	 * メーカー商品コード
	 */
	@ApiModelProperty(value = "メーカー商品コード", required = false, position = 17, allowableValues = "range[0,255]")
	private String makerItemCode;

	/**
	 * BplatsWeb販売用オプション識別区分
	 */
	@ApiModelProperty(value = "BplatsWeb販売用オプション識別区分", required = false, allowableValues = "基本商品(\"1\"), 専用オプション(\"2\"), 共通オプション(\"3\")", example = "1", position = 18)
	private BplatsOptionType bplatsOptionType;

	/**
	 * BplatsWeb販売用オプション親コード
	 */
	@ApiModelProperty(value = "BplatsWeb販売用オプション親コード", required = false, position = 19, allowableValues = "range[0,255]")
	private String bplatsParentOptionProductCd;
}
