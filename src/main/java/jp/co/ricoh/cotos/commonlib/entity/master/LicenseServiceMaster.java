package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.Arrays;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ライセンスサービスマスタを表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "license_service_master")
public class LicenseServiceMaster extends EntityBase {

	@Description(value = "ライセンス区分")
	public enum LicenseType {

		ベース("1"), アドオン("2");

		private final String text;

		private LicenseType(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static LicenseType fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * ライセンスサービスマスタID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "license_service_master_seq")
	@SequenceGenerator(name = "license_service_master_seq", sequenceName = "license_service_master_seq", allocationSize = 1)
	@Schema(description = "ライセンスサービスマスタID(作成時不要)", allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * ライセンスサービスID
	 */
	@Size(max = 255)
	@Schema(description = "ライセンスサービスID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String licenseServiceId;

	/**
	 * ライセンスサービス名
	 */
	@Size(max = 255)
	@Schema(description = "ライセンスサービス名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String licenseServiceName;

	/**
	 * ライセンスサービス種類区分
	 */
	@Size(max = 255)
	@Schema(description = "ライセンスサービス種類区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String licenseServiceClassDiv;

	/**
	 * ライセンス区分構成マスタ
	 */
	@OneToMany(mappedBy = "licenseServiceMaster")
	@Schema(description = "ライセンス区分構成マスタ", requiredMode = Schema.RequiredMode.REQUIRED)
	private List<LicenseServiceCompMaster> licenseServiceCompMasterList;

	/**
	 * ライセンス区分
	 */
	@Schema(description = "ライセンス区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "ベース(\"1\"), アドオン(\"2\")")
	private LicenseType licenseType;
}
