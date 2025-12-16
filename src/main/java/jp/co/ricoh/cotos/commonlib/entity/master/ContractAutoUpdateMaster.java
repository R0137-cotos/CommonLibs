package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 契約自動更新マスタ
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "contract_auto_update_master")
public class ContractAutoUpdateMaster extends EntityBaseMaster {

	/**
	 *
	 * 契約更新方式区分
	 *
	 */
	@Description(value = "契約更新方式区分")
	public enum ContractUpdateType {

		自動更新("1"), 手動更新("2");

		private final String text;

		private ContractUpdateType(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static ContractUpdateType fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 *
	 * 手配情報作成区分
	 *
	 */
	@Description(value = "手配情報作成区分")
	public enum ArrangementCreateType {

		作成しない("0"), 作成する("1");

		private final String text;

		private ArrangementCreateType(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static ArrangementCreateType fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * 契約自動更新マスタID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contract_auto_update_master_seq")
	@SequenceGenerator(name = "contract_auto_update_master_seq", sequenceName = "contract_auto_update_master_seq", allocationSize = 1)
	@Schema(description = "契約自動更新マスタID", requiredMode = Schema.RequiredMode.REQUIRED)
	private long id;

	/**
	 * 品種マスタID
	 */
	@Column(nullable = false)
	@Schema(description = "品種マスタID", requiredMode = Schema.RequiredMode.REQUIRED)
	private long itemMasterId;

	/**
	 * 契約更新方式区分
	 */
	@Column(nullable = false)
	@Schema(description = "契約更新方式区分", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "自動更新(\"1\"), 手動更新(\"2\")", example = "1")
	private ContractUpdateType contractUpdateType;

	/**
	 * 手配情報作成区分
	 */
	@Column(nullable = false)
	@Schema(description = "手配情報作成区分", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "作成しない(\"0\"), 作成する(\"1\")", example = "1")
	private ArrangementCreateType arrangementCreateType;

	/**
	 * サービス開始日更新区分
	 */
	@Size(max = 255)
	@Column(nullable = false)
	@Schema(description = "サービス開始日更新区分", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String serviceTermStartType;

	/**
	 * サービス終了日更新区分
	 */
	@Size(max = 255)
	@Column(nullable = false)
	@Schema(description = "サービス終了日更新区分", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String serviceTermEndType;

	/**
	 * 商品更新区分
	 */
	@Size(max = 255)
	@Schema(description = "商品更新区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String productUpdateType;

	/**
	 * 期間売対象外区分
	 */
	@Size(max = 255)
	@Schema(description = "期間売対象外区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String periodSellingNoTargetType;

}
