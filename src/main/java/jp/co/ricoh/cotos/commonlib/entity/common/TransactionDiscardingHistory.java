package jp.co.ricoh.cotos.commonlib.entity.common;

import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 案件破棄履歴
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "transaction_discarding_history")
public class TransactionDiscardingHistory extends EntityBase {

	@Description(value = "トランザクション種別")
	public enum TransactionType {

		見積("1"), 契約("2");

		private final String text;

		private TransactionType(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static TransactionType fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_discarding_history_seq")
	@SequenceGenerator(name = "transaction_discarding_history_seq", sequenceName = "transaction_discarding_history_seq", allocationSize = 1)
	@Schema(description = "ID(作成時不要)", required = true, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * トランザクションID
	 */
	@Column(nullable = false)
	@NotNull
	@Schema(description = "契約ID", requiredMode = Schema.RequiredMode.REQUIRED)
	private long transactionId;

	/**
	 * トランザクション種別
	 */
	@Column(nullable = false)
	@NotNull
	@Schema(description = "トランザクション種別", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "見積(\"1\"), 契約(\"2\")")
	private TransactionType transactionType;

	/**
	 * 破棄前最終ライフサイクル状態
	 * NOTE: 設定値は各トランザクションのライフサイクルステータスに従う
	 */
	@Column(nullable = false)
	@NotNull
	@Schema(description = "破棄前最終ライフサイクル状態", requiredMode = Schema.RequiredMode.REQUIRED)
	private String lastLifecycleStatus;

	/**
	 * 破棄前最終ワークフロー状態
	 * NOTE: 設定値は各トランザクションのワークフローステータスに従う
	 */
	@Column(nullable = false)
	@NotNull
	@Schema(description = "破棄前最終ワークフロー状態", requiredMode = Schema.RequiredMode.REQUIRED)
	private String lastWorkflowStatus;
}
