package jp.co.ricoh.cotos.commonlib.entity.master;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "ID(作成時不要)", required = true, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 分解後品種マスタ
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "item_decompose_master_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "分解後品種マスタ", required = true)
	private ItemMaster decomposeItemMaster;

	/**
	 * 分解後品種名
	 */
	@Size(max = 255)
	@Schema(description = "分解後品種名", required = false, allowableValues = "range[0,255]")
	private String itemDecomposeName;

	/**
	 * リコー品種コード
	 */
	@Size(max = 255)
	@Schema(description = "リコー品種コード", required = false, allowableValues = "range[0,255]")
	private String ricohItemCode;

	/**
	 * 分解後品種区分
	 */
	@Schema(description = "分解後品種区分", required = false, allowableValues = "なし(\"0\"), 基本(\"1\"), オプション(\"2\")")
	private ItemType itemType;

	/**
	 * 分解後費用種別
	 */
	@Schema(description = "分解後費用種別", required = false, allowableValues = "初期費(\"1\"), 月額_定額(\"2\"), 年額(\"3\"), 月額_従量(\"4\"), 違約金(\"5\")")
	private CostType costType;

	/**
	 * 分解後原価
	 */
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "分解後原価", required = false, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal price;

	/**
	 * 品種マスタ
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "item_master_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "品種マスタ", required = true)
	private ItemMaster itemMaster;

}
