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
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.InitialRunningDiv;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 品種明細(契約用)を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "item_detail_contract")
public class ItemDetailContract extends EntityBase {

	/**
	 * 品種明細ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_detail_contract_seq")
	@SequenceGenerator(name = "item_detail_contract_seq", sequenceName = "item_detail_contract_seq", allocationSize = 1)
	@Schema(description = "品種明細ID(作成時不要)", allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 原価
	 */
	@DecimalMin("-9999999999999999999.99")
	@Digits(integer = 19, fraction = 2)
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "原価", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[-9999999999999999999.99,9999999999999999999.99]")
	private BigDecimal price;

	/**
	 * 振替先課所コード
	 */
	@Size(max = 255)
	@Schema(description = "振替先課所コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String transToServiceOrgCode;

	/**
	 * 振替先課所名
	 */
	@Size(max = 255)
	@Schema(description = "振替先課所名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String transToServiceOrgName;

	/**
	 * イニシャル/ランニング区分
	 */
	@Schema(description = "イニシャル/ランニング区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "イニシャル(\"1\"), ランニング(\"2\"), 期間売(\"3\")")
	private InitialRunningDiv initialRunningDiv;

	/**
	 * 品種ID
	 */
	@ManyToOne(optional = false)
	@JsonIgnore
	@JoinColumn(name = "item_contract_id", referencedColumnName = "id")
	@Schema(description = "品種(契約用)")
	private ItemContract itemContract;

	/**
	 * 品種振替構成マスタID
	 */
	@Min(0)
	@Schema(description = "品種振替構成マスタID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private Long itemTransCompMasterId;

	/**
	 * 一括登録フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "一括登録フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer batchImportFlg;

	/**
	 * 数量
	 */
	@Max(99999)
	@Min(-99999)
	@Schema(description = "数量", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[-99999,99999]")
	private Integer quantity;
}
