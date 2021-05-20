package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(value = "手配業務完了順マスタID", required = true, position = 1, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 商品マスタ
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "product_master_id", referencedColumnName = "id")
	@JsonIgnore
	@ApiModelProperty(value = "商品マスタ", required = true, position = 2)
	private ProductMaster productMaster;

	/**
	 * 契約種別
	 */
	@Column(nullable = false)
	@NotNull
	@ApiModelProperty(value = "契約種別", required = true, allowableValues = "新規(\"1\"), 契約変更(\"2\"), 情報変更(\"3\"), 契約更新(\"4\")", position = 3)
	private ContractType contractType;

	/**
	 * 解約フラグ
	 */
	@Column(nullable = false)
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "解約フラグ", required = true, position = 4, allowableValues = "range[0,9]")
	private int disengagementFlg;

	/**
	 * 手配業務タイプマスタ
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "arrangement_wk_type_master_id", referencedColumnName = "id")
	@JsonIgnore
	@ApiModelProperty(value = "手配業務タイプマスタ", required = true, position = 5)
	private ArrangementWorkTypeMaster arrangementWorkTypeMaster;

	/**
	 * 先実行手配業務タイプマスタID配列
	 */
	@Column(nullable = true)
	@ApiModelProperty(value = "先実行手配業務タイプマスタID配列", required = true, position = 6)
	@Lob
	private String befArrangementWkTypeArray;

	/**
	 * チェックタイミング区分
	 */
	@Column(nullable = false)
	@ApiModelProperty(value = "チェックタイミング区分", required = true, allowableValues = "業務受付時(\"0\"), 業務完了時(\"1\")", position = 7)
	private CheckTimingType checkTimingType;

	/**
	 * 契約種別
	 */
	@Column(nullable = false)
	@ApiModelProperty(value = "契約種別種別", required = false, allowableValues = "新規(\"1\"), 契約更新(\"2\"), 再契約(\"3\"), アップグレード(\"4\"), メニュー変更(\"5\"), 基本プラン減数(\"6\"), プラン_オプション_減数_削除(\"7\"), オプション_追加_増数(\"8\"), オプション_追加_減数(\"9\"), 自動更新(\"10\")", position = 8)
	private ContractTypeDetails contractTypeDetail;
}
