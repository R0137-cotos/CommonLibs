package jp.co.ricoh.cotos.commonlib.entity.master;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 機種構成マスタ
 */
@Entity
@Data
@ToString(exclude = { "itemMaster" })
@EqualsAndHashCode(callSuper = true)
@Table(name = "equipment_comp_master")
public class EquipmentCompMaster extends EntityBaseMaster {

	/**
	 * 機種構成マスタID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "equipment_comp_master_seq")
	@SequenceGenerator(name = "equipment_comp_master_seq", sequenceName = "equipment_comp_master_seq", allocationSize = 1)
	@Schema(description = "機種構成マスタID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9999999999999999999999999999]")
	private long id;

	/**
	 * 機種コード
	 */
	@Column(nullable = false)
	@Size(max = 255)
	@Schema(description = "機種コード", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String equipmentCode;

	/**
	 * 本体フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "本体フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer bodyFlg;

	/**
	 * サービス機器フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "サービス機器フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer serviceMachineFlg;

	/**
	 * 保守売上連携フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "保守売上連携フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer maintenanceLinkageFlg;

	/**
	 * 保守形態
	 */
	@Size(max = 255)
	@Schema(description = "保守形態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String maintenanceForm;

	/**
	 * 品種マスタ
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "item_master_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "品種マスタ", requiredMode = Schema.RequiredMode.REQUIRED)
	private ItemMaster itemMaster;

	/**
	 * 点検診断月指定
	 */
	@Size(max = 255)
	@Schema(description = "点検診断月指定", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String inspectionMonth;

	/**
	 * 点検診断月(12ヶ月分)
	 */
	@Size(max = 255)
	@Schema(description = "点検診断月(12ヶ月分)", allowableValues = "range[0,255]")
	private String inspectionMonthYearWorth;

	/**
	 * 振替対象品種マスタ
	 */
	@Size(max = 255)
	@Schema(description = "振替対象品種マスタ", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String trnsItemMasterId;

	/**
	 * 機器品種作成フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "機器品種作成フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer equipmentItemCreatingFlg;

	/**
	 * ARCS期間売保守データ用保守形態
	 */
	@Size(max = 255)
	@Schema(description = "ARCS期間売保守データ用保守形態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String arcsMaintenanceForm;

	/**
	 * メーカーコード
	 */
	@Size(max = 255)
	@Schema(description = "メーカーコード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String makerCode;

	/**
	 * 機種削除可能フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "機種削除可能フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer equipmentDeletableFlg;
}
