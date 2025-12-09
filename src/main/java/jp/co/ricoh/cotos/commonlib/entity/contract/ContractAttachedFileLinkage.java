package jp.co.ricoh.cotos.commonlib.entity.contract;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.FileLinkageStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 契約添付ファイル連携先
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "contract_attached_file_linkage")
public class ContractAttachedFileLinkage extends EntityBase {

	/**
	 * 契約添付ファイル連携先ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contract_attached_file_linkage_seq")
	@SequenceGenerator(name = "contract_attached_file_linkage_seq", sequenceName = "contract_attached_file_linkage_seq", allocationSize = 1)
	@Schema(description = "契約業務添付ファイル連携先ID", required = true, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 契約添付ファイルID
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "contract_attached_file_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "契約添付ファイルID", required = true)
	private ContractAttachedFile contractAttachedFile;

	/**
	 * ファイル連携先ID
	 */
	@Schema(description = "ファイル連携先ID", required = true)
	private long attachedFileLinkageId;

	/**
	 * ファイル連携先
	 */
	@NotNull
	@Size(max = 255)
	@Schema(description = "ファイル連携先", required = true, allowableValues = "range[0,255]")
	private String attachedFileLinkageName;

	/**
	 * 連携ステータス
	 */
	@NotNull
	@Schema(description = "連携ステータス", required = true, allowableValues = "連携対象外(\"0\"), 未連携(\"1\"), 連携済(\"2\"), 送付済(\"3\")", example = "0")
	private FileLinkageStatus linkageStatus;
	
	/**
	 * 解約フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "解約フラグ", required = false, allowableValues = "range[0,9]")
	private Integer disengagementFlg;
}
