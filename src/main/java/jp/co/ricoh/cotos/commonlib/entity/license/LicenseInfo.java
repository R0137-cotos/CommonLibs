package jp.co.ricoh.cotos.commonlib.entity.license;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OrderBy;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.ContractType;
import jp.co.ricoh.cotos.commonlib.entity.master.LicenseProcessMaster.OperationDiv;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * ライセンス情報を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true, exclude = {"licenseDetailList", "licenseProcessList", "licenseRemainingNumber", "licenseOperationLogList", "licenseAccount"})
@ToString(callSuper = true, exclude = {"licenseDetailList", "licenseProcessList", "licenseRemainingNumber", "licenseOperationLogList", "licenseAccount"})
@Data
@Table(name = "license_info")
public class LicenseInfo extends EntityBase {

	@Description(value = "キャンセル状態")
	public enum CancelStatus {

		未("0"), キャンセル済("1");

		private final String text;

		private CancelStatus(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static CancelStatus fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "CSV出力フラグ")
	public enum CsvOutputFlg {

		未出力("0"), 出力済("1");

		private final String text;

		private CsvOutputFlg(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static CsvOutputFlg fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "工程ロック状態")
	public enum ProcessLockStatus {

		ロック解除("0"), ロック状態("1");

		private final String text;

		private ProcessLockStatus(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static ProcessLockStatus fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * ライセンス情報ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "license_info_seq")
	@SequenceGenerator(name = "license_info_seq", sequenceName = "license_info_seq", allocationSize = 1)
	@Schema(description = "ライセンス情報ID(作成時不要)", required = true, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 契約ID
	 */
	@Column(nullable = false)
	@Min(0)
	@Schema(description = "契約ID", required = true, allowableValues = "range[0,9223372036854775807]")
	private long contractId;

	/**
	 * 恒久契約識別番号
	 */
	@Size(max = 255)
	@Schema(description = "恒久契約識別番号", required = false, allowableValues = "range[0,255]")
	private String immutableContIdentNumber;

	/**
	 * RJ管理番号
	 */
	@Size(max = 255)
	@Schema(description = "RJ管理番号", required = false, allowableValues = "range[0,255]")
	private String rjManageNumber;

	/**
	 * 契約番号
	 */
	@Size(max = 255)
	@Schema(description = "契約番号", required = false, allowableValues = "range[0,255]")
	private String contractNumber;

	/**
	 * 契約番号枝番
	 */
	@Column(nullable = false)
	@Max(99)
	@Min(0)
	@Schema(description = "契約番号枝番", required = true, allowableValues = "range[0,99]")
	private int contractBranchNumber;

	/**
	 * 契約種別
	 */
	@Schema(description = "契約種別", required = true, allowableValues = "新規(\"1\"), 契約変更(\"2\"), 情報変更(\"3\"), 契約更新(\"4\")")
	private ContractType contractType;

	/**
	 * 契約種別詳細
	 */
	@Size(max = 255)
	@Schema(description = "契約種別詳細", required = true, allowableValues = "range[0,255]")
	private String contractTypeDetail;

	/**
	 * 解約フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "解約フラグ", required = false, allowableValues = "range[0,9]")
	private Integer disengagementFlg;

	/**
	 * ライセンス区分マスタID
	 */
	@Column(nullable = false)
	@Min(0)
	@Schema(description = "ライセンス区分マスタID", required = true, allowableValues = "range[0,9223372036854775807]")
	private long licenseDivMasterId;

	/**
	 * 完了工程順
	 */
	@Max(999)
	@Min(0)
	@Schema(description = "完了工程順", required = false, allowableValues = "range[0,999]")
	private Integer completeProcessOrder;

	/**
	 * 完了ライセンス工程マスタID
	 */
	@Min(0)
	@Schema(description = "完了ライセンス工程マスタID", required = false, allowableValues = "range[0,9223372036854775807]")
	private Long completeProcessMasterId;

	/**
	 * 完了手配業務ID
	 */
	@Min(0)
	@Schema(description = "完了手配業務ID", required = false, allowableValues = "range[0,9223372036854775807]")
	private Long completeArrangementWorkId;

	/**
	 * 完了操作区分
	 */
	@Schema(description = "完了操作区分", required = false, allowableValues = "受付(\"1\"), ボタン(\"2\"), CSV出力(\"3\"), CSV取込(\"4\")")
	private OperationDiv completeOperationDiv;

	/**
	 * 作業中工程順
	 */
	@Max(999)
	@Min(0)
	@Schema(description = "作業中工程順", required = false, allowableValues = "range[0,999]")
	private Integer workingProcessOrder;

	/**
	 * 作業中ライセンス工程マスタID
	 */
	@Min(0)
	@Schema(description = "作業中ライセンス工程マスタID", required = false, allowableValues = "range[0,9223372036854775807]")
	private Long workingProcessMasterId;

	/**
	 * 作業中手配業務ID
	 *
	 */
	@Min(0)
	@Schema(description = "作業中手配業務ID", required = false, allowableValues = "range[0,9223372036854775807]")
	private Long workingArrangementWorkId;

	/**
	 * 作業中操作区分
	 */
	@Schema(description = "作業中操作区分", required = false, allowableValues = "受付(\"1\"), ボタン(\"2\"), CSV出力(\"3\"), CSV取込(\"4\")")
	private OperationDiv workingOperationDiv;

	/**
	 * キャンセル状態
	 */
	@Schema(description = "キャンセル状態", required = false, allowableValues = "未(\"0\"), キャンセル済(\"1\")")
	private CancelStatus cancelStatus;

	/**
	 * CSV出力フラグ
	 */
	@Schema(description = "CSV出力フラグ", required = false, allowableValues = "未出力(\"0\"), 出力済(\"1\")")
	private CsvOutputFlg csvOutputFlg;

	/**
	 * CSV出力日時
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "CSV出力日時", required = false)
	private Date csvOutputAt;

	/**
	 * メールアドレス
	 */
	@Size(max = 255)
	@Schema(description = "メールアドレス", required = false, allowableValues = "range[0,255]")
	private String mailAddress;

	/**
	 * 工程ロック状態
	 */
	@Schema(description = "工程ロック状態", required = false, allowableValues = "ロック解除(\"0\"), ロック状態(\"1\")")
	private ProcessLockStatus processLockStatus;

	/**
	 * 拡張項目
	 */
	@Schema(description = "拡張項目", required = false)
	@Lob
	private String extendsParameter;

	/**
	 * ライセンス明細
	 */
	@OneToMany(mappedBy = "licenseInfo")
	@OrderBy("seqNumber ASC")
	@Schema(description = "ライセンス明細", required = false)
	private List<LicenseDetail> licenseDetailList;

	/**
	 * ライセンス工程
	 */
	@OneToMany(mappedBy = "licenseInfo")
	@OrderBy("processOrder ASC")
	@Schema(description = "ライセンス工程", required = true)
	private List<LicenseProcess> licenseProcessList;

	/**
	 * ライセンス残数
	 */
	@OneToOne(mappedBy = "licenseInfo")
	@Schema(description = "ライセンス残数", required = false)
	private LicenseRemainingNumber licenseRemainingNumber;

	/**
	 * ライセンス情報操作履歴
	 */
	@OneToMany(mappedBy = "licenseInfo")
	@Schema(description = "ライセンス情報操作履歴", required = false)
	private List<LicenseInfoOperationLog> licenseOperationLogList;

	/**
	 * ライセンスアカウント
	 */
	@ManyToOne(optional = true)
	@JoinColumn(name = "license_account_id", referencedColumnName = "id")
	@Schema(description = "ライセンスアカウント", required = true)
	private LicenseAccount licenseAccount;

}
