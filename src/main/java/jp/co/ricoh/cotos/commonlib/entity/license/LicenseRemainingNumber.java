package jp.co.ricoh.cotos.commonlib.entity.license;

import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ライセンス残数を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "license_remaining_number")
public class LicenseRemainingNumber extends EntityBase {

	@Description(value = "割当区分")
	public enum AllocationDiv {

		未("0"), 済("1"), 破棄("2");

		private final String text;

		private AllocationDiv(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static AllocationDiv fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * ライセンス残数ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "license_remaining_number_seq")
	@SequenceGenerator(name = "license_remaining_number_seq", sequenceName = "license_remaining_number_seq", allocationSize = 1)
	@Schema(description = "ライセンス残数ID(作成時不要)", allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * ライセンス区分マスタID
	 */
	@Column(nullable = false)
	@Min(0)
	@Schema(description = "ライセンス区分マスタID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]", readOnly = false)
	private long licenseDivMasterId;

	/**
	 * ライセンスキー
	 */
	@NotNull
	@Column(nullable = false)
	@Size(max = 255)
	@Schema(description = "ライセンスキー", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]", readOnly = false)
	private String licenseKey;

	/**
	 * ライセンス情報
	 */
	@OneToOne(optional = true)
	@JoinColumn(name = "license_info_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "ライセンス情報", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private LicenseInfo licenseInfo;

	/**
	 * 恒久契約識別番号
	 */
	@Size(max = 255)
	@Schema(description = "恒久契約識別番号", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]", readOnly = false)
	private String immutableContIdentNumber;

	/**
	 * RJ管理番号
	 */
	@Size(max = 255)
	@Schema(description = "RJ管理番号", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]", readOnly = false)
	private String rjManageNumber;

	/**
	 * 割当区分
	 */
	@Schema(description = "割当区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未(\"0\"), 済(\"1\"), 破棄(\"3\")")
	private AllocationDiv allocationDiv;

	/**
	 * 拡張項目
	 */
	@Schema(description = "拡張項目", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Lob
	private String extendsParameter;

}
