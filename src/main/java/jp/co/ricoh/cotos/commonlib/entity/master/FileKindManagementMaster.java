package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ファイル種別管理マスタ
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "file_kind_management_master")
public class FileKindManagementMaster extends EntityBaseMaster {

	public enum InportExportType {

		取込("1"), 出力("2");

		private final String text;

		private InportExportType(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static InportExportType fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	public enum ErrorProcessingType {

		全件("1"), 個別("2");

		private final String text;

		private ErrorProcessingType(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static ErrorProcessingType fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * ファイル種別管理ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "file_kind_management_master_seq")
	@SequenceGenerator(name = "file_kind_management_master_seq", sequenceName = "file_kind_management_master_seq", allocationSize = 1)
	@ApiModelProperty(value = "ファイル種別管理ID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * ファイル操作関連商品ID
	 */
	@NotNull
	@Column(nullable = false)
	@Min(0)
	@ApiModelProperty(value = "ファイル操作関連商品ID", required = true, position = 2, allowableValues = "range[0,9223372036854775807]")
	private Long fileOperationRelationProductId;

	/**
	 * ファイル種別名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "ファイル種別名", required = false, position = 3, allowableValues = "range[0,255]")
	private String fileKindName;

	/**
	 * 入出力区分
	 */
	@NotNull
	@Column(nullable = false)
	@ApiModelProperty(value = "入出力区分", required = true, allowableValues = "取込(\"1\"), 出力(\"2\")", position = 4)
	private InportExportType inportExportType;

	/**
	 * 出力条件区分
	 */
	@Min(0)
	@ApiModelProperty(value = "出力条件区分", required = false, position = 5, allowableValues = "range[0,9223372036854775807]")
	private Long exportConditionsType;

	/**
	 * エラー処理区分
	 */
	@ApiModelProperty(value = "エラー処理区分", required = false, allowableValues = "全件(\"1\"), 個別(\"2\")", position = 6)
	private ErrorProcessingType errorProcessingType;

	/**
	 * 外部機能
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "外部機能", required = false, position = 7, allowableValues = "range[0,255]")
	private String externalFunction;

}
