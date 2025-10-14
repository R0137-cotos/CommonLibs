package jp.co.ricoh.cotos.commonlib.entity.master;

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

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import jp.co.ricoh.cotos.commonlib.entity.master.ProductMaster.SerialContactDiv;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 商品マスタDto
 * 紐づく品種マスタリストをDTOとし、品種マスタの子エンティティは持たない
 */
@Entity
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@Table(name = "product_master")
public class ProductMasterDto extends EntityBaseMaster {

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
	@OneToMany(mappedBy = "productMasterDto")
	@ApiModelProperty(value = "品種マスタ", required = false, position = 5)
	private List<ItemMasterDto> itemMasterDtoList;

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
	@ApiModelProperty(value = "最低契約月数", required = false, position = 21, allowableValues = "range[0,999]")
	private Integer minContractMonths;

	/**
	 * 契約変更時商流変更可能フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "契約変更時商流変更可能フラグ", required = false, position = 22, allowableValues = "range[0,9]")
	private Integer commercialFlowDivPlanChangeableFlg;

	/**
	 * シリアル連絡URL
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "シリアル連絡URL", required = false, position = 22, allowableValues = "range[0,255]")
	private String serialContactUrl;

	/**
	 * シリアル連絡区分
	 */
	@ApiModelProperty(value = "シリアル連絡区分", required = false, position = 23, allowableValues = "新規のみ(\"1\"), 新規と契約変更(\"2\")", example = "1")
	private SerialContactDiv serialContactDiv;

	/**
	 * 仕入先No
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "仕入先No", required = false, position = 24, allowableValues = "range[0,255]")
	private String siiresakiNo;

	/**
	 * 情報変更禁止開始日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "情報変更禁止開始日", required = false, position = 25)
	private Date infoChangeProhibitedPeriodFrom;

	/**
	 * 情報変更禁止終了日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "情報変更禁止終了日", required = false, position = 26)
	private Date infoChangeProhibitedPeriodTo;

	/**
	 * 契約変更禁止開始日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "契約変更禁止開始日", required = false, position = 27)
	private Date contractChangeProhibitedPeriodFrom;

	/**
	 * 契約変更禁止終了日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "契約変更禁止終了日", required = false, position = 28)
	private Date contractChangeProhibitedPeriodTo;

	/**
	 * サブ商品種類区分
	 */
	@ApiModelProperty(value = "サブ商品種類区分", required = false, position = 29, allowableValues = "range[0,255]")
	private String subProductClassDiv;

	/**
	 * 品種明細情報取込フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "品種明細情報取込フラグ", required = false, position = 30, allowableValues = "range[0,9]")
	private Integer itemDetailInfoImportFlg;

	/**
	 * 取込ファイルバージョン
	 */
	@ApiModelProperty(value = "取込ファイルバージョン", required = false, position = 31, allowableValues = "range[0,9999999999999999999]")
	private Long importFileVersion;

	/**
	 * 取込ファイル格納URL
	 */
	@Size(max = 1000)
	@ApiModelProperty(value = "取込ファイル格納URL", required = false, position = 32, allowableValues = "range[0,1000]")
	private String importFileUrl;
}
