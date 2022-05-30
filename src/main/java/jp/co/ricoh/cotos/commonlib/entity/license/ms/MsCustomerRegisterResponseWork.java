package jp.co.ricoh.cotos.commonlib.entity.license.ms;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * MS_顧客登録レスポンスWORK
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "ms_customer_register_response_work")
public class MsCustomerRegisterResponseWork extends AbstractMsResponseWork {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ms_customer_register_response_work_seq")
	@SequenceGenerator(name = "ms_customer_register_response_work_seq", sequenceName = "ms_customer_register_response_work_seq", allocationSize = 1)
	@ApiModelProperty(value = "MS_顧客登録レスポンスWORK", required = true, position = 1, allowableValues = "range[0,9223372036854775807]")
	private long id;

	/**
	 * 処理状態
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "処理状態", required = false, position = 2, allowableValues = "range[0,255]")
	private MsResponseMappedStatus processStatus;

	/**
	 * 希望ドメインプレフィックス
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "希望ドメインプレフィックス", required = false, position = 3, allowableValues = "range[0,255]")
	private String domainPrefix;

	/**
	 * 顧客会社名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "顧客会社名", required = false, position = 4, allowableValues = "range[0,255]")
	private String companyName;

	/**
	 * 顧客会社　住所
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "顧客会社　住所", required = false, position = 5, allowableValues = "range[0,255]")
	private String addressLine1;

	/**
	 * 顧客会社　住所2
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "顧客会社　住所2", required = false, position = 6, allowableValues = "range[0,255]")
	private String addressLine2;

	/**
	 * 顧客会社　市区町村
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "顧客会社　市区町村", required = false, position = 7, allowableValues = "range[0,255]")
	private String city;

	/**
	 * 顧客会社　県
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "顧客会社　県", required = false, position = 8, allowableValues = "range[0,255]")
	private String region;

	/**
	 * 顧客会社郵便番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "顧客会社郵便番号", required = false, position = 9, allowableValues = "range[0,255]")
	private String postalCode;

	/**
	 * 顧客担当Email
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "顧客担当Email", required = false, position = 10, allowableValues = "range[0,255]")
	private String email;

	/**
	 * 顧客担当Tel
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "顧客担当Tel", required = false, position = 11, allowableValues = "range[0,255]")
	private String tel;

	/**
	 * 顧客担当名前
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "顧客担当名前", required = false, position = 12, allowableValues = "range[0,255]")
	private String firstName;

	/**
	 * 顧客担当苗字
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "顧客担当苗字", required = false, position = 13, allowableValues = "range[0,255]")
	private String lastName;

	/**
	 * 契約ID
	 */
	@Column(nullable = false)
	@ApiModelProperty(value = "契約ID", required = true, position = 14, allowableValues = "range[0,9223372036854775807]")
	private Long contractId;

}
