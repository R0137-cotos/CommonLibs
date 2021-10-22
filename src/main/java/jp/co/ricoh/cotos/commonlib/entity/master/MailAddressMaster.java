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
import javax.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(value = "メールアドレスマスタID", required = true, position = 1, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * メールマスタ
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "mail_master_id", referencedColumnName = "id")
	@JsonIgnore
	@ApiModelProperty(value = "メールマスタ", required = true, position = 2)
	private MailMaster mailMaster;

	/**
	 * メールアドレス区分
	 */
	@ApiModelProperty(value = "メールアドレス区分", required = false, position = 3, allowableValues = "TO(\"1\"), CC(\"2\"), BCC(\"3\")")
	private MailAddressDiv mailAddressDiv;

	/**
	 * 対象エンティティ名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "対象エンティティ名", required = false, position = 4, allowableValues = "range[0,255]")
	private String targetEntityName;

	/**
	 * 対象フィールド名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "対象フィールド名", required = false, position = 5, allowableValues = "range[0,255]")
	private String targetFieldName;

	/**
	 * サービスカテゴリ
	 */
	@ApiModelProperty(value = "サービスカテゴリ", required = false, position = 6, allowableValues = "見積(\"1\"), 契約(\"2\"), 手配(\"3\"), ライセンス(\"4\")")
	private ServiceCategory serviceCategory;
}