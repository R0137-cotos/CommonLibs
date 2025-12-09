package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.TargetContractType;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.LifecycleStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 契約チェックリスト構成マスタを表すEntity
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "contract_checklist_comp_master")
public class ContractChecklistCompMaster extends EntityBaseMaster {

	@Description(value = "対象ライフサイクル状態")
	public enum TargetLifecycleStatus {

		作成中("1"), キャンセル手続き中("2"), 解約手続き中("3");

		private final String text;

		private TargetLifecycleStatus(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static TargetLifecycleStatus fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}

		public static TargetLifecycleStatus fromLifeCycleStatus(LifecycleStatus lifeCycleStatus) {

			// TargetLifecycleStatus と LifecycleStatus 間で区分値構造が異なることによる変換処理
			switch (lifeCycleStatus) {
			case 作成中:
				return TargetLifecycleStatus.作成中;
			case キャンセル手続き中:
				return TargetLifecycleStatus.キャンセル手続き中;
			case 解約手続き中:
				return TargetLifecycleStatus.解約手続き中;
			default:
				throw new IllegalArgumentException(String.valueOf(lifeCycleStatus.toString()));
			}
		};
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contract_checklist_comp_master_seq")
	@SequenceGenerator(name = "contract_checklist_comp_master_seq", sequenceName = "contract_checklist_comp_master_seq", allocationSize = 1)
	@Schema(description = "契約チェックリスト構成マスタID", required = true, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 商品
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "product_master_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "商品マスタ", required = true)
	private ProductMaster productMaster;

	/**
	 * 対象契約種別
	 */
	@Column(nullable = false)
	@Schema(description = "対象契約種別<br /> "//
			+ "共通/新規/契約変更/情報変更/契約更新", required = true, allowableValues = "共通(\"1\"), 新規(\"2\"), 契約変更(\"3\"), 情報変更(\"4\"), 契約更新(\"5\")", example = "1")
	private TargetContractType targetContractType;

	/**
	 * 対象ライフサイクル状態
	 */
	@Column(nullable = false)
	@Schema(description = "対象ライフサイクル状態<br /> "//
			+ "作成中/キャンセル手続き中/解約手続き中", required = true, allowableValues = "作成中(\"1\"), キャンセル手続き中(\"2\"), 解約手続き中(\"3\")", example = "1")
	private TargetLifecycleStatus targetLifecycleStatus;

	/**
	 * 汎用チェック事項マスタ
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "gp_check_matter_master_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "汎用チェック事項マスタ", required = true)
	private GpCheckMatterMaster gpCheckMatterMaster;

	/**
	 * 表示順
	 */
	@Column(nullable = false)
	@Schema(description = "表示順", required = true, allowableValues = "range[0,999]")
	private int displayOrder;

	/**
	 * チェック必須フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "チェック必須フラグ", required = false, allowableValues = "range[0,9]")
	private Integer checkRequiredFlg;

}
