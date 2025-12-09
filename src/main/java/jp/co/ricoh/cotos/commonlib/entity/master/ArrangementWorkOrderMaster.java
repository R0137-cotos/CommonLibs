package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
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
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumType.ContractTypeDetails;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.ContractType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 手配業務完了順マスタを表すEntity
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "arrangement_work_order_master")
public class ArrangementWorkOrderMaster extends EntityBaseMaster {

	@Description(value = "チェックタイミング区分")
	public enum CheckTimingType {

		業務受付時("0"), 業務完了時("1");

		private final String text;

		private CheckTimingType(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static CheckTimingType fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * 手配業務完了順マスタID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "arrangement_wk_mt_order_seq")
	@SequenceGenerator(name = "arrangement_wk_mt_order_seq", sequenceName = "arrangement_wk_mt_order_seq", allocationSize = 1)
	@Schema(description = "手配業務完了順マスタID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 商品マスタ
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "product_master_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "商品マスタ", requiredMode = Schema.RequiredMode.REQUIRED)
	private ProductMaster productMaster;

	/**
	 * 契約種別
	 */
	@Column(nullable = false)
	@NotNull
	@Schema(description = "契約種別", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "新規(\"1\"), 契約変更(\"2\"), 情報変更(\"3\"), 契約更新(\"4\")")
	private ContractType contractType;

	/**
	 * 解約フラグ
	 */
	@Column(nullable = false)
	@Max(9)
	@Min(0)
	@Schema(description = "解約フラグ", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9]")
	private int disengagementFlg;

	/**
	 * 手配業務タイプマスタ
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "arrangement_wk_type_master_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "手配業務タイプマスタ", requiredMode = Schema.RequiredMode.REQUIRED)
	private ArrangementWorkTypeMaster arrangementWorkTypeMaster;

	/**
	 * 先実行手配業務タイプマスタID配列
	 */
	@Column(nullable = true)
	@Schema(description = "先実行手配業務タイプマスタID配列", requiredMode = Schema.RequiredMode.REQUIRED)
	@Lob
	private String befArrangementWkTypeArray;

	/**
	 * チェックタイミング区分
	 */
	@Column(nullable = false)
	@Schema(description = "チェックタイミング区分", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "業務受付時(\"0\"), 業務完了時(\"1\")")
	private CheckTimingType checkTimingType;

	/**
	 * 契約種別
	 */
	@Column(nullable = false)
	@Schema(description = "契約種別種別", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "新規(\"1\"), 契約更新(\"2\"), 再契約(\"3\"), アップグレード(\"4\"), メニュー変更(\"5\"), 基本プラン減数(\"6\"), プラン_オプション_減数_削除(\"7\"), オプション_追加_増数(\"8\"), オプション_追加_減数(\"9\"), 自動更新(\"10\"), 支払周期変更(\"26\")")
	private ContractTypeDetails contractTypeDetail;
}
