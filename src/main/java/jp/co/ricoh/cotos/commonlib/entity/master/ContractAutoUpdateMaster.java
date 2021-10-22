package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(value = "契約自動更新マスタID", required = true, position = 1)
	private long id;

	/**
	 * 品種マスタID
	 */
	@Column(nullable = false)
	@ApiModelProperty(value = "品種マスタID", required = true, position = 2)
	private long itemMasterId;

	/**
	 * 契約更新方式区分
	 */
	@Column(nullable = false)
	@ApiModelProperty(value = "契約更新方式区分", required = true, position = 3, allowableValues = "自動更新(\"1\"), 手動更新(\"2\")", example = "1")
	private ContractUpdateType contractUpdateType;

	/**
	 * 手配情報作成区分
	 */
	@Column(nullable = false)
	@ApiModelProperty(value = "手配情報作成区分", required = true, position = 4, allowableValues = "作成しない(\"0\"), 作成する(\"1\")", example = "1")
	private ArrangementCreateType arrangementCreateType;

	/**
	 * サービス開始日更新区分
	 */
	@Size(max = 255)
	@Column(nullable = false)
	@ApiModelProperty(value = "サービス開始日更新区分", required = true, position = 5, allowableValues = "range[0,255]")
	private String serviceTermStartType;

	/**
	 * サービス終了日更新区分
	 */
	@Size(max = 255)
	@Column(nullable = false)
	@ApiModelProperty(value = "サービス終了日更新区分", required = true, position = 6, allowableValues = "range[0,255]")
	private String serviceTermEndType;

	/**
	 * 商品更新区分
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "商品更新区分", required = false, position = 7, allowableValues = "range[0,255]")
	private String productUpdateType;

	/**
	 * 期間売対象外区分
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "期間売対象外区分", required = false, position = 8, allowableValues = "range[0,255]")
	private String periodSellingNoTargetType;

}
