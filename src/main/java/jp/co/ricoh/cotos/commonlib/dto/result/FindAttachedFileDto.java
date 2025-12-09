package jp.co.ricoh.cotos.commonlib.dto.result;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "連番", required = true)
	private int seq;

	/** 見積ID */
	@Schema(description = "見積ID", required = false)
	private Long estimationId;

	/** 契約ID */
	@Schema(description = "契約ID", required = false)
	private Long contractId;

	/** 手配業務ID */
	@Schema(description = "手配業務ID", required = false)
	private Long arrangementWorkId;

	/** 見積番号 */
	@Schema(description = "見積番号", required = false)
	private String estimationNumber;

	/** 見積種別 */
	@Schema(description = "見積種別", required = false)
	private EstimationType estimationType;

	/** 見積ライフサイクルステータス */
	@Schema(description = "見積ライフサイクルステータス", required = false)
	private Estimation.LifecycleStatus estimationLifecycleStatus;

	/** 契約番号 */
	@Schema(description = "契約番号", required = false)
	private String contractNumber;

	/** 契約種別 */
	@Schema(description = "契約種別", required = false)
	private ContractType contractType;

	/** 契約ライフサイクルステータス */
	@Schema(description = "契約ライフサイクルステータス", required = false)
	private Contract.LifecycleStatus contractLifecycleStatus;

	/** サービス開始日 */
	@Schema(description = "サービス開始日", required = false)
	private Date serviceTermStart;

	/** サービス終了日 */
	@Schema(description = "サービス終了日", required = false)
	private Date serviceTermEnd;

	/** 手配業務タイプ名 */
	@Schema(description = "手配業務タイプ名", required = false)
	private String arrangementWorkName;

	/** 添付先 */
	@Schema(description = "添付先", required = false)
	private String attachedDestination;

	/** ファイル種類 */
	@Schema(description = "ファイル種類", required = false)
	private String fileKind;

	/** ファイル種類名称 */
	@Schema(description = "ファイル種類名称", required = false)
	private String fileKindName;

	/** 添付必須フラグ */
	@Schema(description = "添付必須フラグ", required = false)
	private Integer attachedRequiredFlg;

	/** ファイル名 */
	@Schema(description = "ファイル名", required = false)
	private String fileName;

	/** 添付ファイルID */
	@Schema(description = "添付ファイルID", required = true)
	private Long attachedFileId;

	/** 添付者氏名 */
	@Schema(description = "添付者氏名", required = false)
	private String attachedEmpName;

	/** 添付者組織名 */
	@Schema(description = "添付者組織名", required = false)
	private String attachedOrgName;

	/** 添付日時 */
	@Schema(description = "添付日時", required = false)
	private Date attachedAt;

	/** 各ドメイン添付ファイルID */
	@Schema(description = "各ドメイン添付ファイルID", required = true)
	private Long domainAttachedFileId;

	/** コメント */
	@Schema(description = "コメント", required = false)
	private String attachedComment;
}
