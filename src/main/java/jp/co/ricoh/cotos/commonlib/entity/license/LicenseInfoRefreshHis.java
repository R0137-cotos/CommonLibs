package jp.co.ricoh.cotos.commonlib.entity.license;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.ContractType;
import jp.co.ricoh.cotos.commonlib.entity.license.LicenseInfo.CancelStatus;
import jp.co.ricoh.cotos.commonlib.entity.license.LicenseInfo.CsvOutputFlg;
import jp.co.ricoh.cotos.commonlib.entity.license.LicenseInfo.ProcessLockStatus;
import jp.co.ricoh.cotos.commonlib.entity.master.LicenseProcessMaster.OperationDiv;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ライセンス情報洗い替え履歴を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "license_info_refresh_his")
public class LicenseInfoRefreshHis extends EntityBase {

	/**
	 * ライセンス情報洗い替え履歴ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "license_info_refresh_his_seq")
	@SequenceGenerator(name = "license_info_refresh_his_seq", sequenceName = "license_info_refresh_his_seq", allocationSize = 1)
	@Schema(description = "ライセンス情報洗い替え履歴ID(作成時不要)", required = true, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * ライセンス情報ID
	 */
	@Column(nullable = false)
	@Min(0)
	@Schema(description = "ライセンス情報ID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private long licenseInfoId;

	/**
	 * 契約ID
	 */
	@Column(nullable = false)
	@Min(0)
	@Schema(description = "契約ID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private long contractId;

	/**
	 * 恒久契約識別番号
	 */
	@Size(max = 255)
	@Schema(description = "恒久契約識別番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String immutableContIdentNumber;

	/**
	 * RJ管理番号
	 */
	@Size(max = 255)
	@Schema(description = "RJ管理番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String rjManageNumber;

	/**
	 * 契約番号
	 */
	@Size(max = 255)
	@Schema(description = "契約番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String contractNumber;

	/**
	 * 契約番号枝番
	 */
	@Column(nullable = false)
	@Max(99)
	@Min(0)
	@Schema(description = "契約番号枝番", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,99]")
	private int contractBranchNumber;

	/**
	 * 契約種別
	 */
	@Schema(description = "契約種別", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "新規(\"1\"), 契約変更(\"2\"), 情報変更(\"3\"), 契約更新(\"4\")")
	private ContractType contractType;

	/**
	 * 契約種別詳細
	 */
	@Size(max = 255)
	@Schema(description = "契約種別詳細", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String contractTypeDetail;

	/**
	 * 解約フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "解約フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer disengagementFlg;

	/**
	 * ライセンス区分マスタID
	 */
	@Column(nullable = false)
	@Min(0)
	@Schema(description = "ライセンス区分マスタID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private long licenseDivMasterId;

	/**
	 * 完了工程順
	 */
	@Max(999)
	@Min(0)
	@Schema(description = "完了工程順", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,999]")
	private Integer completeProcessOrder;

	/**
	 * 完了ライセンス工程マスタID
	 */
	@Min(0)
	@Schema(description = "完了ライセンス工程マスタID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private Long completeProcessMasterId;

	/**
	 * 完了手配業務ID
	 */
	@Min(0)
	@Schema(description = "完了手配業務ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private Long completeArrangementWorkId;

	/**
	 * 完了操作区分
	 */
	@Schema(description = "完了操作区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "受付(\"1\"), ボタン(\"2\"), CSV出力(\"3\"), CSV取込(\"4\")")
	private OperationDiv completeOperationDiv;

	/**
	 * 作業中工程順
	 */
	@Max(999)
	@Min(0)
	@Schema(description = "作業中工程順", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,999]")
	private Integer workingProcessOrder;

	/**
	 * 作業中ライセンス工程マスタID
	 */
	@Min(0)
	@Schema(description = "作業中ライセンス工程マスタID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private Long workingProcessMasterId;

	/**
	 * 作業中手配業務ID
	 *
	 */
	@Min(0)
	@Schema(description = "作業中手配業務ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private Long workingArrangementWorkId;

	/**
	 * 作業中操作区分
	 */
	@Schema(description = "作業中操作区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "受付(\"1\"), ボタン(\"2\"), CSV出力(\"3\"), CSV取込(\"4\")")
	private OperationDiv workingOperationDiv;

	/**
	 * キャンセル状態
	 */
	@Schema(description = "キャンセル状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未(\"0\"), キャンセル済(\"1\")")
	private CancelStatus cancelStatus;

	/**
	 * CSV出力フラグ
	 */
	@Schema(description = "CSV出力フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未出力(\"0\"), 出力済(\"1\")")
	private CsvOutputFlg csvOutputFlg;

	/**
	 * CSV出力日時
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "CSV出力日時", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date csvOutputAt;

	/**
	 * メールアドレス
	 */
	@Size(max = 255)
	@Schema(description = "メールアドレス", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String mailAddress;

	/**
	 * 工程ロック状態
	 */
	@Schema(description = "工程ロック状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "ロック解除(\"0\"), ロック状態(\"1\")")
	private ProcessLockStatus processLockStatus;

	/**
	 * 拡張項目
	 */
	@Schema(description = "拡張項目", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Lob
	private String extendsParameter;

	/**
	 * ライセンスアカウントID
	 */
	@Min(0)
	@Schema(description = "ライセンスアカウントID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private Long licenseAccountId;

	/**
	 * 洗替日
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "洗替日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date refreshedAt;

}
