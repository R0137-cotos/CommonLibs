package jp.co.ricoh.cotos.commonlib.entity.license.ms;

import java.util.Arrays;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * MS_顧客登録リクエストWORK
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "ms_customer_register_request_work")
public class MsCustomerRegisterRequestWork extends AbstractMsRequestWork {

	@Description(value = "顧客連携状態")
	public enum MsCustomerLinkageStatus {
		未処理("0"), 顧客登録済("1"), 処理済("2"), 処理対象外("3"), エラー("4"), 顧客MCA同意依頼済("5"), 顧客MCA同意依頼メール送信エラー("6");

		private final String text;

		private MsCustomerLinkageStatus(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static MsCustomerLinkageStatus fromString(final String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ms_customer_register_request_work_seq")
	@SequenceGenerator(name = "ms_customer_register_request_work_seq", sequenceName = "ms_customer_register_request_work_seq", allocationSize = 1)
	@Schema(description = "MS_顧客登録リクエストWORK", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private long id;

	/**
	 * ライセンス情報ID
	 */
	@Column(nullable = false)
	@Schema(description = "ライセンス情報ID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private Long licenseInfoId;

	/**
	 * 処理状態
	 */
	@Schema(description = "処理状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未処理(\"0\"), 出力済(\"1\"), 連携対象外(\"2\")")
	private MsRequestStatus processStatus;

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
	@Schema(description = "契約ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private Long contractId;

	/**
	 * 顧客連携状態
	 */
	@Schema(description = "顧客連携状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未処理(\"0\"), 顧客登録済(\"1\"), 処理済(\"2\"), 処理対象外(\"3\"), エラー(\"4\")")
	private MsCustomerLinkageStatus customerLinkageStatus;

	/**
	 * メール送信日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "メール送信日", required = false, position = 17)
	private Date sentAt;

}
