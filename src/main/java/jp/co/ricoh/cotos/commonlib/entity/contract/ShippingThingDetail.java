package jp.co.ricoh.cotos.commonlib.entity.contract;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.master.ShippingThingMaster.ShippingType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 発送物あり明細を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "shipping_thing_detail")
public class ShippingThingDetail extends EntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shipping_thing_detail_seq")
	@SequenceGenerator(name = "shipping_thing_detail_seq", sequenceName = "shipping_thing_detail_seq", allocationSize = 1)
	@Schema(description = "ID(作成時不要)", required = true, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 物あり品種コード
	 */
	@Size(max = 255)
	@Schema(description = "物あり品種コード", required = false, allowableValues = "range[0,255]")
	private String thingItemCode;

	/**
	 * 発送機器名称
	 */
	@Size(max = 255)
	@Schema(description = "発送機器名称", required = false, allowableValues = "range[0,255]")
	private String shippingMachineName;

	/**
	 * 数量
	 */
	@Max(99999)
	@Min(0)
	@Schema(description = "数量", required = false, allowableValues = "range[0,99999]")
	private int quantity;

	/**
	 * 原価
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@Schema(description = "原価", required = false, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal price;

	/**
	 * 発送区分
	 */
	@Schema(description = "発送区分", required = false, allowableValues = "NW機器経由(\"0\"), 直送(\"1\"), 自課所(\"2\")", example = "1")
	private ShippingType shippingType;

	/**
	 * FFM内部振替除外フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "FFM内部振替除外フラグ", required = false, allowableValues = "range[0,9]")
	private Integer ffmInsideTransExclusionFlg;

	/**
	 * プロダクト確認集計表フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "プロダクト確認集計表フラグ", required = false, allowableValues = "range[0,9]")
	private Integer productSpreadsheetFlg;

	/**
	 * 契約明細
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "contract_detail_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "契約明細", required = true)
	private ContractDetail contractDetail;

	/**
	 * 仕入先コード
	 */
	@Size(max = 255)
	@Schema(description = "仕入先コード", required = false, allowableValues = "range[0,255]")
	private String vendorCode;
}
