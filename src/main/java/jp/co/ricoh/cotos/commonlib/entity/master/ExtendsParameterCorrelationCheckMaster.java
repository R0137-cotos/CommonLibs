package jp.co.ricoh.cotos.commonlib.entity.master;

import java.io.Serializable;
import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 拡張項目相関チェックマスタ
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "extends_parameter_correlation_check_master")
public class ExtendsParameterCorrelationCheckMaster extends EntityBaseMaster {

	@Description(value = "システムドメイン")
	public enum Domain {
		estimation("1"), contract("2");

		private final String text;

		private Domain(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static Domain fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Embeddable
	@Data
	public static class Id implements Serializable {

		/**
		 * シリアルバージョンID
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * 商品マスタID
		 */
		@Column(name = "product_master_id", nullable = false)
		@Schema(description = "商品マスタID", requiredMode = Schema.RequiredMode.REQUIRED)
		private long productMasterId;

		/**
		 * ドメイン
		 */
		@Column(name = "domain", nullable = false)
		@Schema(description = "システムドメイン", requiredMode = Schema.RequiredMode.REQUIRED)
		private Domain domain;
	}

	@EmbeddedId
	private Id id;

	@ManyToOne
	@JoinColumn(name = "product_master_id", referencedColumnName = "id")
	@MapsId("productMasterId")
	@JsonIgnore
	private ProductMaster productMaster;

	/**
	 * 相関チェック条件式
	 */
	@Column(nullable = false)
	@Schema(description = "相関チェック条件式", requiredMode = Schema.RequiredMode.REQUIRED)
	@Lob
	private String correlationCheckConditionFormula;
}
