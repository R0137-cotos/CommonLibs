package jp.co.ricoh.cotos.commonlib.entity.master;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.master.ShippingThingMaster.ShippingType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 部材マスタを表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "element_master")
public class ElementMaster extends EntityBase {

	/**
	 * 部材マスタID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "element_master_seq")
	@SequenceGenerator(name = "element_master_seq", sequenceName = "element_master_seq", allocationSize = 1)
	@ApiModelProperty(value = "部材マスタID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 品種マスタID
	 */
	@Column(nullable = false)
	@Min(0)
	@ApiModelProperty(value = "品種マスタID", required = true, position = 2, allowableValues = "range[0,9223372036854775807]")
	private long itemMasterId;

	/**
	 * 部材区分
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "部材区分", required = false, position = 3, allowableValues = "range[0,255]")
	private String elementDiv;

	/**
	 * カテゴリ
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "カテゴリ", required = false, position = 4, allowableValues = "range[0,255]")
	private String categoryType;

	/**
	 * 物あり品種コード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "物あり品種コード", required = false, position = 5, allowableValues = "range[0,255]")
	private String thingItemCode;

	/**
	 * 部材名称
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "部材名称", required = false, position = 6, allowableValues = "range[0,255]")
	private String elementName;

	/**
	 * 長さ
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "長さ", required = false, position = 7, allowableValues = "range[0,255]")
	private String lengths;

	/**
	 * 原価
	 */
	@DecimalMax("9999999999999999999.99")
	@ApiModelProperty(value = "原価", required = false, position = 8, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal price;

	/**
	 * 発送区分
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "発送区分", required = false, allowableValues = "NW機器経由(\"0\"), 直送(\"1\"), 自課所(\"2\")", position = 9)
	private ShippingType shippingType;

	/**
	 * プロダクト確認集計表フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "プロダクト確認集計表フラグ", required = false, position = 10, allowableValues = "range[0,9]")
	private Integer productSpreadsheetFlg;

	/**
	 * 仕入先コード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "仕入先コード", required = false, position = 11, allowableValues = "range[0,255]")
	private String vendorCode;

	/**
	 * 発注不可チェックフラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "発注不可チェックフラグ", required = false, position = 12, allowableValues = "range[0,9]")
	private Integer orderBadCheckFlg;
}
