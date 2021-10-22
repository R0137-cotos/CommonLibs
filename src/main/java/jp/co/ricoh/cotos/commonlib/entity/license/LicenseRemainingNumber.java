package jp.co.ricoh.cotos.commonlib.entity.license;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(value = "ライセンス残数ID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * ライセンス区分マスタID
	 */
	@Column(nullable = false)
	@Min(0)
	@ApiModelProperty(value = "ライセンス区分マスタID", required = true, position = 2, allowableValues = "range[0,9223372036854775807]", readOnly = false)
	private long licenseDivMasterId;

	/**
	 * ライセンスキー
	 */
	@NotNull
	@Column(nullable = false)
	@Size(max = 255)
	@ApiModelProperty(value = "ライセンスキー", required = true, position = 3, allowableValues = "range[0,255]", readOnly = false)
	private String licenseKey;

	/**
	 * ライセンス情報
	 */
	@OneToOne(optional = true)
	@JoinColumn(name = "license_info_id", referencedColumnName = "id")
	@JsonIgnore
	@ApiModelProperty(value = "ライセンス情報", required = false, position = 4)
	private LicenseInfo licenseInfo;

	/**
	 * 恒久契約識別番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "恒久契約識別番号", required = true, position = 8, allowableValues = "range[0,255]", readOnly = false)
	private String immutableContIdentNumber;

	/**
	 * RJ管理番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "RJ管理番号", required = true, position = 5, allowableValues = "range[0,255]", readOnly = false)
	private String rjManageNumber;

	/**
	 * 割当区分
	 */
	@ApiModelProperty(value = "割当区分", required = false, allowableValues = "未(\"0\"), 済(\"1\"), 破棄(\"3\")", position = 6)
	private AllocationDiv allocationDiv;

	/**
	 * 拡張項目
	 */
	@ApiModelProperty(value = "拡張項目", required = false, position = 7)
	@Lob
	private String extendsParameter;

}
