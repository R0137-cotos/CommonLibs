package jp.co.ricoh.cotos.commonlib.entity.license;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.LicenseStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ライセンスキー情報を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "license_key_info")
public class LicenseKeyInfo extends EntityBase {

	/**
	 * ライセンスキー情報ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "license_key_info_seq")
	@SequenceGenerator(name = "license_key_info_seq", sequenceName = "license_key_info_seq", allocationSize = 1)
	@ApiModelProperty(value = "ライセンスキー情報ID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * ライセンスアカウント
	 */
	@ManyToOne(optional = true)
	@JoinColumn(name = "license_account_id", referencedColumnName = "id")
	@JsonIgnore
	@ApiModelProperty(value = "ライセンスアカウント", required = true, position = 2)
	private LicenseAccount licenseAccount;

	/**
	 * ライセンスサービスID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "ライセンスサービスID", required = true, position = 3, allowableValues = "range[0,255]")
	private String license_service_id;

	/**
	 * ライセンスキー
	 */
	@Column(nullable = false)
	@Size(max = 255)
	@ApiModelProperty(value = "ライセンスキー", required = false, position = 4, allowableValues = "range[0,255]")
	private String license_key;

	/**
	 * ライセンス状態
	 */
	@ApiModelProperty(value = "ライセンス状態", required = false, position = 5, allowableValues = "未確定(\"0\"), 有効(\"1\"), 解約(\"2\")")
	private LicenseStatus license_status;

	/**
	 * ライセンス開始日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "ライセンス開始日", required = false, position = 6)
	private Date license_term_start;

	/**
	 * ライセンス終了日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "ライセンス終了日", required = false, position = 7)
	private Date license_term_end;

	/**
	 * 数量
	 */
	@Max(99999)
	@Min(-99999)
	@ApiModelProperty(value = "数量", required = false, position = 8, allowableValues = "range[-99999,99999]")
	private Integer quantity;

	/**
	 * 拡張項目
	 */
	@ApiModelProperty(value = "拡張項目", required = false, position = 9)
	@Lob
	private String extendsParameter;

}
