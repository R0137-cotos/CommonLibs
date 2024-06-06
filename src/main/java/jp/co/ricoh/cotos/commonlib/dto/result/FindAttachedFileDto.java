package jp.co.ricoh.cotos.commonlib.dto.result;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.ContractType;
import jp.co.ricoh.cotos.commonlib.entity.estimation.Estimation;
import jp.co.ricoh.cotos.commonlib.entity.estimation.Estimation.EstimationType;
import lombok.Data;

/**
 * 添付ファイル一覧取得DTO
 */
@Entity
@Data
public class FindAttachedFileDto {

	/** 連番 */
	@Id
	@ApiModelProperty(value = "連番", required = false, position = 1)
	private int seq;

	/** 見積ID */
	@ApiModelProperty(value = "見積ID", required = false, position = 2)
	private Long estimationId;

	/** 契約ID */
	@ApiModelProperty(value = "契約ID", required = false, position = 3)
	private Long contractId;

	/** 手配業務ID */
	@ApiModelProperty(value = "手配業務ID", required = false, position = 4)
	private Long arrangementWorkId;

	/** 見積番号 */
	@ApiModelProperty(value = "見積番号", required = false, position = 5)
	private String estimationNumber;

	/** 見積種別 */
	@ApiModelProperty(value = "見積種別", required = false, position = 6)
	private EstimationType estimationType;

	/** 見積ライフサイクルステータス */
	@ApiModelProperty(value = "見積ライフサイクルステータス", required = false, position = 7)
	private Estimation.LifecycleStatus estimationLifecycleStatus;

	/** 契約番号 */
	@ApiModelProperty(value = "契約番号", required = false, position = 8)
	private String contractNumber;

	/** 契約種別 */
	@ApiModelProperty(value = "契約種別", required = false, position = 9)
	private ContractType contractType;

	/** 契約ライフサイクルステータス */
	@ApiModelProperty(value = "契約ライフサイクルステータス", required = false, position = 10)
	private Contract.LifecycleStatus contractLifecycleStatus;

	/** サービス開始日 */
	@ApiModelProperty(value = "サービス開始日", required = false, position = 11)
	private Date serviceTermStart;

	/** サービス終了日 */
	@ApiModelProperty(value = "サービス終了日", required = false, position = 12)
	private Date serviceTermEnd;

	/** 手配業務タイプ名 */
	@ApiModelProperty(value = "手配業務タイプ名", required = false, position = 13)
	private String arrangementWorkName;

	/** 添付先 */
	@ApiModelProperty(value = "添付先", required = false, position = 14)
	private String attachedDestination;

	/** ファイル種類 */
	@ApiModelProperty(value = "ファイル種類", required = false, position = 15)
	private String fileKind;

	/** 添付必須フラグ */
	@ApiModelProperty(value = "添付必須フラグ", required = false, position = 16)
	private Integer attachedRequiredFlg;

	/** ファイル名 */
	@ApiModelProperty(value = "ファイル名", required = false, position = 17)
	private String fileName;

	/** 添付ファイルID */
	@ApiModelProperty(value = "添付ファイルID", required = false, position = 18)
	private Long attachedFileId;

	/** 添付者氏名 */
	@ApiModelProperty(value = "添付者氏名", required = false, position = 19)
	private String attachedEmpName;

	/** 添付者組織名 */
	@ApiModelProperty(value = "添付者組織名", required = false, position = 20)
	private String attachedOrgName;

	/** 添付日時 */
	@ApiModelProperty(value = "添付日時", required = false, position = 21)
	private Date attachedAt;

	/** 各ドメインの添付ファイルID */
	@ApiModelProperty(value = "各ドメインの添付ファイルID", required = false, position = 22)
	private Long domainAttachedFileId;

	/** コメント */
	@ApiModelProperty(value = "コメント", required = false, position = 23)
	private String attachedComment;
}
