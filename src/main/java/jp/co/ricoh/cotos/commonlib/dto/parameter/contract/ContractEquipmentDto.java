package jp.co.ricoh.cotos.commonlib.dto.parameter.contract;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.DtoBase;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractEquipment.ArcsPeriodSaleMainteProcStatus;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractEquipment.IsysoneMaintereportProcStatus;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractEquipment.IsysoneProcStatus;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractEquipment.IsysoneReLinkageStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class ContractEquipmentDto extends DtoBase {

	/**
	 * 機種コード
	 */
	@Size(max = 255)
	@NotNull
	@Column(nullable = false)
	@ApiModelProperty(value = "機種コード", required = true, position = 3, allowableValues = "range[0,255]")
	private String equipmentCode;

	/**
	 * 機番
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "機番", required = false, position = 4, allowableValues = "range[0,255]")
	private String equipmentNo;

	/**
	 * 本体フラグ
	 */
	@ApiModelProperty(value = "本体フラグ", required = false, position = 5, allowableValues = "オプション(0),本体(1)")
	private Integer bodyFlg;

	/**
	 * サービス機器フラグ
	 */
	@ApiModelProperty(value = "サービス機器フラグ", required = false, position = 6, allowableValues = "本体機器(0),サービス機器(1)")
	private Integer serviceMachineFlg;

	/**
	 * 設置日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "設置日", required = false, position = 7)
	private Date installationDate;

	/**
	 * 購入形態
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "購入形態", required = false, position = 8, allowableValues = "range[0,255]")
	private String purchaseForm;

	/**
	 * 納入形態
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "納入形態", required = false, position = 9, allowableValues = "range[0,255]")
	private String deliveryForm;

	/**
	 * 保守形態
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "保守形態", required = false, position = 10, allowableValues = "range[0,255]")
	private String maintenanceForm;

	/**
	 * 納入機器区分
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "納入機器区分", required = false, position = 11, allowableValues = "range[0,255]")
	private String deliveryMachineDiv;

	/**
	 * メンテの注意(カナ)
	 */
	@Size(max = 1000)
	@ApiModelProperty(value = "メンテの注意(カナ)", required = false, position = 12, allowableValues = "range[0,1000]")
	private String maintenanceNoteKana;

	/**
	 * Isys-One 処理状態
	 */
	@ApiModelProperty(value = "Isys-One 処理状態", required = false, position = 13, allowableValues = "未処理(\"0\"),CSV作成済み(\"1\"),連携済み(\"2\"),連携エラー(\"3\")")
	private IsysoneProcStatus isysoneProcStatus;

	/**
	 * Isys-One 連携日時
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "Isys-One 連携日時(1時間毎に連携するため日時とする)", required = false, position = 14)
	private Date isysoneLinkageAt;

	/**
	 * 点検診断月指定
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "点検診断月指定", required = false, position = 15, allowableValues = "range[0,255]")
	private String inspectionMonth;

	/**
	 * 点検診断月(12ヶ月分)
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "点検診断月(12ヶ月分)", required = false, position = 16, allowableValues = "range[0,255]")
	private String inspectionMonthYearWorth;

	/**
	 * 拡張項目
	 */
	@ApiModelProperty(value = "拡張項目", required = false, position = 17)
	@Lob
	private String extendsParameter;

	/**
	 * Isys-One保守レポート処理状態
	 */
	@ApiModelProperty(value = "Isys-One保守レポート処理状態", required = false, position = 18, allowableValues = "未処理(\"0\"),CSV作成済み(\"1\"),連携済み(\"2\")")
	private IsysoneMaintereportProcStatus isysoneMaintereportProcStatus;

	/**
	 * Isys-One保守レポート連携日時
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@ApiModelProperty(value = "Isys-One保守レポート連携日時", required = false, position = 19)
	private Date isysoneMaintereportLinkageAt;

	/**
	 * ARCS期間売保守処理状態
	 */
	@ApiModelProperty(value = "ARCS期間売保守処理状態", required = false, position = 20, allowableValues = "未作成(\"0\"),作成済み(\"1\"),作成不要(\"2\"),作成エラー(\"3\")")
	private ArcsPeriodSaleMainteProcStatus arcsPeriodSaleMainteProcStatus;

	/**
	 * ARCS期間売保守連携日
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@ApiModelProperty(value = "ARCS期間売保守連携日", required = false, position = 21)
	private Date arcsPeriodSaleMainteLinkageAt;

	/**
	 * Isys-One再連携ステータス
	 */
	@ApiModelProperty(value = "Isys-One再連携ステータス", required = false, position = 22, allowableValues = "再連携不要(\"0\"),再連携必要(\"1\"),再連携済(\"2\")")
	private IsysoneReLinkageStatus isysoneReLinkageStatus;

	/**
	 * Isys-One連携済機番
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "Isys-One連携済機番", required = false, position = 23, allowableValues = "range[0,255]")
	private String isysoneLinkagedEquipmentNo;

	/**
	 * Isys-One連携済機種コード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "Isys-One連携済機種コード", required = false, position = 24, allowableValues = "range[0,255]")
	private String isysoneLinkagedEquipmentCode;

	/**
	 * 機種名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "機種名", required = false, position = 25, allowableValues = "range[0,255]")
	private String equipmentName;

	/**
	 * ARCS期間売保守データ用保守形態
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "ARCS期間売保守データ用保守形態", required = false, position = 26, allowableValues = "range[0,255]")
	private String arcsMaintenanceForm;

	/**
	 * Isys-One連携済設置日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "Isys-One連携済設置日", required = false, position = 27)
	private Date isysoneLinkagedInstallationDate;
}
