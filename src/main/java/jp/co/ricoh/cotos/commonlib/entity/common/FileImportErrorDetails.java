package jp.co.ricoh.cotos.commonlib.entity.common;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(value = "ファイル取込エラー詳細ID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * ファイル取込管理管理ID
	 */
	@Min(0)
	@ApiModelProperty(value = "ファイル取込管理管理ID", required = true, position = 2, allowableValues = "range[0,9223372036854775807]")
	private long fileImportManagementId;

	/**
	 * 行番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "行番号", required = false, position = 3, allowableValues = "range[0,255]")
	private String lineNumber;

	/**
	 * メッセージ
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "メッセージ", required = false, position = 4, allowableValues = "range[0,255]")
	private String message;

}
