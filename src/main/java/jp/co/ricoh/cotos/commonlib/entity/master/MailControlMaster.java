package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.Arrays;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.ServiceCategory;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 通知メール制御マスタを表すEntity
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "mail_control_master")
public class MailControlMaster extends EntityBaseMaster {

	@Description(value = "通知日タイミング区分")
	public enum NotificationTimingType {

		対象日イコール("0"), 対象日以降("1");

		private final String text;

		private NotificationTimingType(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static NotificationTimingType fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "通知日種別")
	public enum NotificationDateType {

		トランザクションの日付カラム("0"), 最終承認依頼日("1"), 契約の日付カラム_手配用("2"), バウンスメール送信日("3");

		private final String text;

		private NotificationDateType(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static NotificationDateType fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}

	}

	@Description(value = "通知日計算種別")
	public enum NotificationDateCalcType {

		暦日("0"), 営業日("1");

		private final String text;

		private NotificationDateCalcType(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static NotificationDateCalcType fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mail_control_master_seq")
	@SequenceGenerator(name = "mail_control_master_seq", sequenceName = "mail_control_master_seq", allocationSize = 1)
	@Schema(description = "通知メール制御マスタID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 対象ドメイン
	 */
	@Column(nullable = false)
	@NotNull
	@Schema(description = "対象ドメイン", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "見積(\"1\"), 契約(\"2\"), 手配(\"3\")", example = "1")
	private ServiceCategory serviceCategory;

	/**
	 * 対象トランザクションテーブル名
	 */
	@Size(max = 255)
	@Schema(description = "対象トランザクションテーブル名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String targetTransactionTableName;

	/**
	 * 対象カラム名
	 */
	@Size(max = 255)
	@Schema(description = "対象カラム名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String targetColumnName;

	/**
	 * 通知日差分
	 */
	@Max(9)
	@Schema(description = "通知日差分", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Integer notificationDateDifference;

	/**
	 * 通知日タイミング区分
	 */
	@Schema(description = "通知日タイミング区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "対象日イコール(\"0\"), 対象日以降(\"1\")", example = "1")
	private NotificationTimingType notificationTimingType;

	/**
	 * 宛先テーブル区分（To）
	 */
	@Size(max = 255)
	@Schema(description = "宛先テーブル名区分（To）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String contactTableTypeTo;

	/**
	 * 宛先テーブル区分（Cc）
	 */
	@Size(max = 255)
	@Schema(description = "宛先テーブル区分（Cc）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String contactTableTypeCc;

	/**
	 * 宛先テーブル区分（Bcc）
	 */
	@Size(max = 255)
	@Schema(description = "宛先テーブル区分（Bcc）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String contactTableTypeBcc;

	/**
	 * メールテンプレートマスタ
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "mail_template_master_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "メールテンプレートマスタ", requiredMode = Schema.RequiredMode.REQUIRED)
	private MailTemplateMaster mailTemplateMaster;

	/**
	 * 条件式追加フラグ
	 */
	@Schema(description = "条件式追加フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Long extendsQueryFlg;

	/**
	 * 追加条件式
	 */
	@Schema(description = "追加条件式", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String extendsQuery;

	/**
	 * 監査証跡送付フラグ
	 */
	@Schema(description = "監査証跡送付フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Long auditTrailSendingFlg;

	/**
	 * メール変換値マスタ
	 */
	@OneToMany(mappedBy = "mailControlMaster")
	@Schema(description = "メール変換値マスタ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<MailConvertValueMaster> MailConvertValueMasterList;

	/**
	 *  通知メール対象商材マスタ
	 */
	@OneToMany(mappedBy = "mailControlMaster")
	@Schema(description = "通知メール対象商材マスタ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<MailProductMaster> mailProductMasterList;

	/**
	 * 処理実行日計算パターンマスタID
	 */
	@ManyToOne
	@JoinColumn(name = "process_day_calc_pattern_master_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "処理実行日計算パターンマスタ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private DateCalcPatternMaster processDayCalcPatternMaster;

	/**
	 * エラー通知メール制御マスタID
	 */
	@ManyToOne
	@JoinColumn(name = "error_mail_control_master_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "エラー通知メール制御マスタID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private MailControlMaster errorMailControlMasterId;

	/**
	 * 通知日種別
	 */
	@Schema(description = "通知日種別", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "トランザクションの日付カラム(\"0\"), 最終承認依頼日(\"1\"), 契約の日付カラム_手配用(\"2\"), バウンスメール送信日(\"3\")", example = "1")
	private NotificationDateType notificationDateType;

	/**
	 * 手配業務タイプマスタID
	 */
	@Schema(description = "手配業務タイプマスタID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private Long arrangementWorkTypeMasterId;

	/**
	 * 通知日計算種別
	 */
	@Schema(description = "通知日計算種別", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "暦日(\"0\"), 営業日(\"1\")", example = "1")
	private NotificationDateCalcType notificationDateCalcType;
}
