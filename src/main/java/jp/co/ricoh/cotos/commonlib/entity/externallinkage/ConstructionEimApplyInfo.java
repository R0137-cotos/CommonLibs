package jp.co.ricoh.cotos.commonlib.entity.externallinkage;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;
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

		転用("01"), 転用_タイプ変更("02"), タイプ変更("03"), 新規("04"), 移転("05"), 同一フロア内移転("06"), 終端装置変更("07"), ひかり電話変更("08"), 転入("09"), 転出("10"), NTT名義変更("11"), 中断("12"), 再開("13"), ひかり電話追加("14"), ひかり電話削除("15"), 契約_オプション変更("16"), 解約("17"), 転入_タイプ変更("18"), メニュー変更("19");

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
	@ApiModelProperty(value = "工事調整EIM申込情報ID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 見積ID
	 */
	@Min(0)
	@ApiModelProperty(value = "見積ID", required = false, position = 2, allowableValues = "range[0,9223372036854775807]")
	private Long estimationId;

	/**
	 * 契約ID
	 */
	@Min(0)
	@ApiModelProperty(value = "契約ID", required = false, position = 3, allowableValues = "range[0,9223372036854775807]")
	private Long contractId;

	/**
	 * 解約フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "解約フラグ", required = false, position = 4, allowableValues = "range[0,9]")
	private Integer disengagementFlg;

	/**
	 * 引継ぎ解約フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "引継ぎ解約フラグ", required = false, position = 5, allowableValues = "range[0,9]")
	private Integer handoverDisengagementFlg;

	/**
	 * 引継ぎ元契約ID
	 */
	@Min(0)
	@ApiModelProperty(value = "引継ぎ元契約ID", required = false, position = 6, allowableValues = "range[0,9223372036854775807]")
	private Long handoverOriginContractId;

	/**
	 * ステータス
	 */
	@ApiModelProperty(value = "ステータス", required = false, position = 7, allowableValues = "未取込(\"0\"), 取込済(\"1\"), 取込エラー(\"2\")")
	private Status status;

	/**
	 * 管理番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "管理番号", required = false, position = 8, allowableValues = "range[0,255]")
	private String managementNo;

	/**
	 * 工事調整完了番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "工事調整完了番号", required = false, position = 9, allowableValues = "range[0,255]")
	private String constructEimNumber;

	/**
	 * 対象
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "対象", required = false, position = 10, allowableValues = "range[0,255]")
	private String target;

	/**
	 * 注文種別
	 */
	@ApiModelProperty(value = "注文種別", required = false, position = 11, allowableValues = "転用(\"01\"), 転用+タイプ変更(\"02\"), タイプ変更(\"03\"), 新規(\"04\"), 移転(\"05\"), 同一フロア内移転(\"06\"), 終端装置変更(\"07\"), ひかり電話変更(\"08\"), 転入(\"09\"), 転出(\"10\"), NTT名義変更(\"11\"), 中断(\"12\"), 再開(\"13\"), ひかり電話追加(\"14\"), ひかり電話削除(\"15\"), 契約_オプション変更(\"16\"), 解約(\"17\"), 転入 + タイプ変更(\"18\"), メニュー変更(\"19\")")
	private OrderType orderType;

	/**
	 * 注文種別詳細
	 */
	@ApiModelProperty(value = "注文種別詳細", required = false, position = 12, allowableValues = "移転, 同一フロア内移転, 終端装置変更")
	@Enumerated(EnumType.STRING)
	private OrderTypeDetail orderTypeDetail;

	/**
	 * ひかり電話注文種別
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "ひかり電話注文種別", required = false, position = 13, allowableValues = "range[0,255]")
	private String opticalPhoneOrderType;

	/**
	 * 契約・オプション
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "契約・オプション", required = false, position = 14, allowableValues = "range[0,255]")
	private String contractAndOptionChanges;

	/**
	 * 工事調整EIM文書URL
	 */
	@Size(max = 1000)
	@ApiModelProperty(value = "工事調整EIM文書URL", required = false, position = 15, allowableValues = "range[0,1000]")
	private String constructionEimUrl;

	/**
	 * NTTお客様ID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "NTTお客様ID", required = false, position = 16, allowableValues = "range[0,255]")
	private String nttCustomerId;

	/**
	 * 設置先NTT事業者
	 */
	@ApiModelProperty(value = "設置先NTT事業者", required = false, position = 17, allowableValues = "東日本, 西日本")
	@Enumerated(EnumType.STRING)
	private LineEastWest lineEastWest;

	/**
	 * 設置先建物種別
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "設置先建物種別", required = false, position = 18, allowableValues = "range[0,255]")
	private String installationBuildingType;

	/**
	 * 回線メニュー
	 */
	@ApiModelProperty(value = "回線メニュー", required = false, position = 19, allowableValues = "スタンダードメニュー, アクセスメニュー")
	@Enumerated(EnumType.STRING)
	private LineMenu lineMenu;

	/**
	 * 回線種別
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "回線種別", required = false, position = 20, allowableValues = "range[0,255]")
	private String lineType;

	/**
	 * 回線リコー品種コード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "回線リコー品種コード", required = false, position = 21, allowableValues = "range[0,255]")
	private String lineRicohItemCode;

	/**
	 * 変更前NTTお客様ID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "変更前NTTお客様ID", required = false, position = 22, allowableValues = "range[0,255]")
	private String beforeNttCustomerId;

	/**
	 * 変更前設置先NTT事業者
	 */
	@ApiModelProperty(value = "変更前設置先NTT事業者", required = false, position = 23, allowableValues = "東日本, 西日本")
	@Enumerated(EnumType.STRING)
	private BeforeLineEastWest beforeLineEastWest;

	/**
	 * 変更前回線メニュー
	 */
	@ApiModelProperty(value = "変更前回線メニュー", required = false, position = 24, allowableValues = "スタンダードメニュー, アクセスメニュー")
	@Enumerated(EnumType.STRING)
	private BeforeLineMenu beforeLineMenu;

	/**
	 * 変更前回線種別
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "変更前回線種別", required = false, position = 25, allowableValues = "range[0,255]")
	private String beforeLineType;

	/**
	 * 設置先工事種別
	 */
	@ApiModelProperty(value = "設置先工事種別", required = false, position = 26, allowableValues = "平日, 土休日, 無派遣")
	@Enumerated(EnumType.STRING)
	private InstallationWorkType installationWorkType;

	/**
	 * 配線ルート構築
	 */
	@ApiModelProperty(value = "配線ルート構築", required = false, position = 27, allowableValues = "なし, 工事と同日, 工事と別日")
	@Enumerated(EnumType.STRING)
	private RootConstruction rootConstruction;

	/**
	 * 設置先リコーひかり工事日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "設置先リコーひかり工事日", required = false, position = 28)
	private Date lineConstructionDate;

	/**
	 * 設置先リコーひかり工事日時（時間）
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "設置先リコーひかり工事日時（時間）", required = false, position = 29, allowableValues = "range[0,255]")
	private String lineConstructionTime;

	/**
	 * 設置元工事種別
	 */
	@ApiModelProperty(value = "設置元工事種別", required = false, position = 30, allowableValues = "平日, 土休日, 無派遣")
	@Enumerated(EnumType.STRING)
	private OriginInstallationWorkType originInstallationWorkType;

	/**
	 * 設置元リコーひかり工事日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "設置元リコーひかり工事日", required = false, position = 31)
	private Date originLineConstructionDate;

	/**
	 * 設置元リコーひかり工事日時（時間）
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "設置元リコーひかり工事日時（時間）", required = false, position = 32, allowableValues = "range[0,255]")
	private String originLineConstructionTime;

	/**
	 * ひかり電話工事日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "ひかり電話工事日", required = false, position = 33)
	private Date phoneConstructionDate;

	/**
	 * ひかり電話工事日時（時間）
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "ひかり電話工事日時（時間）", required = false, position = 34, allowableValues = "range[0,255]")
	private String phoneConstructionTime;

	/**
	 * 月額割引開始月
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "月額割引開始月", required = false, position = 35)
	private Date monthlyDiscountStartMonth;

	/**
	 * 月額割引回数
	 */
	@Max(99999)
	@Min(-99999)
	@ApiModelProperty(value = "月額割引回数", required = false, position = 36, allowableValues = "range[-99999,99999]")
	private Integer monthlyDiscountCount;

	/**
	 * 月額割引月額
	 */
	@DecimalMax("9999999999999999999.99")
	@ApiModelProperty(value = "月額割引月額", required = false, position = 37, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal monthlyDiscountMonthly;

	/**
	 * 合計残債
	 */
	@DecimalMax("9999999999999999999.99")
	@ApiModelProperty(value = "合計残債", required = false, position = 38, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal totalRemainingDebt;

	/**
	 * 契約者企事部ID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "契約者企事部ID", required = false, position = 39, allowableValues = "range[0,255]")
	private String contractorKjbId;

	/**
	 * 郵便番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "郵便番号", required = false, position = 40, allowableValues = "range[0,255]")
	private String companyZipCode;

	/**
	 * 住所
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "住所", required = false, position = 41, allowableValues = "range[0,255]")
	private String companyAddress;

	/**
	 * 代表者名（フリガナ）
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "代表者名（フリガナ）", required = false, position = 42, allowableValues = "range[0,255]")
	private String representativeNameKana;

	/**
	 * 代表者名（漢字）
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "代表者名（漢字）", required = false, position = 43, allowableValues = "range[0,255]")
	private String representativeName;

	/**
	 * 担当者部署名（フリガナ）
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "担当者部署名（フリガナ）", required = false, position = 44, allowableValues = "range[0,255]")
	private String picDepartmentNameKana;

	/**
	 * 担当者部署名（漢字）
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "担当者部署名（漢字）", required = false, position = 45, allowableValues = "range[0,255]")
	private String picDepartmentName;

	/**
	 * 担当者名（フリガナ）
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "担当者名（フリガナ）", required = false, position = 46, allowableValues = "range[0,255]")
	private String picNameKana;

	/**
	 * 担当者名（漢字）
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "担当者名（漢字）", required = false, position = 47, allowableValues = "range[0,255]")
	private String picName;

	/**
	 * 担当者電話番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "担当者電話番号", required = false, position = 48, allowableValues = "range[0,255]")
	private String picPhoneNumber;

	/**
	 * 担当者メールアドレス
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "担当者メールアドレス", required = false, position = 49, allowableValues = "range[0,255]")
	private String picMailAddress;

	/**
	 * 販社企事部ID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "販社企事部ID", required = false, position = 50, allowableValues = "range[0,255]")
	private String dealerKjbId;

	/**
	 * 販売会社名（フリガナ）
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "販売会社名（フリガナ）", required = false, position = 51, allowableValues = "range[0,255]")
	private String dealerNameKana;

	/**
	 * 営業社員番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "営業社員番号", required = false, position = 52, allowableValues = "range[0,255]")
	private String salesEmployeeNumber;

	/**
	 * 責任者（フリガナ）
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "責任者（フリガナ）", required = false, position = 53, allowableValues = "range[0,255]")
	private String salesResponsibleNameKana;

	/**
	 * 責任者（漢字）
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "責任者（漢字）", required = false, position = 54, allowableValues = "range[0,255]")
	private String salesResponsibleName;

	/**
	 * 保守SS課所コード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "保守SS課所コード", required = false, position = 55, allowableValues = "range[0,255]")
	private String mntSsOrgCode;

	/**
	 * 保守CE社員番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "保守CE社員番号", required = false, position = 56, allowableValues = "range[0,255]")
	private String mntCeEmployeeNumber;

	/**
	 * 契約者情報に同じフラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "契約者情報に同じフラグ", required = false, position = 57, allowableValues = "range[0,9]")
	private Integer sameContractorFlg;

	/**
	 * 設置先企事部ID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "設置先企事部ID", required = false, position = 58, allowableValues = "range[0,255]")
	private String installationKjbId;

	/**
	 * 設置先郵便番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "設置先郵便番号", required = false, position = 59, allowableValues = "range[0,255]")
	private String installationZipCode;

	/**
	 * 設置先住所
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "設置先住所", required = false, position = 60, allowableValues = "range[0,255]")
	private String installationAddress;

	/**
	 * 設置先建物名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "設置先建物名", required = false, position = 61, allowableValues = "range[0,255]")
	private String installationBuildingName;

	/**
	 * 設置先フロア
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "設置先フロア", required = false, position = 62, allowableValues = "range[0,255]")
	private String installationFloor;

	/**
	 * 設置先担当者名（漢字）
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "設置先担当者名（漢字）", required = false, position = 63, allowableValues = "range[0,255]")
	private String installationPicName;

	/**
	 * 設置先担当者電話番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "設置先担当者電話番号", required = false, position = 64, allowableValues = "range[0,255]")
	private String installationPicPhoneNumber;

	/**
	 * Net RICOH ID（メールアドレス）
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "Net RICOH ID（メールアドレス）", required = false, position = 65, allowableValues = "range[0,255]")
	private String netRicohId;

	/**
	 * 上流システムコード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "上流システムコード", required = false, position = 66, allowableValues = "range[0,255]")
	private String originalSystemCode;

	/**
	 * 回収方法名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "回収方法名", required = false, position = 67, allowableValues = "range[0,255]")
	private String collectionMethodName;

	/**
	 * R割適用有無
	 */
	@ApiModelProperty(value = "R割適用有無", required = false, position = 68, allowableValues = "通常, R割, ダブルR割")
	@Enumerated(EnumType.STRING)
	private RdiscountDiv rdiscountDiv;

	/**
	 * R割対象商品
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "R割対象商品", required = false, position = 69, allowableValues = "range[0,255]")
	private String rdiscountTargetProduct;

	/**
	 * R割対象商品契約ID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "R割対象商品契約ID", required = false, position = 70, allowableValues = "range[0,255]")
	private String rdiscountTargetContractId;

	/**
	 * メールアドレスアドレス（第一希望）
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "メールアドレスアドレス（第一希望）", required = false, position = 71, allowableValues = "range[0,255]")
	private String firstChoiceMailAddress;

	/**
	 * メールアドレスアドレス（第二希望）
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "メールアドレスアドレス（第二希望）", required = false, position = 72, allowableValues = "range[0,255]")
	private String secondChoiceMailAddress;

	/**
	 * 電話番号1
	 */
	@Size(max = 255)
	@Column(name = "phone_number_1")
	@ApiModelProperty(value = "電話番号1", required = false, position = 73, allowableValues = "range[0,255]")
	private String phoneNumber1;

	/**
	 * 代表フラグ1
	 */
	@Max(9)
	@Min(0)
	@Column(name = "representative_flg_1")
	@ApiModelProperty(value = "代表フラグ1", required = false, position = 74, allowableValues = "range[0,9]")
	private Integer representativeFlg1;

	/**
	 * 電話番号2
	 */
	@Size(max = 255)
	@Column(name = "phone_number_2")
	@ApiModelProperty(value = "電話番号2", required = false, position = 75, allowableValues = "range[0,255]")
	private String phoneNumber2;

	/**
	 * 代表フラグ2
	 */
	@Max(9)
	@Min(0)
	@Column(name = "representative_flg_2")
	@ApiModelProperty(value = "代表フラグ2", required = false, position = 76, allowableValues = "range[0,9]")
	private Integer representativeFlg2;

	/**
	 * 電話番号3
	 */
	@Size(max = 255)
	@Column(name = "phone_number_3")
	@ApiModelProperty(value = "電話番号3", required = false, position = 77, allowableValues = "range[0,255]")
	private String phoneNumber3;

	/**
	 * 代表フラグ3
	 */
	@Max(9)
	@Min(0)
	@Column(name = "representative_flg_3")
	@ApiModelProperty(value = "代表フラグ3", required = false, position = 78, allowableValues = "range[0,9]")
	private Integer representativeFlg3;

	/**
	 * 電話番号4
	 */
	@Size(max = 255)
	@Column(name = "phone_number_4")
	@ApiModelProperty(value = "電話番号4", required = false, position = 79, allowableValues = "range[0,255]")
	private String phoneNumber4;

	/**
	 * 代表フラグ4
	 */
	@Max(9)
	@Min(0)
	@Column(name = "representative_flg_4")
	@ApiModelProperty(value = "代表フラグ4", required = false, position = 80, allowableValues = "range[0,9]")
	private Integer representativeFlg4;

	/**
	 * 電話番号5
	 */
	@Size(max = 255)
	@Column(name = "phone_number_5")
	@ApiModelProperty(value = "電話番号5", required = false, position = 81, allowableValues = "range[0,255]")
	private String phoneNumber5;

	/**
	 * 代表フラグ5
	 */
	@Max(9)
	@Min(0)
	@Column(name = "representative_flg_5")
	@ApiModelProperty(value = "代表フラグ5", required = false, position = 82, allowableValues = "range[0,9]")
	private Integer representativeFlg5;

	/**
	 * 電話番号6
	 */
	@Size(max = 255)
	@Column(name = "phone_number_6")
	@ApiModelProperty(value = "電話番号6", required = false, position = 83, allowableValues = "range[0,255]")
	private String phoneNumber6;

	/**
	 * 代表フラグ6
	 */
	@Max(9)
	@Min(0)
	@Column(name = "representative_flg_6")
	@ApiModelProperty(value = "代表フラグ6", required = false, position = 84, allowableValues = "range[0,9]")
	private Integer representativeFlg6;

	/**
	 * 工事調整EIM申込品種情報
	 */
	@Valid
	@OneToMany(mappedBy = "constructionEimApplyInfo")
	@ApiModelProperty(value = "工事調整EIM申込品種情報", required = true, position = 85)
	private List<ConstructionEimItemInfo> constructionEimItemInfoList;
}
