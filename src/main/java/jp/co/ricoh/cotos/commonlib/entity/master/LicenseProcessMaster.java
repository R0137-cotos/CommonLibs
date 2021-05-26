package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(value = "ライセンス工程マスタID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * ライセンス区分マスタID
	 */
	@Column(nullable = false)
	@Min(0)
	@ApiModelProperty(value = "ライセンス区分マスタID", required = true, position = 2, allowableValues = "range[0,9223372036854775807]")
	private long licenseDivMasterId;

	/**
	 * 工程名称
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "工程名称", required = false, position = 3, allowableValues = "range[0,255]")
	private String processName;

	/**
	 * 工程完了名称
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "工程完了名称", required = false, position = 4, allowableValues = "range[0,255]")
	private String completeProcessName;

	/**
	 * 操作区分
	 */
	@NotNull
	@Size(max = 255)
	@Column(nullable = false)
	@ApiModelProperty(value = "操作区分", required = true, allowableValues = "受付(\"1\"), ボタン(\"2\"), CSV出力(\"3\"), CSV取込(\"4\"), 自動(\"5\")", position = 5)
	private OperationDiv operationDiv;

	/**
	 * 優先順位
	 */
	@Min(0)
	@Max(999)
	@Column(nullable = false)
	@ApiModelProperty(value = "優先順位", required = true, position = 6, allowableValues = "range[0,999]")
	private int priorityOrder;

	/**
	 * ライセンス工程種類区分
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "ライセンス工程種類区分", required = false, position = 7, allowableValues = "range[0,255]")
	private String licenseProcessClassDiv;

	/**
	 * ライセンス工程パターンマスタリスト
	 */
	@OneToMany(mappedBy = "licenseProcessMaster")
	@ApiModelProperty(value = "ライセンス工程パターンマスタ", required = false, position = 8)
	private List<LicenseProcessPatternMaster> licenseProcessPatternMasterList;

}
