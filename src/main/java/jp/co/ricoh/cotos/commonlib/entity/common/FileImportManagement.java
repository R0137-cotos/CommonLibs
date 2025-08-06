package jp.co.ricoh.cotos.commonlib.entity.common;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(value = "ファイル取込管理ID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * ファイル種別管理マスタID
	 */
	@Column(nullable = false)
	@Min(0)
	@ApiModelProperty(value = "ファイル種別管理マスタID", required = true, position = 2, allowableValues = "range[0,9223372036854775807]")
	private long fileKindManagementMasterId;

	/**
	 * ファイル名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "ファイル名", required = false, position = 3, allowableValues = "range[0,255]")
	private String fileName;

	/**
	 * 添付ファイルID
	 */
	@NotNull
	@OneToOne(optional = false)
	@JoinColumn(name = "attachment_id", referencedColumnName = "id")
	@ApiModelProperty(value = "添付ファイル", required = true, position = 4)
	private AttachedFile attachmentFile;

	/**
	 * エラー添付ファイルID
	 */
	@OneToOne(optional = true)
	@JoinColumn(name = "error_attachment_id", referencedColumnName = "id")
	@ApiModelProperty(value = "エラー添付ファイル", required = false, position = 5)
	private AttachedFile errorAttachmentFile;

	/**
	 * 取込実施者
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "取込実施者", required = false, position = 6, allowableValues = "range[0,255]")
	private String importUser;

	/**
	 * 取込日
	 */
	@ApiModelProperty(value = "取込日", required = false, position = 7)
	@Temporal(TemporalType.DATE)
	private Date importDate;

	/**
	 * バッチ実行ステータス
	 */
	@Column(nullable = false)
	@ApiModelProperty(value = "バッチ実行ステータス", required = true, allowableValues = "取込待ち(\"1\"), 取込中(\"2\"), エラー(\"3\"), 正常終了(\"4\"), 取込後処理中(\"5\"), 取込エラー(\"6\"), その他(\"99\")", //
			position = 8, readOnly = false)
	private BatchExecutionStatus batchExecutionStatus;

	/**
	 * 取込開始日時
	 */
	@ApiModelProperty(value = "取込開始日時", required = false, position = 9)
	@Temporal(TemporalType.TIMESTAMP)
	private Date importStartDate;

	/**
	 * 取込終了日時
	 */
	@ApiModelProperty(value = "取込終了日時", required = false, position = 10)
	@Temporal(TemporalType.TIMESTAMP)
	private Date importEndDate;

	/**
	 * ファイル取込エラー詳細
	 */
	@OneToMany(mappedBy = "fileImportManagement")
	@OrderBy("lineNumber ASC")
	@ApiModelProperty(value = "ファイル取込エラー詳細", required = false, position = 11)
	private List<FileImportErrorDetails> fileImportErrorDetailsList;

	/**
	 * 価格書換見積破棄対象品種
	 */
	@OneToOne(mappedBy = "fileImportManagement")
	@ApiModelProperty(value = "価格書換見積破棄対象品種", required = false, position = 12)
	private PriceRewriteEstimationDestructionItem priceRewriteEstimationDestructionItem;

	/**
	 * 価格書換除外契約
	 */
	@OneToOne(mappedBy = "fileImportManagement")
	@ApiModelProperty(value = "価格書換除外契約", required = false, position = 13)
	private PriceRewriteExclusionContract priceRewriteExclusionContract;

	/**
	 * 価格書換品種情報
	 */
	@OneToOne(mappedBy = "fileImportManagement")
	@ApiModelProperty(value = "価格書換品種情報", required = false, position = 14)
	private PriceRewriteItemInfo priceRewriteItemInfo;

	/**
	 * 関連ファイル取込管理ID
	 */
	@ManyToOne
	@JoinColumn(name = "related_file_import_management_id", referencedColumnName = "id")
	@JsonIgnore
	@ApiModelProperty(value = "関連ファイル取込管理ID", required = false, position = 15, allowableValues = "range[0,9223372036854775807]")
	private FileImportManagement relatedFileImportManagementId;

}
