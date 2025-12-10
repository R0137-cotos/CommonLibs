package jp.co.ricoh.cotos.commonlib.entity.externallinkage;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 工事調整EIM申込情報を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "construction_eim_apply_info")
public class ConstructionEimApplyInfo extends EntityBase {

	@Description(value = "契約種別詳細")
	public enum OrderTypeDetail {
		移転, 同一フロア内移転, 終端装置変更
	}

	@Description(value = "設置先NTT事業者")
	public enum LineEastWest {
		東日本, 西日本
	}

	@Description(value = "回線メニュー")
	public enum LineMenu {
		スタンダードメニュー, アクセスメニュー
	}

	@Description(value = "変更前設置先NTT事業者")
	public enum BeforeLineEastWest {
		東日本, 西日本
	}

	@Description(value = "変更前回線メニュー")
	public enum BeforeLineMenu {
		スタンダードメニュー, アクセスメニュー
	}

	@Description(value = "設置先工事種別")
	public enum InstallationWorkType {
		平日, 土休日, 無派遣
	}

	@Description(value = "配線ルート構築")
	public enum RootConstruction {
		なし, 工事と同日, 工事と別日
	}

	@Description(value = "設置元工事種別")
	public enum OriginInstallationWorkType {
		平日, 土休日, 無派遣
	}

	@Description(value = "R割適用有無")
	public enum RdiscountDiv {
		通常, R割, ダブルR割
	}

	@Description(value = "ステータス")
	public enum Status {

		未取込("0"), 取込済("1"), 取込エラー("2");

		private final String text;

		private Status(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static Status fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "注文種別")
	public enum OrderType {

		転用("01"), 転用_タイプ変更("02"), タイプ変更("03"), 新規("04"), 移転("05"), 同一フロア内移転("06"), 終端装置変更("07"), ひかり電話変更("08"), 転入("09"), 転出("10"), NTT名義変更("11"), 中断("12"), 再開("13"), ひかり電話追加("14"), ひかり電話削除("15"), 契約_オプション変更("16"), 解約("17"), 転入_タイプ変更("18"), メニュー変更("19"), R割適用("20");

		private final String text;

