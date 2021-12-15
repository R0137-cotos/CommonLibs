package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 通知メール変換値マスタを表すEntity
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "mail_convert_value_master")
public class MailConvertValueMaster extends EntityBaseMaster {

	public enum SubjectBodyType {

		件名("0"), 本文("1"), 本文_リスト("2");

		private final String text;

		private SubjectBodyType(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static SubjectBodyType fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}

	}

	public enum ConvertType {

		エンティティ("1"), 独自SQL("2");

		private final String text;

		private ConvertType(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static ConvertType fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}

	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mail_convert_value_master_seq")
	@SequenceGenerator(name = "mail_convert_value_master_seq", sequenceName = "mail_convert_value_master_seq", allocationSize = 1)
	@ApiModelProperty(value = "通知メール変換値マスタID", required = true, position = 1, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 通知メール制御マスタ
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "mail_control_master_id", referencedColumnName = "id")
	@JsonIgnore
	@ApiModelProperty(value = "通知メール制御マスタ", required = true, position = 2)
	private MailControlMaster mailControlMaster;

	/**
	 * 件名/本文区分
	 */
	@ApiModelProperty(value = "件名/本文区分", required = false, allowableValues = "件名(\"0\"), 本文(\"1\"), 本文(\"2\")", example = "1", position = 3)
	private SubjectBodyType subjectBodyType;

	/**
	 * 置換変数番号
	 */
	@Max(99)
	@ApiModelProperty(value = "置換変数番号", required = false, position = 4)
	private Integer replaceVariableNumber;

	/**
	 * 置換値エンティティ名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "置換値エンティティ名", required = false, position = 5, allowableValues = "range[0,255]")
	private String replaceEntityName;

	/**
	 * 置換値フィールド名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "置換値フィールド名", required = false, position = 6, allowableValues = "range[0,255]")
	private String replaceFieldName;

	/**
	 * 拡張項目フラグ
	 */
	@Max(9)
	@ApiModelProperty(value = "拡張項目フラグ", required = false, position = 7, allowableValues = "range[0,9]")
	private Integer extendsParameterFlg;

	/**
	 * 置換値マスタ名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "置換値マスタ名", required = false, position = 8, allowableValues = "range[0,255]")
	private String replaceMasterName;

	/**
	 * 置換値マスタ検索エンティティ名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "置換値マスタ検索エンティティ名", required = false, position = 9, allowableValues = "range[0,255]")
	private String replaceMasterSearchEntityName;

	/**
	 * 置換値マスタ検索フィールド名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "置換値マスタ検索フィールド名", required = false, position = 10, allowableValues = "range[0,255]")
	private String replaceMasterSearchFieldName;

	/**
	 * 置換値区分
	 */
	@ApiModelProperty(value = "置換値区分", required = false, allowableValues = "エンティティ(\"1\"), 独自SQL(\"2\")", example = "1", position = 11)
	private ConvertType convertType;

	/**
	 * SQL区分
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "SQL区分", required = false, position = 12, allowableValues = "range[0,255]")
	private String sqlType;

}
