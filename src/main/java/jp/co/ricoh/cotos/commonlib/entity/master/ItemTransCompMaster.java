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
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.InitialRunningDiv;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 品種振替構成マスタ
 */
@Entity
@Data
@ToString(exclude = { "itemMaster" })
@EqualsAndHashCode(callSuper = true)
@Table(name = "item_trans_comp_master")
public class ItemTransCompMaster extends EntityBaseMaster {

	/**
	 * 品種振替構成マスタID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_trans_comp_master_seq")
	@SequenceGenerator(name = "item_trans_comp_master_seq", sequenceName = "item_trans_comp_master_seq", allocationSize = 1)
	@Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private long id;

	/**
	 * 原価
	 */
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "原価", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal price;

	/**
	 * 振替先課所コード
	 */
	@Size(max = 255)
	@Schema(description = "振替先課所コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String transToServiceOrgCode;

	/**
	 * イニシャル/ランニング区分
	 */
	@Schema(description = "イニシャル/ランニング区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "イニシャル(\"1\"), ランニング(\"2\"), 期間売(\"3\"), 期間売_月額(\"4\")")
	private InitialRunningDiv initialRunningDiv;

	/**
	 * 品種マスタ
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "item_master_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "品種マスタ", requiredMode = Schema.RequiredMode.REQUIRED)
	private ItemMaster itemMaster;

	/**
	 * ディスパッチ振替フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "ディスパッチ振替フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer dispatchTransferFlg;

	/**
	 * 価格改定日マスタID
	 */
	@Schema(description = "価格改定日マスタID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private Long priceRevisionDateMasterId;

	/**
	 * 一括取込商品フラグ
	 */
	@Schema(description = "一括取込商品フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer batchImportTargetFlg;
}