		private OrderType(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static OrderType fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * 工事調整EIM申込情報ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "construction_eim_apply_info_seq")
	@SequenceGenerator(name = "construction_eim_apply_info_seq", sequenceName = "construction_eim_apply_info_seq", allocationSize = 1)
	@Schema(description = "工事調整EIM申込情報ID(作成時不要)", required = true, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 見積ID
	 */
	@Min(0)
	@Schema(description = "見積ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private Long estimationId;

	/**
	 * 契約ID
	 */
	@Min(0)
	@Schema(description = "契約ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private Long contractId;

	/**
	 * 解約フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "解約フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer disengagementFlg;

	/**
	 * 引継ぎ解約フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "引継ぎ解約フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer handoverDisengagementFlg;

	/**
	 * 引継ぎ元契約ID
	 */
	@Min(0)
	@Schema(description = "引継ぎ元契約ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private Long handoverOriginContractId;

	/**
	 * ステータス
	 */
	@Schema(description = "ステータス", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未取込(\"0\"), 取込済(\"1\"), 取込エラー(\"2\")")
	private Status status;

	/**
	 * 管理番号
	 */
	@Size(max = 255)
	@Schema(description = "管理番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String managementNo;

	/**
	 * 工事調整完了番号
	 */
	@Size(max = 255)
	@Schema(description = "工事調整完了番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String constructAdjustmentNumber;

	/**
	 * 対象
	 */
	@Size(max = 255)
	@Schema(description = "対象", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String target;

	/**
	 * 注文種別
	 */
	@Schema(description = "注文種別", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "転用(\"01\"), 転用+タイプ変更(\"02\"), タイプ変更(\"03\"), 新規(\"04\"), 移転(\"05\"), 同一フロア内移転(\"06\"), 終端装置変更(\"07\"), ひかり電話変更(\"08\"), 転入(\"09\"), 転出(\"10\"), NTT名義変更(\"11\"), 中断(\"12\"), 再開(\"13\"), ひかり電話追加(\"14\"), ひかり電話削除(\"15\"), 契約_オプション変更(\"16\"), 解約(\"17\"), 転入 + タイプ変更(\"18\"), メニュー変更(\"19\"), R割適用(\"20\")")
	private OrderType orderType;

	/**
	 * 注文種別詳細
	 */
	@Schema(description = "注文種別詳細", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "移転, 同一フロア内移転, 終端装置変更")
	@Enumerated(EnumType.STRING)
	private OrderTypeDetail orderTypeDetail;

	/**
	 * ひかり電話注文種別
	 */
	@Size(max = 255)
	@Schema(description = "ひかり電話注文種別", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String opticalPhoneOrderType;

	/**
	 * 契約・オプション
	 */
	@Size(max = 255)
	@Schema(description = "契約・オプション", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String contractAndOptionChanges;

	/**
	 * 工事調整EIM文書URL
	 */
	@Size(max = 1000)
	@Schema(description = "工事調整EIM文書URL", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,1000]")
	private String constructionEimUrl;

	/**
	 * NTTお客様ID
	 */
	@Size(max = 255)
	@Schema(description = "NTTお客様ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String nttCustomerId;

	/**
	 * 設置先NTT事業者
	 */
	@Schema(description = "設置先NTT事業者", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "東日本, 西日本")
	@Enumerated(EnumType.STRING)
	private LineEastWest lineEastWest;

	/**
	 * 設置先建物種別
	 */
	@Size(max = 255)
	@Schema(description = "設置先建物種別", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String installationBuildingType;

	/**
	 * 回線メニュー
	 */
	@Schema(description = "回線メニュー", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "スタンダードメニュー, アクセスメニュー")
	@Enumerated(EnumType.STRING)
	private LineMenu lineMenu;

	/**
	 * 回線種別
	 */
	@Size(max = 255)
	@Schema(description = "回線種別", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String lineType;

	/**
	 * 回線リコー品種コード
	 */
	@Size(max = 255)
	@Schema(description = "回線リコー品種コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String lineRicohItemCode;

	/**
	 * 変更前NTTお客様ID
	 */
	@Size(max = 255)
	@Schema(description = "変更前NTTお客様ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String beforeNttCustomerId;

	/**
	 * 変更前設置先NTT事業者
	 */
	@Schema(description = "変更前設置先NTT事業者", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "東日本, 西日本")
	@Enumerated(EnumType.STRING)
	private BeforeLineEastWest beforeLineEastWest;

	/**
	 * 変更前回線メニュー
	 */
	@Schema(description = "変更前回線メニュー", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "スタンダードメニュー, アクセスメニュー")
	@Enumerated(EnumType.STRING)
	private BeforeLineMenu beforeLineMenu;

	/**
	 * 変更前回線種別
	 */
	@Size(max = 255)
	@Schema(description = "変更前回線種別", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String beforeLineType;

	/**
	 * 設置先工事種別
	 */
	@Schema(description = "設置先工事種別", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "平日, 土休日, 無派遣")
	@Enumerated(EnumType.STRING)
	private InstallationWorkType installationWorkType;

	/**
	 * 配線ルート構築
	 */
	@Schema(description = "配線ルート構築", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "なし, 工事と同日, 工事と別日")
	@Enumerated(EnumType.STRING)
	private RootConstruction rootConstruction;

	/**
	 * 設置先リコーひかり工事日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "設置先リコーひかり工事日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date lineConstructionDate;

	/**
	 * 設置先リコーひかり工事日時（時間）
	 */
	@Size(max = 255)
	@Schema(description = "設置先リコーひかり工事日時（時間）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String lineConstructionTime;

	/**
	 * 設置元工事種別
	 */
	@Schema(description = "設置元工事種別", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "平日, 土休日, 無派遣")
	@Enumerated(EnumType.STRING)
	private OriginInstallationWorkType originInstallationWorkType;

	/**
	 * 設置元リコーひかり工事日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "設置元リコーひかり工事日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date originLineConstructionDate;

	/**
	 * 設置元リコーひかり工事日時（時間）
	 */
	@Size(max = 255)
	@Schema(description = "設置元リコーひかり工事日時（時間）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String originLineConstructionTime;

	/**
	 * ひかり電話工事日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "ひかり電話工事日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date phoneConstructionDate;

	/**
	 * ひかり電話工事日時（時間）
	 */
	@Size(max = 255)
	@Schema(description = "ひかり電話工事日時（時間）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String phoneConstructionTime;

	/**
	 * 月額割引開始月
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "月額割引開始月", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date monthlyDiscountStartMonth;

	/**
	 * 月額割引回数
	 */
	@Max(99999)
	@Min(-99999)
	@Schema(description = "月額割引回数", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[-99999,99999]")
	private Integer monthlyDiscountCount;

	/**
	 * 月額割引月額
	 */
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "月額割引月額", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal monthlyDiscountMonthly;

	/**
	 * 合計残債
	 */
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "合計残債", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal totalRemainingDebt;

	/**
	 * 契約者企事部ID
	 */
	@Size(max = 255)
	@Schema(description = "契約者企事部ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String contractorKjbId;

	/**
	 * 郵便番号
	 */
	@Size(max = 255)
	@Schema(description = "郵便番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String companyZipCode;

	/**
	 * 住所
	 */
	@Size(max = 255)
	@Schema(description = "住所", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String companyAddress;

	/**
	 * 代表者名（フリガナ）
	 */
	@Size(max = 255)
	@Schema(description = "代表者名（フリガナ）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String representativeNameKana;

	/**
	 * 代表者名（漢字）
	 */
	@Size(max = 255)
	@Schema(description = "代表者名（漢字）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String representativeName;

	/**
	 * 担当者部署名（フリガナ）
	 */
	@Size(max = 255)
	@Schema(description = "担当者部署名（フリガナ）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String picDepartmentNameKana;

	/**
	 * 担当者部署名（漢字）
	 */
	@Size(max = 255)
	@Schema(description = "担当者部署名（漢字）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String picDepartmentName;

	/**
	 * 担当者名（フリガナ）
	 */
	@Size(max = 255)
	@Schema(description = "担当者名（フリガナ）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String picNameKana;

	/**
	 * 担当者名（漢字）
	 */
	@Size(max = 255)
	@Schema(description = "担当者名（漢字）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String picName;

	/**
	 * 担当者電話番号
	 */
	@Size(max = 255)
	@Schema(description = "担当者電話番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String picPhoneNumber;

	/**
	 * 担当者メールアドレス
	 */
	@Size(max = 255)
	@Schema(description = "担当者メールアドレス", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String picMailAddress;

	/**
	 * 販社企事部ID
	 */
	@Size(max = 255)
	@Schema(description = "販社企事部ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String dealerKjbId;

	/**
	 * 販売会社名（フリガナ）
	 */
	@Size(max = 255)
	@Schema(description = "販売会社名（フリガナ）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String dealerNameKana;

	/**
	 * 営業社員番号
	 */
	@Size(max = 255)
	@Schema(description = "営業社員番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String salesEmployeeNumber;

	/**
	 * 責任者（フリガナ）
	 */
	@Size(max = 255)
	@Schema(description = "責任者（フリガナ）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String salesResponsibleNameKana;

	/**
	 * 責任者（漢字）
	 */
	@Size(max = 255)
	@Schema(description = "責任者（漢字）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String salesResponsibleName;

	/**
	 * 保守SS課所コード
	 */
	@Size(max = 255)
	@Schema(description = "保守SS課所コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String mntSsOrgCode;

	/**
	 * 保守CE社員番号
	 */
	@Size(max = 255)
	@Schema(description = "保守CE社員番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String mntCeEmployeeNumber;

	/**
	 * 契約者情報に同じフラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "契約者情報に同じフラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer sameContractorFlg;

	/**
	 * 設置先企事部ID
	 */
	@Size(max = 255)
	@Schema(description = "設置先企事部ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String installationKjbId;

	/**
	 * 設置先郵便番号
	 */
	@Size(max = 255)
	@Schema(description = "設置先郵便番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String installationZipCode;

	/**
	 * 設置先住所
	 */
	@Size(max = 255)
	@Schema(description = "設置先住所", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String installationAddress;

	/**
	 * 設置先建物名
	 */
	@Size(max = 255)
	@Schema(description = "設置先建物名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String installationBuildingName;

	/**
	 * 設置先フロア
	 */
	@Size(max = 255)
	@Schema(description = "設置先フロア", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String installationFloor;

	/**
	 * 設置先担当者名（漢字）
	 */
	@Size(max = 255)
	@Schema(description = "設置先担当者名（漢字）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String installationPicName;

	/**
	 * 設置先担当者電話番号
	 */
	@Size(max = 255)
	@Schema(description = "設置先担当者電話番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String installationPicPhoneNumber;

	/**
	 * Net RICOH ID（メールアドレス）
	 */
	@Size(max = 255)
	@Schema(description = "Net RICOH ID（メールアドレス）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String netRicohId;

	/**
	 * 上流システムコード
	 */
	@Size(max = 255)
	@Schema(description = "上流システムコード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String originalSystemCode;

	/**
	 * 回収方法名
	 */
	@Size(max = 255)
	@Schema(description = "回収方法名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String collectionMethodName;

	/**
	 * R割適用有無
	 */
	@Schema(description = "R割適用有無", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "通常, R割, ダブルR割")
	@Enumerated(EnumType.STRING)
	private RdiscountDiv rdiscountDiv;

	/**
	 * R割対象商品
	 */
	@Size(max = 255)
	@Schema(description = "R割対象商品", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String rdiscountTargetProduct;

	/**
	 * R割対象商品契約ID
	 */
	@Size(max = 255)
	@Schema(description = "R割対象商品契約ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String rdiscountTargetContractId;

	/**
	 * メールアドレスアドレス（第一希望）
	 */
	@Size(max = 255)
	@Schema(description = "メールアドレスアドレス（第一希望）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String firstChoiceMailAddress;

	/**
	 * メールアドレスアドレス（第二希望）
	 */
	@Size(max = 255)
	@Schema(description = "メールアドレスアドレス（第二希望）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String secondChoiceMailAddress;

	/**
	 * 電話番号1
	 */
	@Size(max = 255)
	@Column(name = "phone_number_1")
	@Schema(description = "電話番号1", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String phoneNumber1;

	/**
	 * 代表フラグ1
	 */
	@Max(9)
	@Min(0)
	@Column(name = "representative_flg_1")
	@Schema(description = "代表フラグ1", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer representativeFlg1;

	/**
	 * 電話番号2
	 */
	@Size(max = 255)
	@Column(name = "phone_number_2")
	@Schema(description = "電話番号2", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String phoneNumber2;

	/**
	 * 代表フラグ2
	 */
	@Max(9)
	@Min(0)
	@Column(name = "representative_flg_2")
	@Schema(description = "代表フラグ2", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer representativeFlg2;

	/**
	 * 電話番号3
	 */
	@Size(max = 255)
	@Column(name = "phone_number_3")
	@Schema(description = "電話番号3", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String phoneNumber3;

	/**
	 * 代表フラグ3
	 */
	@Max(9)
	@Min(0)
	@Column(name = "representative_flg_3")
	@Schema(description = "代表フラグ3", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer representativeFlg3;

	/**
	 * 電話番号4
	 */
	@Size(max = 255)
	@Column(name = "phone_number_4")
	@Schema(description = "電話番号4", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String phoneNumber4;

	/**
	 * 代表フラグ4
	 */
	@Max(9)
	@Min(0)
	@Column(name = "representative_flg_4")
	@Schema(description = "代表フラグ4", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer representativeFlg4;

	/**
	 * 電話番号5
	 */
	@Size(max = 255)
	@Column(name = "phone_number_5")
	@Schema(description = "電話番号5", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String phoneNumber5;

	/**
	 * 代表フラグ5
	 */
	@Max(9)
	@Min(0)
	@Column(name = "representative_flg_5")
	@Schema(description = "代表フラグ5", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer representativeFlg5;

	/**
	 * 電話番号6
	 */
	@Size(max = 255)
	@Column(name = "phone_number_6")
	@Schema(description = "電話番号6", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String phoneNumber6;

	/**
	 * 代表フラグ6
	 */
	@Max(9)
	@Min(0)
	@Column(name = "representative_flg_6")
	@Schema(description = "代表フラグ6", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer representativeFlg6;

	/**
	 * 工事調整EIM申込品種情報
	 */
	@Valid
	@OneToMany(mappedBy = "constructionEimApplyInfo")
	@Schema(description = "工事調整EIM申込品種情報", requiredMode = Schema.RequiredMode.REQUIRED)
	private List<ConstructionEimItemInfo> constructionEimItemInfoList;

	/**
	 * キャンペーン対象
	 */
	@Size(max = 255)
	@Schema(description = "キャンペーン対象", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String campaignTarget;

}
