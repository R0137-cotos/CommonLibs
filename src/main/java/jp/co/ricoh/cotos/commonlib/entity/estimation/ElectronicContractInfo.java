package jp.co.ricoh.cotos.commonlib.entity.estimation;

import java.util.Arrays;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 電子契約情報を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "electronic_contract_info")
public class ElectronicContractInfo extends EntityBase {

	@Description(value = "お客様ご担当者区分")
	public enum CustomerPicDiv {
		お客様情報を使用する("1"), 決裁権限者をお客様担当者に設定する("2"), 一次承認者をお客様担当者に設定する("3"), お客様にクラウドサイン上で入力いただく("4");

		private final String text;

		private CustomerPicDiv(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static CustomerPicDiv fromString(final String string) {
			if (StringUtils.isEmpty(string)) {
				return null;
			}
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "利用開始希望日区分")
	public enum DesiredStartDateDiv {
		SA入力("1"), お客様に入力いただく("2");

		private final String text;

		private DesiredStartDateDiv(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static DesiredStartDateDiv fromString(final String string) {
			if (StringUtils.isEmpty(string)) {
				return null;
			}
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "electronic_contract_info_seq")
	@SequenceGenerator(name = "electronic_contract_info_seq", sequenceName = "electronic_contract_info_seq", allocationSize = 1)
	@Schema(description = "ID(作成時不要)", required = true, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 見積
	 */
	@OneToOne(optional = false)
	@JsonIgnore
	@JoinColumn(name = "estimation_id", referencedColumnName = "id")
	@Schema(description = "見積", requiredMode = Schema.RequiredMode.REQUIRED)
	private Estimation estimation;

	/**
	 * イントラマート電子契約事前確認申請管理番号
	 */
	@Size(max = 255)
	@Schema(description = "イントラマート電子契約事前確認申請管理番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String imfrSdInsertId;

	/**
	 * イントラマート電子契約事前確認申請行番号
	 */
	@Size(max = 255)
	@Schema(description = "イントラマート電子契約事前確認申請行番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String imfrSdRowNo;

	/**
	 * 企業ID
	 */
	@Size(max = 255)
	@Schema(description = "企業ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String companyId;

	/**
	 * 企業名
	 */
	@Size(max = 255)
	@Schema(description = "企業名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String companyName;

	/**
	 * 事業所ID
	 */
	@Size(max = 255)
	@Schema(description = "事業所ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String officeId;

	/**
	 * 事業所名
	 */
	@Size(max = 255)
	@Schema(description = "事業所名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String officeName;

	/**
	 * 一次承認者名
	 */
	@Size(max = 255)
	@Schema(description = "一次承認者名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String primaryApprover;

	/**
	 * 一次承認者所属
	 */
	@Size(max = 255)
	@Schema(description = "一次承認者所属", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String primaryDep;

	/**
	 * 一次承認者役職
	 */
	@Size(max = 255)
	@Schema(description = "一次承認者役職", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String primaryPostname;

	/**
	 * 一次承認者メールアドレス
	 */
	@Size(max = 255)
	@Schema(description = "一次承認者メールアドレス", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String primaryEmail;

	/**
	 * 決裁者名
	 */
	@Size(max = 255)
	@Schema(description = "決裁者名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String finalApprover;

	/**
	 * 決裁者所属
	 */
	@Size(max = 255)
	@Schema(description = "決裁者所属", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String finalDep;

	/**
	 * 決裁者役職
	 */
	@Size(max = 255)
	@Schema(description = "決裁者役職", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String finalPostname;

	/**
	 * 決裁者メールアドレス
	 */
	@Size(max = 255)
	@Schema(description = "決裁者メールアドレス", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String finalEmail;

	/**
	 * お客様ご担当者区分
	 */
	@Schema(description = "お客様ご担当者区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "お客様情報を使用する(\"1\"), 決裁権限者をお客様担当者に設定する(\"2\"), 一次承認者をお客様担当者に設定する(\"3\"), お客様にクラウドサイン上で入力いただく(\"4\")")
	private CustomerPicDiv customerPicDiv;

	/**
	 * 利用開始希望日区分
	 */
	@Schema(description = "利用開始希望日区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "SA入力(\"1\"), お客様に入力いただく(\"2\")")
	private DesiredStartDateDiv desiredStartDateDiv;

	/**
	 * サービス利用希望日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "サービス利用希望日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date conclusionPreferredDate;

	/**
	 * 支払条件
	 */
	@Size(max = 255)
	@Schema(description = "支払条件", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String paymentTerms;

	/**
	 * 特記事項
	 */
	@Size(max = 255)
	@Schema(description = "特記事項", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String specialNotes;

	/**
	 * 電子契約ドキュメントキー
	 */
	@Size(max = 255)
	@Schema(description = "電子契約ドキュメントキー", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String electronicContractDocumentKey;

	/**
	 * 電子契約ドキュメントID
	 */
	@Size(max = 255)
	@Schema(description = "電子契約ドキュメントID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String electronicContractDocumentId;

	/**
	 * 電子契約文書登録日時
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "電子契約文書登録日時", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date electronicContractDocumentCreatedAt;
}
