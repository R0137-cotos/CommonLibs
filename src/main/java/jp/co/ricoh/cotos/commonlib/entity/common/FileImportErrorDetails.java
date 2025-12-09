package jp.co.ricoh.cotos.commonlib.entity.common;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ファイル取込エラー詳細を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "file_import_error_details")
public class FileImportErrorDetails extends EntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "file_import_error_details_seq")
	@SequenceGenerator(name = "file_import_error_details_seq", sequenceName = "file_import_error_details_seq", allocationSize = 1)
	@Schema(description = "ファイル取込エラー詳細ID(作成時不要)", required = true, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * ファイル取込管理管理ID
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "file_import_management_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "ファイル取込管理", requiredMode = Schema.RequiredMode.REQUIRED)
	private FileImportManagement fileImportManagement;

	/**
	 * 行番号
	 */
	@Min(0)
	@Schema(description = "行番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private Long lineNumber;

	/**
	 * メッセージ
	 */
	@Size(max = 255)
	@Schema(description = "メッセージ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String message;

}
