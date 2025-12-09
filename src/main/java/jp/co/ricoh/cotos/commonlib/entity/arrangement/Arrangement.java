package jp.co.ricoh.cotos.commonlib.entity.arrangement;

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

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.ContractStatusControlType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 契約情報と紐づく手配情報を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true, exclude={"arrangementWorkList"})
@ToString(callSuper = true, exclude = {"arrangementWorkList"})
@Data
@Table(name = "arrangement")
public class Arrangement extends EntityBase {

	@Description(value = "ワークフロー状態(手配)")
	public enum WorkflowStatus {

		手配中("1"), 手配完了("2");

		private final String text;

		private WorkflowStatus(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static WorkflowStatus fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "arrangement_seq")
	@SequenceGenerator(name = "arrangement_seq", sequenceName = "arrangement_seq", allocationSize = 1)
	@Schema(description = "手配ID (作成時不要)", allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 契約ID
	 */
	@Column(nullable = false)
	@Min(0)
	@Schema(description = "契約ID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private long contractId;

	/**
	 * 解約フラグ
	 */
	@Column(nullable = false)
	@Max(9)
	@Min(0)
	@Schema(description = "解約フラグ", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9]")
	private int disengagementFlg;

	/**
	 * ワークフロー状態
	 */
	@Column(nullable = false)
	@NotNull
	@Schema(description = "ワークフロー状態", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "手配中(\"1\"), 手配完了(\"2\")", example = "1")
	private WorkflowStatus workflowStatus;

	/**
	 * 手配業務
	 */
	@OneToMany(mappedBy = "arrangement")
	@Schema(description = "手配業務", requiredMode = Schema.RequiredMode.REQUIRED)
	@JsonIgnore
	private List<ArrangementWork> arrangementWorkList;

	/**
	 * 契約状態遷移制御区分
	 */
	@Schema(description = "契約状態遷移制御区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "手配の作業完了をもって契約状態を進める(\"0\"),手配の作業完了を待たずに契約状態を進める(\"1\")")
	private ContractStatusControlType contractStatusControlType;
}