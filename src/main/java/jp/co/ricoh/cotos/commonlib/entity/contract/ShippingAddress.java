package jp.co.ricoh.cotos.commonlib.entity.contract;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 配送先を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@EntityListeners(ShippingAddressListener.class)
@Data
@Table(name = "shipping_address")
public class ShippingAddress extends EntityBase {

	/**
	 * 配送先ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shipping_address_seq")
	@SequenceGenerator(name = "shipping_address_seq", sequenceName = "shipping_address_seq", allocationSize = 1)
	@Schema(description = "配送先ID(作成時不要)", required = true, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * MoM社員ID
	 */
	@Column(nullable = false)
	@Size(max = 255)
	@Schema(description = "MoM社員ID<br/>※POST時「RJ社員情報マスタ」存在チェック実施", required = true, allowableValues = "range[0,255]")
	private String momEmployeeId;

	/**
	 * 所属組織MoM組織ID
	 */
	@Size(max = 255)
	@Schema(description = "所属組織MoM組織ID", required = false, allowableValues = "range[0,255]", readOnly = false)
	private String momOrgId;

	/**
	 * 所属組織階層レベル
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "所属組織階層レベル", required = false, allowableValues = "range[0,9]", readOnly = false)
	private Integer orgHierarchyLevel;

	/**
	 * 所属組織名
	 */
	@Size(max = 255)
	@Schema(description = "所属組織名", required = false, allowableValues = "range[0,255]", readOnly = false)
	private String orgName;

	/**
	 * 販売会社名
	 */
	@Size(max = 255)
	@Schema(description = "販売会社名", required = false, allowableValues = "range[0,255]", readOnly = false)
	private String salesCompanyName;

	/**
	 * 会社代表電話番号
	 */
	@Size(max = 1000)
	@Schema(description = "会社代表電話番号", required = false, allowableValues = "range[0,1000]", readOnly = false)
	private String orgPhoneNumber;

	/**
	 * 社員名
	 */
	@Size(max = 255)
	@Schema(description = "社員名", required = false, allowableValues = "range[0,255]", readOnly = false)
	private String employeeName;

	/**
	 * 部署名
	 */
	@Size(max = 255)
	@Schema(description = "部署名", required = false, allowableValues = "range[0,255]", readOnly = false)
	private String salesDepartmentName;

	/**
	 * 郵便番号
	 */
	@Size(max = 255)
	@Schema(description = "郵便番号", required = false, allowableValues = "range[0,255]", readOnly = false)
	private String postNumber;

	/**
	 * 都道府県
	 */
	@Size(max = 255)
	@Schema(description = "都道府県", required = false, allowableValues = "range[0,255]", readOnly = false)
	private String prefectures;

	/**
	 * 市区町村番地
	 */
	@Size(max = 1000)
	@Schema(description = "市区町村番地", required = false, allowableValues = "range[0,1000]", readOnly = false)
	private String cityStreet;

	/**
	 * 電話番号
	 */
	@Size(max = 255)
	@Schema(description = "電話番号", required = false, allowableValues = "range[0,255]", readOnly = false)
	private String phoneNumber;

	/**
	 * ヤマト便お問合せ伝票番号
	 */
	@Size(max = 255)
	@Schema(description = "ヤマト便お問合せ伝票番号", required = false, allowableValues = "range[0,255]", readOnly = false)
	private String yamatoSlipNumber;

	/**
	 * 契約
	 */
	@OneToOne(optional = false)
	@JsonIgnore
	@JoinColumn(name = "contract_id", referencedColumnName = "id")
	@Schema(description = "契約", required = true)
	private Contract contract;

	/**
	 * 建物名
	 */
	@Size(max = 255)
	@Schema(description = "建物名", required = false, allowableValues = "range[0,255]", readOnly = false)
	private String buildingName;

	/**
	 * 企業名
	 */
	@Size(max = 255)
	@Schema(description = "企業名", required = false, allowableValues = "range[0,255]", readOnly = false)
	private String companyName;

}
