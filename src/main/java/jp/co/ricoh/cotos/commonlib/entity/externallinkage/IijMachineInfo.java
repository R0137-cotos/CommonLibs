package jp.co.ricoh.cotos.commonlib.entity.externallinkage;

import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * IIJ機器情報を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "iij_machine_info")
public class IijMachineInfo extends EntityBase {

	@Description(value = "設定状態")
	public enum SettingStatus {

		未設定("0"), 設定済("1"), 解除済("2");

		private final String text;

		private SettingStatus(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static SettingStatus fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * IIJ機器情報ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "iij_machine_info_seq")
	@SequenceGenerator(name = "iij_machine_info_seq", sequenceName = "iij_machine_info_seq", allocationSize = 1)
	@Schema(description = "IIJ機器情報ID(作成時不要)", allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * シリアル番号
	 */
	@Size(max = 255)
	@Schema(description = "シリアル番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String serialNumber;

	/**
	 * ディストリビューションID
	 */
	@Size(max = 255)
	@Schema(description = "ディストリビューションID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String distributionId;

	/**
	 * お問い合わせ番号
	 */
	@Size(max = 255)
	@Schema(description = "お問い合わせ番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String contactNo;

	/**
	 * RJ管理番号
	 */
	@Size(max = 255)
	@Schema(description = "RJ管理番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String rjManageNumber;

	/**
	 * 契約番号
	 */
	@Size(max = 255)
	@Schema(description = "契約番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String contractNumber;

	/**
	 * 契約番号枝番
	 */
	@Column(nullable = false)
	@Max(99)
	@Min(0)
	@Schema(description = "契約番号枝番", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,99]")
	private int contractBranchNumber;

	/**
	 * 契約ID
	 */
	@Column(nullable = false)
	@Min(0)
	@Schema(description = "契約ID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private long contractId;

	/**
	 * 設定状態
	 */
	@Schema(description = "設定状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未設定(\"0\"), 設定済(\"1\"), 解除済(\"2\")")
	private SettingStatus settingStatus;

}
