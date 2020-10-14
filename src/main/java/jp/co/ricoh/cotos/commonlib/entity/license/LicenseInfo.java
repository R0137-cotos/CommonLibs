package jp.co.ricoh.cotos.commonlib.entity.license;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
	@NotNull
	@Column(nullable = false)
	@Min(0)
	@ApiModelProperty(value = "契約ID", required = true, position = 2, allowableValues = "range[0,9223372036854775807]")
	private Long contractId;

	/**
	 * RJ管理番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "RJ管理番号", required = false, position = 3, allowableValues = "range[0,255]")
	private String rjManageNumber;

	/**
	 * 契約番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "契約番号", required = false, position = 4, allowableValues = "range[0,255]")
	private String contractNumber;

	/**
	 * 契約番号枝番
	 */
	@Column(nullable = false)
	@ApiModelProperty(value = "契約番号枝番", required = true, position = 5, allowableValues = "range[0,99]")
	private int contractBranchNumber;

	/**
	 * 契約種別
	 */
	@ApiModelProperty(value = "契約種別", required = true, allowableValues = "新規(\"1\"), 契約変更(\"2\"), 情報変更(\"3\"), 契約更新(\"4\")", position = 6)
	private ContractType contractType;

	/**
	 * 契約種別詳細
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "契約種別詳細", required = true, position = 7, allowableValues = "range[0,255]")
	private String contractTypeDetail;

	/**
	 * ライセンス区分
	 */
	@NotNull
	@Column(nullable = false)
	@Size(max = 255)
	@ApiModelProperty(value = "ライセンス区分", required = true, position = 8, allowableValues = "range[0,255]")
	private String licenseDiv;

	/**
	 * 完了工程順
	 */
	@ApiModelProperty(value = "完了工程順", required = false, position = 9, allowableValues = "range[0,999]")
	private int completeProcessOrder;

	/**
	 * 完了工程ID
	 */
	@Min(0)
	@ApiModelProperty(value = "完了工程ID", required = false, position = 10, allowableValues = "range[0,9223372036854775807]")
	private Long completeProcessId;

	/**
	 * 完了手配業務ID
	 */
	@Min(0)
	@ApiModelProperty(value = "完了手配業務ID", required = false, position = 11, allowableValues = "range[0,9223372036854775807]")
	private Long completeArrangementWorkId;

	/**
	 * 完了操作区分
	 */
	@ApiModelProperty(value = "完了操作区分", required = false, allowableValues = "受付(\"1\"), ボタン(\"2\"), CSV出力(\"3\"), CSV取込(\"4\")", position = 12)
	private OperationDiv completeOperationDiv;

	/**
	 * 作業中工程順
	 */
	@ApiModelProperty(value = "作業中工程順", required = false, position = 13, allowableValues = "range[0,999]")
	private int workingProcessOrder;

	/**
	 * 作業中工程ID
	 */
	@Min(0)
	@ApiModelProperty(value = "作業中工程ID", required = false, position = 14, allowableValues = "range[0,9223372036854775807]")
	private Long workingProcessId;

	/**
	 * 作業中手配業務ID
	 *
	 */
	@Min(0)
	@ApiModelProperty(value = "作業中手配業務ID", required = false, position = 15, allowableValues = "range[0,9223372036854775807]")
	private Long workingArrangementWorkId;

	/**
	 * 作業中操作区分
	 */
	@ApiModelProperty(value = "作業中操作区分", required = false, allowableValues = "受付(\"1\"), ボタン(\"2\"), CSV出力(\"3\"), CSV取込(\"4\")", position = 16)
	private OperationDiv workingArrangementWorkOperationDiv;

	/**
	 * キャンセル状態
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "キャンセル状態", required = false, position = 17, allowableValues = "range[0,9]")
	private Integer cancelStatus;

	/**
	 * CSV出力フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "CSV出力フラグ", required = false, position = 18, allowableValues = "range[0,9]")
	private Integer csvOutputFlg;

	/**
	 * CSV出力日時
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@ApiModelProperty(value = "CSV出力日時", required = false, position = 19)
	private Date csvOutputAt;

	/**
	 * メールアドレス
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "メールアドレス", required = false, position = 20, allowableValues = "range[0,255]")
	private String mailAddress;

	/**
	 * 工程ロック状態
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "工程ロック状態", required = false, position = 21, allowableValues = "range[0,9]")
	private Integer processLockStatus;

	/**
	 * 拡張項目
	 */
	@ApiModelProperty(value = "拡張項目", required = false, position = 22)
	@Lob
	private String extendsParameter;

}
