package jp.co.ricoh.cotos.commonlib.entity.communication;

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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 問い合わせ宛先を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "contact_to")
public class ContactTo extends EntityBase {

	@Description(value = "送信タイプ")
	public enum SendType {

		TO("1"), CC("2");

		private final String text;

		private SendType(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static SendType fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contact_to_seq")
	@SequenceGenerator(name = "contact_to_seq", sequenceName = "contact_to_seq", allocationSize = 1)
	@Schema(description = "宛先ID (作成時不要)", required = true, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 問い合わせ
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "contact_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "問い合わせ", required = true)
	private Contact contact;

	/**
	 * 送信タイプ
	 */
	@Schema(description = "送信タイプ", required = false, allowableValues = "TO(\"1\"), CC(\"2\")", example = "1")
	private SendType sendType;

	/**
	 * 宛先MoM社員ID
	 */
	@Column(nullable = false)
	@NotNull
	@Size(max = 255)
	@Schema(description = "宛先MoM社員ID", required = true, allowableValues = "range[0,255]")
	private String contactToEmpId;

	/**
	 * 宛先メールアドレス
	 */
	@Size(max = 255)
	@Schema(description = "宛先メールアドレス", required = false, allowableValues = "range[0,255]")
	private String contactToEmail;

	/**
	 * 宛先氏名
	 */
	@Size(max = 255)
	@Schema(description = "宛先氏名", required = false, allowableValues = "range[0,255]")
	private String contactToEmpName;
}
