package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 商品マスタ
 */
@Entity
@Data
@ToString(exclude = { "itemMasterList" })
@EqualsAndHashCode(callSuper = true)
@Table(name = "product_master")
public class ProductMaster extends EntityBaseMaster {

	@Description(value = "シリアル連絡区分")
	public enum SerialContactDiv {

		新規のみ("1"), 新規と契約変更("2");

		private final String text;

		private SerialContactDiv(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static SerialContactDiv fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "RtoR設定可能区分")
	public enum RtorSettingPossibleDiv {

		引継ぎ先と引継ぎ元("1"), 引継ぎ元のみ("2");

		private final String text;

		private RtorSettingPossibleDiv(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static RtorSettingPossibleDiv fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_master_seq")
	@SequenceGenerator(name = "product_master_seq", sequenceName = "product_master_seq", allocationSize = 1)
	@Schema(description = "商品マスタID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 商品名
	 */
	@Column(nullable = false)
	@Schema(description = "商品名", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String productName;

	/**
	 * 代表品種マスタID
	 */
	@Schema(description = "代表品種マスタID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private Long repItemMasterId;

	/**
	 * 商品構成マスタ
	 */
	@OneToMany(mappedBy = "productMaster")
	@JsonIgnore
	@Schema(description = "商品構成マスタ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<ProductCompMaster> productCompMasterList;

	/**
	 * 品種マスタ
	 */
	@OneToMany(mappedBy = "productMaster")
	@Schema(description = "品種マスタ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<ItemMaster> itemMasterList;

	/**
	 * 見積チェックリスト構成マスタ
	 */
	@OneToMany(mappedBy = "productMaster")
	@Schema(description = "チェックリスト構成マスタ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<EstimationChecklistCompMaster> estimationChecklistCompMasterList;

	/**
	 * 契約チェックリスト構成マスタ
	 */
	@OneToMany(mappedBy = "productMaster")
	@Schema(description = "チェックリスト構成マスタ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<ContractChecklistCompMaster> contractChecklistCompMasterList;

	/**
	 * アプリケーションID
	 */
	@Size(max = 255)
	@Schema(description = "アプリケーションID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String appId;

	/**
	 * JSONスキーママスタID
	 */
	@Schema(description = "JSONスキーママスタID", requiredMode = Schema.RequiredMode.REQUIRED)
	private Long jsonSchemaMasterId;

	/**
	 * 拡張項目相関チェックマスタ
	 */
	@OneToMany(mappedBy = "productMaster", orphanRemoval = true, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(FetchMode.SUBSELECT)
	@Schema(description = "拡張項目相関チェックマスタ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<ExtendsParameterCorrelationCheckMaster> extendsParameterCorrelationCheckMasterList;

	/**
	 * 契約種類区分
	 */
	@Schema(description = "契約種類区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String contractClassDiv;

	/**
	 * 商品種類区分
	 */
	@Size(max = 255)
	@Schema(description = "商品種類区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String productClassDiv;

	/**
	 * IFSその他機器情報管理マスタ
	 */
	@OneToOne(mappedBy = "productMaster")
	@Schema(description = "IFSその他機器情報管理マスタ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private IfsCsvMaster ifsCsvMaster;

	/**
	 * 商品担当者マスタ
	 */
	@OneToMany(mappedBy = "productMaster")
	@Schema(description = "商品担当者マスタ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<ProductPicMaster> productPicMasterList;

	/**
	 * ファイル連携先
	 */
	@OneToMany(mappedBy = "productMaster")
	@Schema(description = "ファイル連携先", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<AttachedFileLinkage> attachedFileLinkageList;

	/**
	 * ベンダー商品マスタ
	 */
	@OneToOne(mappedBy = "productMaster")
	@Schema(description = "ベンダー商品マスタ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private VendorProductMaster vendorProductMaster;

	/**
	 * 商品拡張項目マスタ
	 */
	@OneToMany(mappedBy = "productMaster")
	@Schema(description = "商品拡張項目マスタ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<ProductExtendsParameterMaster> productExtendsParameterMasterList;

	/**
	 * 固有項目区分
	 */
	@Size(max = 255)
	@Schema(description = "固有項目区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String extendsParameterDiv;

	/**
	 * 決裁者フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "決裁者フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer approverFlg;

	/**
	 * 統合契約用種類区分
	 */
	@Schema(description = "統合契約用種類区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String integrationContractClassDiv;

	/**
	 * 違約金有無フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "違約金有無フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer penaltyFlg;

	/**
	 * 最低契約月数
	 */
	@Max(999)
	@Min(0)
	@Schema(description = "最低契約月数", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,999]")
	private Integer minContractMonths;

	/**
	 * 最長契約月数
	 */
	@Max(99999)
	@Min(0)
	@Schema(description = "最長契約月数", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,99999]")
	private Integer maxContractMonths;

	/**
	 * 仕入先No
	 */
	@Size(max = 255)
	@Schema(description = "仕入先No", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String siiresakiNo;

	/**
	 * ARCS契約種類区分
	 */
	@Size(max = 255)
	@Schema(description = "ARCS契約種類区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String arcsContractClassDiv;

	/**
	 * 訪販代売禁止フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "訪販代売禁止フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer substituteSalesProhibitedFlg;

	/**
	 * 契約変更時商流変更可能フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "契約変更時商流変更可能フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer commercialFlowDivPlanChangeableFlg;

	/**
	 * ヤマト便有無フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "ヤマト便有無フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer yamatoFlg;

	/**
	 * シリアル連絡URL
	 */
	@Size(max = 255)
	@Schema(description = "シリアル連絡URL", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String serialContactUrl;

	/**
	 * シリアル連絡区分
	 */
	@Schema(description = "シリアル連絡区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "新規のみ(\"1\"), 新規と契約変更(\"2\")", example = "1")
	private SerialContactDiv serialContactDiv;

	/**
	 * 正式商品名
	 */
	@Column(nullable = false)
	@Schema(description = "正式商品名", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String officialProductName;

	/**
	 * RtoR設定可能区分
	 */
	@Schema(description = "RtoR設定可能区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "引継ぎ先と引継ぎ元(\"1\"), 引継ぎ元のみ(\"2\")")
	private RtorSettingPossibleDiv rtorSettingPossibleDiv;

	/**
	 * サービスコード
	 */
	@Schema(description = "サービスコード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String serviceCode;

	/**
	 * ISMAP商品フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "ISMAP商品フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer ismapProductFlg;

	/**
	 * 情報変更禁止開始日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "情報変更禁止開始日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date infoChangeProhibitedPeriodFrom;

	/**
	 * 情報変更禁止終了日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "情報変更禁止終了日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date infoChangeProhibitedPeriodTo;

	/**
	 * 契約変更禁止開始日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "契約変更禁止開始日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date contractChangeProhibitedPeriodFrom;

	/**
	 * 契約変更禁止終了日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "契約変更禁止終了日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date contractChangeProhibitedPeriodTo;

	/**
	 * サブ商品種類区分
	 */
	@Schema(description = "サブ商品種類区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String subProductClassDiv;

	/**
	 * 品種明細情報取込フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "品種明細情報取込フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer itemDetailInfoImportFlg;

	/**
	 * 取込ファイルバージョン
	 */
	@Schema(description = "取込ファイルバージョン", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private Long importFileVersion;

	/**
	 * 取込ファイル格納URL
	 */
	@Size(max = 1000)
	@Schema(description = "取込ファイル格納URL", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,1000]")
	private String importFileUrl;
}
