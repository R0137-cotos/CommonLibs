package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.Arrays;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;
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

	@Description(value = "件名/本文区分")
	public enum SubjectVodyType {

		件名("0"), 本文("1"), 本文_リスト("2");

		private final String text;

		private SubjectVodyType(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static SubjectVodyType fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}

	}

	@Description(value = "置換値区分")
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
	@Schema(description = "通知メール変換値マスタID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 通知メール制御マスタ
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "mail_control_master_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "通知メール制御マスタ", requiredMode = Schema.RequiredMode.REQUIRED)
	private MailControlMaster mailControlMaster;

	/**
	 * 件名/本文区分
	 */
	@Schema(description = "件名/本文区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "件名(\"0\"), 本文(\"1\"), 本文(\"2\")", example = "1")
	private SubjectVodyType subjectBodyType;

	/**
	 * 置換変数番号
	 */
	@Max(99)
	@Schema(description = "置換変数番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Integer replaceVariableNumber;

	/**
	 * 置換値エンティティ名
	 */
	@Size(max = 255)
	@Schema(description = "置換値エンティティ名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String replaceEntityName;

	/**
	 * 置換値フィールド名
	 */
	@Size(max = 255)
	@Schema(description = "置換値フィールド名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String replaceFieldName;

	/**
	 * 拡張項目フラグ
	 */
	@Max(9)
	@Schema(description = "拡張項目フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer extendsParameterFlg;

	/**
	 * 置換値マスタ名
	 */
	@Size(max = 255)
	@Schema(description = "置換値マスタ名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String replaceMasterName;

	/**
	 * 置換値マスタ検索エンティティ名
	 */
	@Size(max = 255)
	@Schema(description = "置換値マスタ検索エンティティ名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String replaceMasterSearchEntityName;

	/**
	 * 置換値マスタ検索フィールド名
	 */
	@Size(max = 255)
	@Schema(description = "置換値マスタ検索フィールド名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String replaceMasterSearchFieldName;

	/**
	 * 置換値区分
	 */
	@Schema(description = "置換値区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "エンティティ(\"1\"), 独自SQL(\"2\")", example = "1")
	private ConvertType convertType;

	/**
	 * SQL区分
	 */
	@Size(max = 255)
	@Schema(description = "SQL区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String sqlType;

}
