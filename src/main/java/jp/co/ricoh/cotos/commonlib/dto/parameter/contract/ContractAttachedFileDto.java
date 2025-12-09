package jp.co.ricoh.cotos.commonlib.dto.parameter.contract;

import java.util.Date;
import java.util.List;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.AttachedFileDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.DtoBase;
import jp.co.ricoh.cotos.commonlib.entity.master.AttachedFileProductGrpCheckMaster.CheckTimingDiv;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class ContractAttachedFileDto extends DtoBase {

	/**
	 * ファイル名
	 */
	@NotNull
	@Size(max = 255)
	@Schema(description = "ファイル名", required = true, allowableValues = "range[0,255]")
	private String fileName;

	/**
	 * ファイル種類
	 */
	@Size(max = 255)
	@Schema(description = "ファイル種類", required = false, allowableValues = "range[0,255]")
	private String fileKind;

	/**
	 * 添付ファイル
	 */
	@Valid
	@NotNull
	@OneToOne(optional = false)
	@JoinColumn(name = "attached_file_id", referencedColumnName = "id")
	@Schema(description = "添付ファイル", required = true)
	private AttachedFileDto attachedFile;

	/**
	 * コメント
	 */
	@Size(max = 1000)
	@Schema(description = "コメント", required = false, allowableValues = "range[0,1000]")
	private String attachedComment;

	/**
	 * 添付者MoM社員ID
	 */
	@NotNull
	@Size(max = 255)
	@Schema(description = "添付者MoM社員ID", required = true, allowableValues = "range[0,255]")
	private String attachedEmpId;

	/**
	 * 添付者氏名
	 */
	@NotNull
	@Size(max = 255)
	@Schema(description = "添付者氏名", required = true, allowableValues = "range[0,255]")
	private String attachedEmpName;

	/**
	 * 添付者組織名
	 */
	@Size(max = 255)
	@Schema(description = "添付者組織名", required = false, allowableValues = "range[0,255]")
	private String attachedOrgName;

	/**
	 * 添付日時
	 */
	@Schema(description = "添付日時", required = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date attachedAt;

	/**
	 * 契約添付ファイル連携先
	 */
	@Valid
	@OneToMany(mappedBy = "contractAttachedFile")
	@Schema(description = "契約添付ファイル連携先", required = false)
	private List<ContractAttachedFileLinkageDto> contractAttachedFileLinkageList;

	/**
	 * 添付必須フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "添付必須フラグ", required = false, allowableValues = "range[0,9]")
	private Integer attachedRequiredFlg;

	/**
	 * チェックタイミング区分
	 */
	@Schema(description = "チェックタイミング区分", required = false, allowableValues = "常時(\"0\"), 承認のみ(\"1\")")
	private CheckTimingDiv checkTimingDiv;
}
