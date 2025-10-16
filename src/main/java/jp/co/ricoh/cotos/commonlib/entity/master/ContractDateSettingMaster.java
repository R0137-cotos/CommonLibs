package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.ContractType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 契約日付設定マスタを表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "contract_date_setting_master")
public class ContractDateSettingMaster extends EntityBaseMaster {

	@Description(value = "サービス開始日設定区分")
	public enum ServiceTermStartSettingType {

		システム日付("1"), システム日付の翌月1日("2");

		private final String text;

		private ServiceTermStartSettingType(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static ServiceTermStartSettingType fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "課金開始日設定区分")
	public enum BillingStartDateSettingType {

		システム日付の翌月１日("1"), サービス開始日と同日("2");

		private final String text;

		private BillingStartDateSettingType(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static BillingStartDateSettingType fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "サービス終了日設定区分")
	public enum ServiceTermEndSettingType {

		課金開始日からNヵ月後の日付("1"), 課金開始日からNヵ月後の日付の月末("2");

		private final String text;

		private ServiceTermEndSettingType(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static ServiceTermEndSettingType fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * 契約日付設定マスタ
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contract_date_setting_master_seq")
	@SequenceGenerator(name = "contract_date_setting_master_seq", sequenceName = "contract_date_setting_master_seq", allocationSize = 1)
	@ApiModelProperty(value = "契約日付設定マスタID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 商品マスタID
	 */
	@Min(0)
	@Column(nullable = false)
	@ApiModelProperty(value = "商品マスタID", required = true, position = 2, allowableValues = "range[0,9223372036854775807]")
	private long productMasterId;

	/**
	 * 契約種別
	 */
	@ApiModelProperty(value = "契約種別", required = false, allowableValues = "新規(\"1\"), 契約変更(\"2\"), 情報変更(\"3\"), 契約更新(\"4\")", position = 3)
	private ContractType contractType;

	/**
	 * 手配業務タイプマスタID
	 */
	@Min(0)
	@ApiModelProperty(value = "手配業務タイプマスタID", required = false, position = 4, allowableValues = "range[0,9223372036854775807]")
	private Long arrangementWorkTypeMasterId;

	/**
	 * サービス開始日設定区分
	 */
	@ApiModelProperty(value = "サービス開始日設定区分", required = false, allowableValues = "システム日付(\"1\"), システム日付の翌月1日(\"2\")", example = "1", position = 5)
	private ServiceTermStartSettingType serviceTermStartSettingType;

	/**
	 * 課金開始日設定区分
	 */
	@ApiModelProperty(value = "課金開始日設定区分", required = false, allowableValues = "システム日付の翌月１日(\"1\"), サービス開始日と同日(\"2\")", example = "1", position = 6)
	private BillingStartDateSettingType billingStartDateSettingType;

	/**
	 * サービス終了日設定区分
	 */
	@ApiModelProperty(value = "サービス終了日設定区分", required = false, allowableValues = "課金開始日からNヵ月後の日付(\"1\"), 課金開始日からNヵ月後の日付の月末(\"2\")", example = "1", position = 7)
	private ServiceTermEndSettingType serviceTermEndSettingType;
}