package jp.co.ricoh.cotos.commonlib.dto.result;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import jp.co.ricoh.cotos.commonlib.entity.common.FileImportManagement.BatchExecutionStatus;
import lombok.Data;

@Data
@Entity
public class FileImportManagementDto {

	/**
	 * ファイル取込管理ID
	 */
	@Id
	private long id;

	/**
	 * ファイル種別管理マスタID
	 */
	private long fileKindManagementMasterId;

	/**
	 * ファイル名
	 */
	private String fileName;

	/**
	 * 添付ファイルID
	 */
	@Column(name = "attachment_id")
	private Long attachmentFile;

	/**
	 * エラー添付ファイルID
	 */
	@Column(name = "error_attachment_id")
	private Long errorAttachmentFile;

	/**
	 * 取込実施者
	 */
	private String importUser;

	/**
	 * 取込日
	 */
	@Temporal(TemporalType.DATE)
	private Date importDate;

	/**
	 * バッチ実行ステータス
	 */
	private BatchExecutionStatus batchExecutionStatus;

	/**
	 * 取込開始日時
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date importStartDate;

	/**
	 * 取込終了日時
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date importEndDate;

	/**
	 * 関連ファイル取込管理ID
	 */
	@Column(name = "related_file_import_management_id")
	private Long relatedFileImportManagementId;
}
