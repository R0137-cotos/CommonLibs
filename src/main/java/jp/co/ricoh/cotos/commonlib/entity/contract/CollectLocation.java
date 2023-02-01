package jp.co.ricoh.cotos.commonlib.entity.contract;

import java.util.Arrays;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 回収先を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "collect_location")
public class CollectLocation extends EntityBase {

	@Description(value = "連携状態")
	public enum LinkageState {

		未連携("0"), 連携済("1"), 対象外("2");

		private final String text;

		private LinkageState(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static LinkageState fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * 回収先ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "collect_location_seq")
	@SequenceGenerator(name = "collect_location_seq", sequenceName = "collect_location_seq", allocationSize = 1)
	@ApiModelProperty(value = "回収先ID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 希望回収日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "希望回収日", required = false, position = 2)
	private Date collectPreferredDate;

	/**
	 * 担当者氏名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "担当者氏名", required = false, position = 3, allowableValues = "range[0,255]")
	private String picName;

	/**
	 * 郵便番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "郵便番号", required = false, position = 4, allowableValues = "range[0,255]")
	private String postNumber;

	/**
	 * 住所
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "住所", required = false, position = 5, allowableValues = "range[0,255]")
	private String address;

	/**
	 * 企業名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "企業名", required = false, position = 6, allowableValues = "range[0,255]")
	private String companyName;

	/**
	 * 事業所名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "事業所名", required = false, position = 7, allowableValues = "range[0,255]")
	private String officeName;

	/**
	 * 担当者部署
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "担当者部署", required = false, position = 8, allowableValues = "range[0,255]")
	private String picDeptName;

	/**
	 * 電話番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "電話番号", required = false, position = 9, allowableValues = "range[0,255]")
	private String phoneNumber;

	/**
	 * 回収不要フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "回収不要フラグ", required = false, position = 10, allowableValues = "range[0,9]")
	private Integer collectUnnecessaryFlg;

	/**
	 * アップグレードRJ管理番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "アップグレードRJ管理番号", required = false, position = 11, allowableValues = "range[0,255]")
	private String upgradeRjManageNumber;

	/**
	 * 連携状態
	 */
	@ApiModelProperty(value = "連携状態", required = false, position = 12, allowableValues = "未連携(\"0\"), 連携済(\"1\"), 対象外(\"2\")")
	private LinkageState linkageState;

	/**
	 * 契約
	 */
	@OneToOne(optional = false)
	@JsonIgnore
	@JoinColumn(name = "contract_id", referencedColumnName = "id")
	@ApiModelProperty(value = "契約", required = true, position = 13)
	private Contract contract;

}
