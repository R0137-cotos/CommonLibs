package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(value = "商品マスタID", required = true, position = 1, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 商品名
	 */
	@Column(nullable = false)
	@ApiModelProperty(value = "商品名", required = true, position = 2, allowableValues = "range[0,255]")
	private String productName;

	/**
	 * 代表品種マスタID
	 */
	@ApiModelProperty(value = "代表品種マスタID", required = false, position = 3, allowableValues = "range[0,9999999999999999999]")
	private Long repItemMasterId;

	/**
	 * 商品構成マスタ
	 */
	@OneToMany(mappedBy = "productMaster")
	@JsonIgnore
	@ApiModelProperty(value = "商品構成マスタ", required = false, position = 4)
	private List<ProductCompMaster> productCompMasterList;

	/**
	 * 品種マスタ
	 */
	@OneToMany(mappedBy = "productMaster")
	@ApiModelProperty(value = "品種マスタ", required = false, position = 5)
	private List<ItemMaster> itemMasterList;

	/**
	 * 見積チェックリスト構成マスタ
	 */
	@OneToMany(mappedBy = "productMaster")
	@ApiModelProperty(value = "チェックリスト構成マスタ", required = false, position = 6)
	private List<EstimationChecklistCompMaster> estimationChecklistCompMasterList;

	/**
	 * 契約チェックリスト構成マスタ
	 */
	@OneToMany(mappedBy = "productMaster")
	@ApiModelProperty(value = "チェックリスト構成マスタ", required = false, position = 7)
	private List<ContractChecklistCompMaster> contractChecklistCompMasterList;

	/**
	 * アプリケーションID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "アプリケーションID", required = false, position = 8, allowableValues = "range[0,255]")
	private String appId;

	/**
	 * JSONスキーママスタID
	 */
	@ApiModelProperty(value = "JSONスキーママスタID", required = true, position = 9)
	private Long jsonSchemaMasterId;

	/**
	 * 拡張項目相関チェックマスタ
	 */
	@OneToMany(mappedBy = "productMaster", orphanRemoval = true, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(FetchMode.SUBSELECT)
	@ApiModelProperty(value = "拡張項目相関チェックマスタ", required = false, position = 10)
	private List<ExtendsParameterCorrelationCheckMaster> extendsParameterCorrelationCheckMasterList;

	/**
	 * 契約種類区分
	 */
	@ApiModelProperty(value = "契約種類区分", required = false, position = 11, allowableValues = "range[0,255]")
	private String contractClassDiv;

	/**
	 * 商品種類区分
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "商品種類区分", required = false, position = 12, allowableValues = "range[0,255]")
	private String productClassDiv;

	/**
	 * IFSその他機器情報管理マスタ
	 */
	@OneToOne(mappedBy = "productMaster")
	@ApiModelProperty(value = "IFSその他機器情報管理マスタ", required = false, position = 13)
	private IfsCsvMaster ifsCsvMaster;

	/**
	 * 商品担当者マスタ
	 */
	@OneToMany(mappedBy = "productMaster")
	@ApiModelProperty(value = "商品担当者マスタ", required = false, position = 14)
	private List<ProductPicMaster> productPicMasterList;

	/**
	 * ファイル連携先
	 */
	@OneToMany(mappedBy = "productMaster")
	@ApiModelProperty(value = "ファイル連携先", required = false, position = 15)
	private List<AttachedFileLinkage> attachedFileLinkageList;

	/**
	 * ベンダー商品マスタ
	 */
	@OneToOne(mappedBy = "productMaster")
	@ApiModelProperty(value = "ベンダー商品マスタ", required = false, position = 16)
	private VendorProductMaster vendorProductMaster;

	/**
	 * 商品拡張項目マスタ
	 */
	@OneToMany(mappedBy = "productMaster")
	@ApiModelProperty(value = "商品拡張項目マスタ", required = false, position = 17)
	private List<ProductExtendsParameterMaster> productExtendsParameterMasterList;

	/**
	 * 固有項目区分
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "固有項目区分", required = false, position = 18, allowableValues = "range[0,255]")
	private String extendsParameterDiv;

	/**
	 * 決裁者フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "決裁者フラグ", required = false, position = 19, allowableValues = "range[0,9]")
	private Integer approverFlg;

	/**
	 * 統合契約用種類区分
	 */
	@ApiModelProperty(value = "統合契約用種類区分", required = false, position = 20, allowableValues = "range[0,255]")
	private String integrationContractClassDiv;

	/**
	 * 違約金有無フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "違約金有無フラグ", required = false, position = 21, allowableValues = "range[0,9]")
	private Integer penaltyFlg;

	/**
	 * 最低契約月数
	 */
	@Max(999)
	@Min(0)
	@ApiModelProperty(value = "最低契約月数", required = false, position = 22, allowableValues = "range[0,999]")
	private Integer minContractMonths;

	/**
	 * 最長契約月数
	 */
	@Max(99999)
	@Min(0)
	@ApiModelProperty(value = "最長契約月数", required = false, position = 23, allowableValues = "range[0,99999]")
	private Integer maxContractMonths;

	/**
	 * 仕入先No
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "仕入先No", required = false, position = 24, allowableValues = "range[0,255]")
	private String siiresakiNo;

	/**
	 * ARCS契約種類区分
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "ARCS契約種類区分", required = false, position = 25, allowableValues = "range[0,255]")
	private String arcsContractClassDiv;

	/**
	 * 訪販代売禁止フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "訪販代売禁止フラグ", required = false, position = 26, allowableValues = "range[0,9]")
	private Integer substituteSalesProhibitedFlg;

	/**
	 * 契約変更時商流変更可能フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "契約変更時商流変更可能フラグ", required = false, position = 23, allowableValues = "range[0,9]")
	private Integer commercialFlowDivPlanChangeableFlg;

	/**
	 * ヤマト便有無フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "ヤマト便有無フラグ", required = false, position = 27, allowableValues = "range[0,9]")
	private Integer yamatoFlg;

	/**
	 * シリアル連絡URL
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "シリアル連絡URL", required = false, position = 28, allowableValues = "range[0,255]")
	private String serialContactUrl;

	/**
	 * シリアル連絡区分
	 */
	@ApiModelProperty(value = "シリアル連絡区分", required = false, position = 29, allowableValues = "新規のみ(\"1\"), 新規と契約変更(\"2\")", example = "1")
	private SerialContactDiv serialContactDiv;

	/**
	 * 正式商品名
	 */
	@Column(nullable = false)
	@ApiModelProperty(value = "正式商品名", required = true, position = 30, allowableValues = "range[0,255]")
	private String officialProductName;

	/**
	 * サービスコード
	 */
	@ApiModelProperty(value = "サービスコード", required = false, position = 31, allowableValues = "range[0,255]")
	private String serviceCode;

	/**
	 * RtoR設定可能区分
	 */
	@ApiModelProperty(value = "RtoR設定可能区分", required = false, position = 32, allowableValues = "引継ぎ先と引継ぎ元(\"1\"), 引継ぎ元のみ(\"2\")")
	private RtorSettingPossibleDiv rtorSettingPossibleDiv;
}
