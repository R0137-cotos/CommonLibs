package jp.co.ricoh.cotos.commonlib.entity.externallinkage;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * IIJ機器情報を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "license_info")
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
	@ApiModelProperty(value = "IIJ機器情報ID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * シリアル番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "シリアル番号", required = false, position = 2, allowableValues = "range[0,255]")
	private String serialNumber;

	/**
	 * ディストリビューションID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "ディストリビューションID", required = false, position = 3, allowableValues = "range[0,255]")
	private String distributionId;

	/**
	 * お問い合わせ番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "お問い合わせ番号", required = false, position = 4, allowableValues = "range[0,255]")
	private String contactNo;

	/**
	 * RJ管理番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "RJ管理番号", required = false, position = 5, allowableValues = "range[0,255]")
	private String rjManageNumber;

	/**
	 * 契約番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "契約番号", required = false, position = 6, allowableValues = "range[0,255]")
	private String contractNumber;

	/**
	 * 契約番号枝番
	 */
	@Column(nullable = false)
	@Max(99)
	@Min(0)
	@ApiModelProperty(value = "契約番号枝番", required = true, position = 7, allowableValues = "range[0,99]")
	private int contractBranchNumber;

	/**
	 * 契約ID
	 */
	@Column(nullable = false)
	@Min(0)
	@ApiModelProperty(value = "契約ID", required = true, position = 8, allowableValues = "range[0,9223372036854775807]")
	private long contractId;

	/**
	 * 設定状態
	 */
	@ApiModelProperty(value = "設定状態", required = false, position = 9, allowableValues = "未設定(\"0\"), 設定済(\"1\"), 解除済(\"2\")")
	private SettingStatus settingStatus;

}
