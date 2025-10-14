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
import jakarta.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.TargetContractType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商材固有項目マスタを表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "specific_control_master")
public class SpecificControlMaster extends EntityBase {

	@Description(value = "項目値区分")
	public enum ItemValueDiv {

		単項目_文字列("0"), 単項目_数値("1"), 単項目_日時("2"), 単項目以外("9");

		private final String text;

		private ItemValueDiv(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static ItemValueDiv fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * 商材固有項目マスタID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "specific_control_master_seq")
	@SequenceGenerator(name = "specific_control_master_seq", sequenceName = "specific_control_master_seq", allocationSize = 1)
	@ApiModelProperty(value = "商材固有項目マスタID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 商材固有項目名
	 */
	@Column(nullable = false)
	@Size(max = 255)
	@ApiModelProperty(value = "商材固有項目名", required = true, position = 2, allowableValues = "range[0,255]")
	private String specificControlName;

	/**
	 * 商材固有項目区分
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "商材固有項目区分", required = false, position = 3, allowableValues = "range[0,255]")
	private String specificControlDiv;

	/**
	 * 商材固有品種グループマスタID
	 */
	@ManyToOne
	@JoinColumn(name = "specific_item_group_master_id", referencedColumnName = "id")
	@ApiModelProperty(value = "商材固有品種グループマスタID", required = false, position = 4)
	private SpecificItemGroupMaster specificItemGroupMaster;

	/**
	 * 商材固有セクションマスタID
	 */
	@ManyToOne
	@JoinColumn(name = "specific_section_master_id", referencedColumnName = "id")
	@ApiModelProperty(value = "商材固有セクションマスタID", required = false, position = 5)
	private SpecificSectionMaster specificSectionMaster;

	/**
	 * 情報変更不可フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "情報変更不可フラグ", required = false, position = 6, allowableValues = "range[0,9]")
	private Integer infoChangeNotAllowedFlg;

	/**
	 * 対象契約種別
	 */
	@ApiModelProperty(value = "対象契約種別", required = false, position = 7)
	private TargetContractType targetContractType;

	/**
	 * 活性フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "活性フラグ", required = false, position = 8, allowableValues = "range[0,9]")
	private Integer enableFlg;

	/**
	 * 項目値区分
	 */
	@ApiModelProperty(value = "項目値区分", required = false, position = 9)
	private ItemValueDiv itemValueDiv;

	/**
	 * 拡張項目
	 */
	@ApiModelProperty(value = "拡張項目", required = false, position = 10)
	@Lob
	private String extendsParameter;

	/**
	 * 解約用商材固有項目追加フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "解約用商材固有項目追加フラグ", required = false, position = 11, allowableValues = "range[0,9]")
	private Integer specificControlAddFlgCancel;
}
