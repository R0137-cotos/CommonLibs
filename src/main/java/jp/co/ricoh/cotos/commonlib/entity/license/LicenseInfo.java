package jp.co.ricoh.cotos.commonlib.entity.license;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.ContractType;
import jp.co.ricoh.cotos.commonlib.entity.master.LicenseProcessMaster.OperationDiv;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ライセンス情報を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
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
	@ApiModelProperty(value = "ライセンス情報ID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 契約ID
	 */
	@Column(nullable = false)
	@Min(0)
	@ApiModelProperty(value = "契約ID", required = true, position = 2, allowableValues = "range[0,9223372036854775807]")
	private long contractId;

	/**
	 * 恒久契約識別番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "恒久契約識別番号", required = false, position = 3, allowableValues = "range[0,255]")
	private String immutableContIdentNumber;

	/**
	 * RJ管理番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "RJ管理番号", required = false, position = 4, allowableValues = "range[0,255]")
	private String rjManageNumber;

	/**
	 * 契約番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "契約番号", required = false, position = 5, allowableValues = "range[0,255]")
	private String contractNumber;

	/**
	 * 契約番号枝番
	 */
	@Column(nullable = false)
	@Max(99)
	@Min(0)
	@ApiModelProperty(value = "契約番号枝番", required = true, position = 6, allowableValues = "range[0,99]")
	private int contractBranchNumber;

	/**
	 * 契約種別
	 */
	@ApiModelProperty(value = "契約種別", required = true, allowableValues = "新規(\"1\"), 契約変更(\"2\"), 情報変更(\"3\"), 契約更新(\"4\")", position = 7)
	private ContractType contractType;

	/**
	 * 契約種別詳細
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "契約種別詳細", required = true, position = 8, allowableValues = "range[0,255]")
	private String contractTypeDetail;

	/**
	 * 解約フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "解約フラグ", required = false, position = 9, allowableValues = "range[0,9]")
	private Integer disengagementFlg;

	/**
	 * ライセンス区分マスタID
	 */
	@Column(nullable = false)
	@Min(0)
	@ApiModelProperty(value = "ライセンス区分マスタID", required = true, position = 10, allowableValues = "range[0,9223372036854775807]")
	private long licenseDivMasterId;

	/**
	 * 完了工程順
	 */
	@Max(999)
	@Min(0)
	@ApiModelProperty(value = "完了工程順", required = false, position = 11, allowableValues = "range[0,999]")
	private Integer completeProcessOrder;

	/**
	 * 完了ライセンス工程マスタID
	 */
	@Min(0)
	@ApiModelProperty(value = "完了ライセンス工程マスタID", required = false, position = 12, allowableValues = "range[0,9223372036854775807]")
	private Long completeProcessMasterId;

	/**
	 * 完了手配業務ID
	 */
	@Min(0)
	@ApiModelProperty(value = "完了手配業務ID", required = false, position = 13, allowableValues = "range[0,9223372036854775807]")
	private Long completeArrangementWorkId;

	/**
	 * 完了操作区分
	 */
	@ApiModelProperty(value = "完了操作区分", required = false, allowableValues = "受付(\"1\"), ボタン(\"2\"), CSV出力(\"3\"), CSV取込(\"4\")", position = 14)
	private OperationDiv completeOperationDiv;

	/**
	 * 作業中工程順
	 */
	@Max(999)
	@Min(0)
	@ApiModelProperty(value = "作業中工程順", required = false, position = 15, allowableValues = "range[0,999]")
	private Integer workingProcessOrder;

	/**
	 * 作業中ライセンス工程マスタID
	 */
	@Min(0)
	@ApiModelProperty(value = "作業中ライセンス工程マスタID", required = false, position = 16, allowableValues = "range[0,9223372036854775807]")
	private Long workingProcessMasterId;

	/**
	 * 作業中手配業務ID
	 *
	 */
	@Min(0)
	@ApiModelProperty(value = "作業中手配業務ID", required = false, position = 17, allowableValues = "range[0,9223372036854775807]")
	private Long workingArrangementWorkId;

	/**
	 * 作業中操作区分
	 */
	@ApiModelProperty(value = "作業中操作区分", required = false, allowableValues = "受付(\"1\"), ボタン(\"2\"), CSV出力(\"3\"), CSV取込(\"4\")", position = 18)
	private OperationDiv workingOperationDiv;

	/**
	 * キャンセル状態
	 */
	@ApiModelProperty(value = "キャンセル状態", required = false, position = 19, allowableValues = "未(\"0\"), キャンセル済(\"1\")")
	private CancelStatus cancelStatus;

	/**
	 * CSV出力フラグ
	 */
	@ApiModelProperty(value = "CSV出力フラグ", required = false, position = 20, allowableValues = "未出力(\"0\"), 出力済(\"1\")")
	private CsvOutputFlg csvOutputFlg;

	/**
	 * CSV出力日時
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@ApiModelProperty(value = "CSV出力日時", required = false, position = 21)
	private Date csvOutputAt;

	/**
	 * メールアドレス
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "メールアドレス", required = false, position = 22, allowableValues = "range[0,255]")
	private String mailAddress;

	/**
	 * 工程ロック状態
	 */
	@ApiModelProperty(value = "工程ロック状態", required = false, position = 23, allowableValues = "ロック解除(\"0\"), ロック状態(\"1\")")
	private ProcessLockStatus processLockStatus;

	/**
	 * 拡張項目
	 */
	@ApiModelProperty(value = "拡張項目", required = false, position = 24)
	@Lob
	private String extendsParameter;

	/**
	 * ライセンス明細
	 */
	@OneToMany(mappedBy = "licenseInfo")
	@OrderBy("seqNumber ASC")
	@ApiModelProperty(value = "ライセンス明細", required = false, position = 25)
	private List<LicenseDetail> licenseDetailList;

	/**
	 * ライセンス工程
	 */
	@OneToMany(mappedBy = "licenseInfo")
	@OrderBy("processOrder ASC")
	@ApiModelProperty(value = "ライセンス工程", required = true, position = 26)
	private List<LicenseProcess> licenseProcessList;

	/**
	 * ライセンス残数
	 */
	@OneToOne(mappedBy = "licenseInfo")
	@ApiModelProperty(value = "ライセンス残数", required = false, position = 27)
	private LicenseRemainingNumber licenseRemainingNumber;

	/**
	 * ライセンス情報操作履歴
	 */
	@OneToMany(mappedBy = "licenseInfo")
	@ApiModelProperty(value = "ライセンス情報操作履歴", required = false, position = 28)
	private List<LicenseInfoOperationLog> licenseOperationLogList;

	/**
	 * ライセンスアカウント
	 */
	@ManyToOne(optional = true)
	@JoinColumn(name = "license_account_id", referencedColumnName = "id")
	@ApiModelProperty(value = "ライセンスアカウント", required = true, position = 29)
	private LicenseAccount licenseAccount;

}
