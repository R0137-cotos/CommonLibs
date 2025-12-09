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
import jakarta.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "mail_address_master")
public class MailAddressMaster extends EntityBaseMaster {

	@Description(value = "メールアドレス区分")
	public enum MailAddressDiv {

		TO("1"), CC("2"), BCC("3");

		private final String text;

		private MailAddressDiv(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static MailAddressDiv fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "サービスカテゴリ")
	public enum ServiceCategory {

		見積("1"), 契約("2"), 手配("3"), ライセンス("4");

		private final String text;

		private ServiceCategory(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static ServiceCategory fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mail_address_master_seq")
	@SequenceGenerator(name = "mail_address_master_seq", sequenceName = "mail_address_master_seq", allocationSize = 1)
	@Schema(description = "メールアドレスマスタID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * メールマスタ
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "mail_master_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "メールマスタ", requiredMode = Schema.RequiredMode.REQUIRED)
	private MailMaster mailMaster;

	/**
	 * メールアドレス区分
	 */
	@Schema(description = "メールアドレス区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "TO(\"1\"), CC(\"2\"), BCC(\"3\")")
	private MailAddressDiv mailAddressDiv;

	/**
	 * 対象エンティティ名
	 */
	@Size(max = 255)
	@Schema(description = "対象エンティティ名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String targetEntityName;

	/**
	 * 対象フィールド名
	 */
	@Size(max = 255)
	@Schema(description = "対象フィールド名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String targetFieldName;

	/**
	 * サービスカテゴリ
	 */
	@Schema(description = "サービスカテゴリ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "見積(\"1\"), 契約(\"2\"), 手配(\"3\"), ライセンス(\"4\")")
	private ServiceCategory serviceCategory;
}