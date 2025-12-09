package jp.co.ricoh.cotos.commonlib.entity.common;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OrderBy;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.contract.PriceRewriteExclusionContract;
import jp.co.ricoh.cotos.commonlib.entity.contract.PriceRewriteItemInfo;
import jp.co.ricoh.cotos.commonlib.entity.estimation.PriceRewriteEstimationDestructionItem;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ファイル取込管理を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "file_import_management")
public class FileImportManagement extends EntityBase {

	@Description(value = "バッチ実行ステータス")
	public enum BatchExecutionStatus {

		取込待ち("1"), 取込中("2"), エラー("3"), 正常終了("4"), 取込後処理中("5"), 取込エラー("6"), その他("99");

		private final String text;

		private BatchExecutionStatus(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static BatchExecutionStatus fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "file_import_management_seq")
	@SequenceGenerator(name = "file_import_management_seq", sequenceName = "file_import_management_seq", allocationSize = 1)
	@Schema(description = "ファイル取込管理ID(作成時不要)", required = true, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * ファイル種別管理マスタID
	 */
	@Column(nullable = false)
	@Min(0)
	@Schema(description = "ファイル種別管理マスタID", required = true, allowableValues = "range[0,9223372036854775807]")
	private long fileKindManagementMasterId;

	/**
	 * ファイル名
	 */
	@Size(max = 255)
	@Schema(description = "ファイル名", required = false, allowableValues = "range[0,255]")
	private String fileName;

	/**
	 * 添付ファイルID
	 */
	@NotNull
	@OneToOne(optional = false)
	@JoinColumn(name = "attachment_id", referencedColumnName = "id")
	@Schema(description = "添付ファイル", required = true)
	private AttachedFile attachmentFile;

	/**
	 * エラー添付ファイルID
	 */
	@OneToOne(optional = true)
	@JoinColumn(name = "error_attachment_id", referencedColumnName = "id")
	@Schema(description = "エラー添付ファイル", required = false)
	private AttachedFile errorAttachmentFile;

	/**
	 * 取込実施者
	 */
	@Size(max = 255)
	@Schema(description = "取込実施者", required = false, allowableValues = "range[0,255]")
	private String importUser;

	/**
	 * 取込日
	 */
	@Schema(description = "取込日", required = false)
	@Temporal(TemporalType.DATE)
	private Date importDate;

	/**
	 * バッチ実行ステータス
	 */
	@Column(nullable = false)
	@Schema(description = "バッチ実行ステータス", required = true, allowableValues = "取込待ち(\"1\"), 取込中(\"2\"), エラー(\"3\"), 正常終了(\"4\"), 取込後処理中(\"5\"), 取込エラー(\"6\"), その他(\"99\")", readOnly = false)
	private BatchExecutionStatus batchExecutionStatus;

	/**
	 * 取込開始日時
	 */
	@Schema(description = "取込開始日時", required = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date importStartDate;

	/**
	 * 取込終了日時
	 */
	@Schema(description = "取込終了日時", required = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date importEndDate;

	/**
	 * ファイル取込エラー詳細
	 */
	@OneToMany(mappedBy = "fileImportManagement")
	@OrderBy("lineNumber ASC")
	@Schema(description = "ファイル取込エラー詳細", required = false)
	private List<FileImportErrorDetails> fileImportErrorDetailsList;

	/**
	 * 価格書換見積破棄対象品種
	 */
	@OneToMany(mappedBy = "fileImportManagement")
	@Schema(description = "価格書換見積破棄対象品種", required = false)
	private List<PriceRewriteEstimationDestructionItem> priceRewriteEstimationDestructionItemList;

	/**
	 * 価格書換除外契約
	 */
	@OneToMany(mappedBy = "fileImportManagement")
	@Schema(description = "価格書換除外契約", required = false)
	private List<PriceRewriteExclusionContract> priceRewriteExclusionContractList;

	/**
	 * 価格書換品種情報
	 */
	@OneToMany(mappedBy = "fileImportManagement")
	@Schema(description = "価格書換品種情報", required = false)
	private List<PriceRewriteItemInfo> priceRewriteItemInfoList;

	/**
	 * 関連ファイル取込管理ID
	 */
	@ManyToOne
	@JoinColumn(name = "related_file_import_management_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "関連ファイル取込管理", required = false, allowableValues = "range[0,9223372036854775807]")
	private FileImportManagement relatedFileImportManagement;

}
