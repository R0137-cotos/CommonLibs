package jp.co.ricoh.cotos.commonlib.dto.result;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Version;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.ContractType;
import jp.co.ricoh.cotos.commonlib.entity.license.LicenseAccount;
import jp.co.ricoh.cotos.commonlib.entity.license.LicenseInfo.CancelStatus;
import jp.co.ricoh.cotos.commonlib.entity.license.LicenseInfo.CsvOutputFlg;
import jp.co.ricoh.cotos.commonlib.entity.license.LicenseInfo.ProcessLockStatus;
import jp.co.ricoh.cotos.commonlib.entity.license.LicenseInfoOperationLog;
import jp.co.ricoh.cotos.commonlib.entity.license.LicenseRemainingNumber;
import jp.co.ricoh.cotos.commonlib.entity.master.LicenseProcessMaster.OperationDiv;
import lombok.Data;

/**
 * ライセンス情報を取得するためのDTOです。
 */
@Entity
@Data
public class LicenseInfoResult {

	/**
	 * ライセンス情報ID
	 */
	@Id
	@Schema(description = "ライセンス情報ID(作成時不要)", required = true, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * ライセンス情報version
	 */
	@Version
	@Schema(description = "ライセンス情報version(作成時不要)", required = true, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long version;

	/**
	 * 契約ID
	 */
	@Schema(description = "契約ID", required = true, allowableValues = "range[0,9223372036854775807]")
	private long contractId;

	/**
	 * 恒久契約識別番号
	 */
	@Schema(description = "恒久契約識別番号", required = false, allowableValues = "range[0,255]")
	private String immutableContIdentNumber;

	/**
	 * RJ管理番号
	 */
	@Schema(description = "RJ管理番号", required = false, allowableValues = "range[0,255]")
	private String rjManageNumber;

	/**
	 * 契約番号
	 */
	@Schema(description = "契約番号", required = false, allowableValues = "range[0,255]")
	private String contractNumber;

	/**
	 * 契約番号枝番
	 */
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
	@Schema(description = "契約種別詳細", required = true, allowableValues = "range[0,255]")
	private String contractTypeDetail;

	/**
	 * 解約フラグ
	 */
	@Schema(description = "解約フラグ", required = false, allowableValues = "range[0,9]")
	private Integer disengagementFlg;

	/**
	 * ライセンス区分マスタID
	 */
	@Schema(description = "ライセンス区分マスタID", required = true, allowableValues = "range[0,9223372036854775807]")
	private long licenseDivMasterId;

	/**
	 * 完了工程順
	 */
	@Schema(description = "完了工程順", required = false, allowableValues = "range[0,999]")
	private Integer completeProcessOrder;

	/**
	 * 完了ライセンス工程マスタID
	 */
	@Schema(description = "完了ライセンス工程マスタID", required = false, allowableValues = "range[0,9223372036854775807]")
	private Long completeProcessMasterId;

	/**
	 * 完了手配業務ID
	 */
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
	@Schema(description = "作業中工程順", required = false, allowableValues = "range[0,999]")
	private Integer workingProcessOrder;

	/**
	 * 作業中ライセンス工程マスタID
	 */
	@Schema(description = "作業中ライセンス工程マスタID", required = false, allowableValues = "range[0,9223372036854775807]")
	private Long workingProcessMasterId;

	/**
	 * 作業中手配業務ID
	 *
	 */
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
	private String extendsParameter;

	/**
	 * ライセンス明細
	 */
	@OneToMany
	@Schema(description = "ライセンス明細", required = false)
	private List<LicenseDetailResult> licenseDetailList;

	/**
	 * ライセンス工程DTO
	 */
	@OneToMany
	@Schema(description = "ライセンス工程DTO", required = true)
	private List<LicenseProcessResult> licenseProcessResultList;

	/**
	 * ライセンス残数
	 */
	@OneToOne
	@Schema(description = "ライセンス残数", required = false)
	private LicenseRemainingNumber licenseRemainingNumber;

	/**
	 * ライセンス情報操作履歴
	 */
	@OneToMany
	@Schema(description = "ライセンス情報操作履歴", required = false)
	private List<LicenseInfoOperationLog> licenseOperationLogList;

	/**
	 * ライセンスアカウント
	 */
	@ManyToOne(optional = true)
	@Schema(description = "ライセンスアカウント", required = false)
	private LicenseAccount licenseAccount;
}
