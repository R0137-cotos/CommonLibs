package jp.co.ricoh.cotos.commonlib.entity.common;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.EimLinkedStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 添付ファイル情報を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "attached_file")
@EntityListeners(AttachedFileListener .class)
public class AttachedFile extends EntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attached_file_seq")
	@SequenceGenerator(name = "attached_file_seq", sequenceName = "attached_file_seq", allocationSize = 1)
	@Schema(description = "添付ファイルID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private long id;

	/**
	 * 物理ファイル名
	 */
	@Column(nullable = false)
	@NotNull
	@Size(max = 255)
	@Schema(description = "物理ファイル名", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String filePhysicsName;

	/**
	 * ファイルサイズ
	 */
	@Column(nullable = false)
	@Schema(description = "ファイルサイズ", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private long fileSize;

	/**
	 * コンテンツタイプ
	 */
	@Column(nullable = false)
	@NotNull
	@Size(max = 255)
	@Schema(description = "コンテンツタイプ", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String contentType;

	/**
	 * サーバーパス
	 */
	@Column(nullable = false)
	@NotNull
	@Size(max = 1000)
	@Schema(description = "サーバーパス", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,1000]")
	private String savedPath;

	/**
	 * EIM連携済状態
	 */
	@Schema(description = "EIM連携済状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未連携(\"0\"), 連携済(\"1\"),対象外 (\"9\")")
	private EimLinkedStatus eimLinkedStatus;

	/**
	 * EIMファイルID
	 */
	@Schema(description = "EIMファイルID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String eimFileId;
}
