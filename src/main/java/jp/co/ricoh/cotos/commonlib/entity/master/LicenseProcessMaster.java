package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.Arrays;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ライセンス工程マスタを表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "license_process_master")
public class LicenseProcessMaster extends EntityBase {

	@Description(value = "操作区分")
	public enum OperationDiv {

		受付("1"), ボタン("2"), CSV出力("3"), CSV取込("4"), 自動("5");

		private final String text;

		private OperationDiv(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static OperationDiv fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * ライセンス工程マスタID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "license_process_master_seq")
	@SequenceGenerator(name = "license_process_master_seq", sequenceName = "license_process_master_seq", allocationSize = 1)
	@Schema(description = "ライセンス工程マスタID(作成時不要)", required = true, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * ライセンス区分マスタID
	 */
	@Column(nullable = false)
	@Min(0)
	@Schema(description = "ライセンス区分マスタID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private long licenseDivMasterId;

	/**
	 * 工程名称
	 */
	@Size(max = 255)
	@Schema(description = "工程名称", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String processName;

	/**
	 * 工程完了名称
	 */
	@Size(max = 255)
	@Schema(description = "工程完了名称", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String completeProcessName;

	/**
	 * 操作区分
	 */
	@NotNull
	@Size(max = 255)
	@Column(nullable = false)
	@Schema(description = "操作区分", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "受付(\"1\"), ボタン(\"2\"), CSV出力(\"3\"), CSV取込(\"4\"), 自動(\"5\")")
	private OperationDiv operationDiv;

	/**
	 * 優先順位
	 */
	@Min(0)
	@Max(999)
	@Column(nullable = false)
	@Schema(description = "優先順位", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,999]")
	private int priorityOrder;

	/**
	 * ライセンス工程種類区分
	 */
	@Size(max = 255)
	@Schema(description = "ライセンス工程種類区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String licenseProcessClassDiv;

	/**
	 * ライセンス工程パターンマスタリスト
	 */
	@OneToMany(mappedBy = "licenseProcessMaster")
	@Schema(description = "ライセンス工程パターンマスタ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<LicenseProcessPatternMaster> licenseProcessPatternMasterList;

}
