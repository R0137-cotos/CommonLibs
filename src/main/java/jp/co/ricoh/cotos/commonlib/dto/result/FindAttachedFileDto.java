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
	@Schema(description = "連番", requiredMode = Schema.RequiredMode.REQUIRED)
	private int seq;

	/** 見積ID */
	@Schema(description = "見積ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Long estimationId;

	/** 契約ID */
	@Schema(description = "契約ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Long contractId;

	/** 手配業務ID */
	@Schema(description = "手配業務ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Long arrangementWorkId;

	/** 見積番号 */
	@Schema(description = "見積番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String estimationNumber;

	/** 見積種別 */
	@Schema(description = "見積種別", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private EstimationType estimationType;

	/** 見積ライフサイクルステータス */
	@Schema(description = "見積ライフサイクルステータス", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Estimation.LifecycleStatus estimationLifecycleStatus;

	/** 契約番号 */
	@Schema(description = "契約番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String contractNumber;

	/** 契約種別 */
	@Schema(description = "契約種別", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private ContractType contractType;

	/** 契約ライフサイクルステータス */
	@Schema(description = "契約ライフサイクルステータス", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Contract.LifecycleStatus contractLifecycleStatus;

	/** サービス開始日 */
	@Schema(description = "サービス開始日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date serviceTermStart;

	/** サービス終了日 */
	@Schema(description = "サービス終了日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date serviceTermEnd;

	/** 手配業務タイプ名 */
	@Schema(description = "手配業務タイプ名", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String arrangementWorkName;

	/** 添付先 */
	@Schema(description = "添付先", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String attachedDestination;

	/** ファイル種類 */
	@Schema(description = "ファイル種類", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String fileKind;

	/** ファイル種類名称 */
	@Schema(description = "ファイル種類名称", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String fileKindName;

	/** 添付必須フラグ */
	@Schema(description = "添付必須フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Integer attachedRequiredFlg;

	/** ファイル名 */
	@Schema(description = "ファイル名", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String fileName;

	/** 添付ファイルID */
	@Schema(description = "添付ファイルID", requiredMode = Schema.RequiredMode.REQUIRED)
	private Long attachedFileId;

	/** 添付者氏名 */
	@Schema(description = "添付者氏名", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String attachedEmpName;

	/** 添付者組織名 */
	@Schema(description = "添付者組織名", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String attachedOrgName;

	/** 添付日時 */
	@Schema(description = "添付日時", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date attachedAt;

	/** 各ドメイン添付ファイルID */
	@Schema(description = "各ドメイン添付ファイルID", requiredMode = Schema.RequiredMode.REQUIRED)
	private Long domainAttachedFileId;

	/** コメント */
	@Schema(description = "コメント", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String attachedComment;
}
