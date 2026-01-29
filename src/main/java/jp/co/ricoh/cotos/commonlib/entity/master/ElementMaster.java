package jp.co.ricoh.cotos.commonlib.entity.master;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "部材マスタID(作成時不要)", required = true, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 品種マスタID
	 */
	@Column(nullable = false)
	@Min(0)
	@Schema(description = "品種マスタID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private long itemMasterId;

	/**
	 * 部材区分
	 */
	@Size(max = 255)
	@Schema(description = "部材区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String elementDiv;

	/**
	 * カテゴリ
	 */
	@Size(max = 255)
	@Schema(description = "カテゴリ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String categoryType;

	/**
	 * 物あり品種コード
	 */
	@Size(max = 255)
	@Schema(description = "物あり品種コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String thingItemCode;

	/**
	 * 部材名称
	 */
	@Size(max = 255)
	@Schema(description = "部材名称", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String elementName;

	/**
	 * 長さ
	 */
	@Size(max = 255)
	@Schema(description = "長さ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String lengths;

	/**
	 * 原価
	 */
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "原価", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal price;

	/**
	 * 発送区分
	 */
	@Size(max = 255)
	@Schema(description = "発送区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "NW機器経由(\"0\"), 直送(\"1\"), 自課所(\"2\")")
	private ShippingType shippingType;

	/**
	 * プロダクト確認集計表フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "プロダクト確認集計表フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer productSpreadsheetFlg;

	/**
	 * 仕入先コード
	 */
	@Size(max = 255)
	@Schema(description = "仕入先コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String vendorCode;

	/**
	 * 発注不可チェックフラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "発注不可チェックフラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer orderBadCheckFlg;
}
