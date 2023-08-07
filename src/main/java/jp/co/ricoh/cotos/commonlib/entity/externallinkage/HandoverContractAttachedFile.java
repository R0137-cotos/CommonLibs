package jp.co.ricoh.cotos.commonlib.entity.externallinkage;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(value = "引継ぎ用契約添付ファイル(作成時不要)", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 契約ID
	 */
	@Min(0)
	@ApiModelProperty(value = "契約ID", required = true, position = 2, allowableValues = "range[0,9223372036854775807]")
	private long contractId;

	/**
	 * ファイル名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "ファイル名", required = false, position = 3, allowableValues = "range[0,255]")
	private String fileName;

	/**
	 * ファイル種類
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "ファイル種類", required = false, position = 4, allowableValues = "range[0,255]")
	private String fileKind;

	/**
	 * 添付ファイルID
	 */
	@Min(0)
	@ApiModelProperty(value = "添付ファイルID", required = false, position = 5, allowableValues = "range[0,9223372036854775807]")
	private Long attachedFileId;

	/**
	 * コメント
	 */
	@Size(max = 1000)
	@ApiModelProperty(value = "コメント", required = false, position = 6, allowableValues = "range[0,1000]")
	private String attachedComment;

	/**
	 * 添付者MoM社員ID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "添付者MoM社員ID", required = false, position = 7, allowableValues = "range[0,255]")
	private String attachedEmpId;

	/**
	 * 添付者氏名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "添付者氏名", required = false, position = 8, allowableValues = "range[0,255]")
	private String attachedEmpName;

	/**
	 * 添付者組織名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "添付者組織名", required = false, position = 9, allowableValues = "range[0,255]")
	private String attachedOrgName;

	/**
	 * 添付日時
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@ApiModelProperty(value = "添付日時", required = false, position = 10)
	private Date attachedAt;

	/**
	 * 添付必須フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "添付必須フラグ", required = false, position = 11, allowableValues = "range[0,9]")
	private Integer attachedRequiredFlg;

	/**
	 * 引継ぎ元契約ID
	 */
	@Min(0)
	@ApiModelProperty(value = "引継ぎ元契約ID", required = false, position = 12, allowableValues = "range[0,9223372036854775807]")
	private Long handoverContractId;

	/**
	 * 引継ぎ反映フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "引継ぎ反映フラグ", required = false, position = 13, allowableValues = "range[0,9]")
	private Integer handoverMappedFlg;

	/**
	 * 商品種類区分
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "商品種類区分", required = false, position = 14, allowableValues = "range[0,255]")
	private String productClassDiv;

	/**
	 * カテゴリ
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "カテゴリ", required = false, position = 15, allowableValues = "range[0,255]")
	private String category;
}
