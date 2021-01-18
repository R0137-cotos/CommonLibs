package jp.co.ricoh.cotos.commonlib.entity.master;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import jp.co.ricoh.cotos.commonlib.entity.master.ItemMaster.CostType;
import jp.co.ricoh.cotos.commonlib.entity.master.ItemMaster.ItemType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 品種分解マスタ
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "item_decompose_master")
public class ItemDecomposeMaster extends EntityBaseMaster {

	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_decompose_master_seq")
	@SequenceGenerator(name = "item_decompose_master_seq", sequenceName = "item_decompose_master_seq", allocationSize = 1)
	@ApiModelProperty(value = "ID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 分解後品種マスタ
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "item_decompose_master_id", referencedColumnName = "id")
	@JsonIgnore
	@ApiModelProperty(value = "分解後品種マスタ", required = true, position = 2)
	private ItemMaster decomposeItemMaster;

	/**
	 * 分解後品種名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "分解後品種名", required = false, position = 3, allowableValues = "range[0,255]")
	private String itemDecomposeName;

	/**
	 * リコー品種コード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "リコー品種コード", required = false, position = 4, allowableValues = "range[0,255]")
	private String ricohItemCode;

	/**
	 * 分解後品種区分
	 */
	@ApiModelProperty(value = "分解後品種区分", required = false, allowableValues = "なし(\"0\"), 基本(\"1\"), オプション(\"2\")", position = 5)
	private ItemType itemType;

	/**
	 * 分解後費用種別
	 */
	@ApiModelProperty(value = "分解後費用種別", required = false, allowableValues = "初期費(\"1\"), 月額_定額(\"2\"), 年額(\"3\"), 月額_従量(\"4\"), 違約金(\"5\")", position = 6)
	private CostType costType;

	/**
	 * 分解後原価
	 */
	@DecimalMax("9999999999999999999.99")
	@ApiModelProperty(value = "分解後原価", required = false, position = 7, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal price;

	/**
	 * 品種マスタ
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "item_master_id", referencedColumnName = "id")
	@JsonIgnore
	@ApiModelProperty(value = "品種マスタ", required = true, position = 8)
	private ItemMaster itemMaster;

}
