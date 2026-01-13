package jp.co.ricoh.cotos.commonlib.entity.contract;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.serializer.UnixTimestampDateSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 契約機種を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "contract_equipment")
public class ContractEquipment extends EntityBase {

	@Description(value = "Isys-One 処理状態")
	public enum IsysoneProcStatus {
		未処理("0"), CSV作成済み("1"), 連携済み("2"), 連携エラー("3"), 対象外("4"), 管理対象外("5");

		private final String text;

		private IsysoneProcStatus(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static IsysoneProcStatus fromString(final String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "Isys-One保守レポート処理状態")
	public enum IsysoneMaintereportProcStatus {
		未処理("0"), CSV作成済み("1"), 対象外("2");

		private final String text;

		private IsysoneMaintereportProcStatus(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static IsysoneMaintereportProcStatus fromString(final String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "ARCS期間売保守処理状態")
	public enum ArcsPeriodSaleMainteProcStatus {
		未作成("0"), CSV作成済み("1"), 対象外("2"), 管理対象外("3");

		private final String text;

		private ArcsPeriodSaleMainteProcStatus(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static ArcsPeriodSaleMainteProcStatus fromString(final String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	// SVPの移行元のRITOSと同様に0から採番
	@Description(value = "機器区分")
	public enum MachineType {
		サーバー本体("0"), HWオプション("1"), SWオプション("2");

		private final String text;

		private MachineType(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static MachineType fromString(final String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "Isys-One再連携ステータス")
	public enum IsysoneReLinkageStatus {
		再連携不要("0"), 再連携必要("1"), 再連携済("2");

		private final String text;

		private IsysoneReLinkageStatus(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static IsysoneReLinkageStatus fromString(final String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contract_equipment_seq")
	@SequenceGenerator(name = "contract_equipment_seq", sequenceName = "contract_equipment_seq", allocationSize = 1)
	@Schema(description = "ID(作成時不要)", required = true, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 機種コード
	 */
	@Size(max = 255)
	@NotNull
	@Column(nullable = false)
	@Schema(description = "機種コード", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String equipmentCode;

	/**
	 * 機番
	 */
	@Size(max = 255)
	@Schema(description = "機番", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String equipmentNo;

	/**
	 * 本体フラグ
	 */
	@Schema(description = "本体フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer bodyFlg;

	/**
	 * サービス機器フラグ
	 */
	@Schema(description = "サービス機器フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer serviceMachineFlg;

	/**
	 * 設置日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "設置日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date installationDate;

	/**
	 * 購入形態
	 */
	@Size(max = 255)
	@Schema(description = "購入形態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String purchaseForm;

	/**
	 * 納入形態
	 */
	@Size(max = 255)
	@Schema(description = "納入形態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String deliveryForm;

	/**
	 * 保守形態
	 */
	@Size(max = 255)
	@Schema(description = "保守形態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String maintenanceForm;

	/**
	 * 納入機器区分
	 */
	@Size(max = 255)
	@Schema(description = "納入機器区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String deliveryMachineDiv;

	/**
	 * メンテの注意(カナ)
	 */
	@Size(max = 1000)
	@Schema(description = "メンテの注意(カナ)", required = false, allowableValues = "range[0,1000]")
	private String maintenanceNoteKana;

	/**
	 * Isys-One 処理状態
	 */
	@Schema(description = "Isys-One 処理状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未処理(\"0\"),CSV作成済み(\"1\"),連携済み(\"2\"),連携エラー(\"3\"),対象外(\"4\"),管理対象外(\"5\")")
	private IsysoneProcStatus isysoneProcStatus;

	/**
	 * Isys-One 連携日時
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = UnixTimestampDateSerializer.class)
	@Schema(description = "Isys-One 連携日時(1時間毎に連携するため日時とする)", required = false)
	private Date isysoneLinkageAt;

	/**
	 * 契約
	 */
	@ManyToOne(optional = false)
	@JsonIgnore
	@JoinColumn(name = "contract_id", referencedColumnName = "id")
	@Schema(description = "契約", requiredMode = Schema.RequiredMode.REQUIRED)
	private Contract contract;

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
	@Schema(description = "点検診断月(12ヶ月分)", required = false, allowableValues = "range[0,255]")
	private String inspectionMonthYearWorth;

	/**
	 * Isys-One保守レポート処理状態
	 */
	@Schema(description = "Isys-One保守レポート処理状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未処理(\"0\"),CSV作成済み(\"1\"),連携済み(\"2\")")
	private IsysoneMaintereportProcStatus isysoneMaintereportProcStatus;

	/**
	 * Isys-One保守レポート連携日時
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = UnixTimestampDateSerializer.class)
	@Schema(description = "Isys-One保守レポート連携日時", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date isysoneMaintereportLinkageAt;

	/**
	 * ARCS期間売保守処理状態
	 */
	@Schema(description = "ARCS期間売保守処理状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未作成(\"0\"),作成済み(\"1\"),作成不要(\"2\"),管理対象外(\"3\")")
	private ArcsPeriodSaleMainteProcStatus arcsPeriodSaleMainteProcStatus;

	/**
	 * ARCS期間売保守連携日
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = UnixTimestampDateSerializer.class)
	@Schema(description = "ARCS期間売保守連携日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date arcsPeriodSaleMainteLinkageAt;

	/**
	 * 拡張項目
	 */
	@Schema(description = "拡張項目", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Lob
	private String extendsParameter;

	/**
	 * メーカーコード
	 */
	@Size(max = 255)
	@Schema(description = "メーカーコード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String makerCode;

	/**
	 * 分類コード
	 */
	@Size(max = 255)
	@Schema(description = "分類コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String classificationCode;

	/**
	 * 機器区分
	 */
	@Schema(description = "機器区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "サーバー本体(\"1\"),HWオプション(\"2\"),SWオプション(\"3\")")
	private MachineType machineType;

	/**
	 * 機種名
	 */
	@Size(max = 255)
	@Schema(description = "機種名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String equipmentName;

	/**
	 * 契約機種状態管理
	 */
	@Valid
	@OneToMany(mappedBy = "contractEquipment")
	@JsonIgnore
	@Schema(description = "契約機種状態管理", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<ManagedContractEquipmentStatus> managedContractEquipmentStatus;

	/**
	 * Isys-One再連携ステータス
	 */
	@Schema(description = "Isys-One再連携ステータス", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "再連携不要(\"0\"),再連携必要(\"1\"),再連携済(\"2\")")
	private IsysoneReLinkageStatus isysoneReLinkageStatus;

	/**
	 * Isys-One連携済機番
	 */
	@Size(max = 255)
	@Schema(description = "Isys-One連携済機番", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String isysoneLinkagedEquipmentNo;

	/**
	 * Isys-One連携済機種コード
	 */
	@Size(max = 255)
	@Schema(description = "Isys-One連携済機種コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String isysoneLinkagedEquipmentCode;

	/**
	 * ARCS期間売保守データ用保守形態
	 */
	@Size(max = 255)
	@Schema(description = "ARCS期間売保守データ用保守形態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String arcsMaintenanceForm;

	/**
	 * Isys-One連携済設置日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "Isys-One連携済設置日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date isysoneLinkagedInstallationDate;
}
