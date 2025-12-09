package jp.co.ricoh.cotos.commonlib.entity.externallinkage;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 引継ぎ用契約添付ファイルを表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@EntityListeners(HandoverContractAttachedFileListener.class)
@Data
@Table(name = "handover_contract_attached_file")
public class HandoverContractAttachedFile extends EntityBase {

	/**
	 * 引継ぎ用契約添付ファイルID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "handover_contract_attached_file_seq")
	@SequenceGenerator(name = "handover_contract_attached_file_seq", sequenceName = "handover_contract_attached_file_seq", allocationSize = 1)
	@Schema(description = "引継ぎ用契約添付ファイル(作成時不要)", allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 契約ID
	 */
	@Min(0)
	@Schema(description = "契約ID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private long contractId;

	/**
	 * ファイル名
	 */
	@Size(max = 255)
	@Schema(description = "ファイル名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String fileName;

	/**
	 * ファイル種類
	 */
	@Size(max = 255)
	@Schema(description = "ファイル種類", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String fileKind;

	/**
	 * 添付ファイルID
	 */
	@Min(0)
	@Schema(description = "添付ファイルID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private Long attachedFileId;

	/**
	 * コメント
	 */
	@Size(max = 1000)
	@Schema(description = "コメント", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,1000]")
	private String attachedComment;

	/**
	 * 添付者MoM社員ID
	 */
	@Size(max = 255)
	@Schema(description = "添付者MoM社員ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String attachedEmpId;

	/**
	 * 添付者氏名
	 */
	@Size(max = 255)
	@Schema(description = "添付者氏名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String attachedEmpName;

	/**
	 * 添付者組織名
	 */
	@Size(max = 255)
	@Schema(description = "添付者組織名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String attachedOrgName;

	/**
	 * 添付日時
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "添付日時", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date attachedAt;

	/**
	 * 添付必須フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "添付必須フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer attachedRequiredFlg;

	/**
	 * 引継ぎ元契約ID
	 */
	@Min(0)
	@Schema(description = "引継ぎ元契約ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private Long handoverContractId;

	/**
	 * 引継ぎ反映フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "引継ぎ反映フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer handoverMappedFlg;

	/**
	 * 商品種類区分
	 */
	@Size(max = 255)
	@Schema(description = "商品種類区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String productClassDiv;

	/**
	 * カテゴリ
	 */
	@Size(max = 255)
	@Schema(description = "カテゴリ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String category;
}
