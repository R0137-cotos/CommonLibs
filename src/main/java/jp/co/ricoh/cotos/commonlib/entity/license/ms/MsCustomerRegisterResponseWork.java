package jp.co.ricoh.cotos.commonlib.entity.license.ms;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "MS_顧客登録レスポンスWORK", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private long id;

	/**
	 * 処理状態
	 */
	@Schema(description = "処理状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未反映(\"0\"), 反映済(\"1\"), 反映不要(\"2\")")
	private MsResponseMappedStatus processStatus;

	/**
	 * 希望ドメインプレフィックス
	 */
	@Size(max = 255)
	@Schema(description = "希望ドメインプレフィックス", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String domainPrefix;

	/**
	 * 顧客会社名
	 */
	@Size(max = 255)
	@Schema(description = "顧客会社名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String companyName;

	/**
	 * 顧客会社　住所
	 */
	@Size(max = 255)
	@Schema(description = "顧客会社　住所", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String addressLine1;

	/**
	 * 顧客会社　住所2
	 */
	@Size(max = 255)
	@Schema(description = "顧客会社　住所2", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String addressLine2;

	/**
	 * 顧客会社　市区町村
	 */
	@Size(max = 255)
	@Schema(description = "顧客会社　市区町村", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String city;

	/**
	 * 顧客会社　県
	 */
	@Size(max = 255)
	@Schema(description = "顧客会社　県", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String region;

	/**
	 * 顧客会社郵便番号
	 */
	@Size(max = 255)
	@Schema(description = "顧客会社郵便番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String postalCode;

	/**
	 * 顧客担当Email
	 */
	@Size(max = 255)
	@Schema(description = "顧客担当Email", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String email;

	/**
	 * 顧客担当Tel
	 */
	@Size(max = 255)
	@Schema(description = "顧客担当Tel", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String tel;

	/**
	 * 顧客担当名前
	 */
	@Size(max = 255)
	@Schema(description = "顧客担当名前", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String firstName;

	/**
	 * 顧客担当苗字
	 */
	@Size(max = 255)
	@Schema(description = "顧客担当苗字", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String lastName;

	/**
	 * 契約ID
	 */
	@Column(nullable = false)
	@Schema(description = "契約ID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private Long contractId;

	/**
	 * 顧客テナントID
	 */
	@Size(max = 255)
	@Schema(description = "顧客テナントID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String customerId;
	
	/**
	 * 管理者ユーザーID
	 */
	@Size(max = 255)
	@Schema(description = "管理者ユーザーID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String adminUserId;
	
	/**
	 * 管理者ユーザーパスワード
	 */
	@Size(max = 255)
	@Schema(description = "管理者ユーザーパスワード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String adminUserPassword;
	
	/**
	 * 確定サブドメイン
	 */
	@Size(max = 255)
	@Schema(description = "確定サブドメイン", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String commitSubDomain;
}
