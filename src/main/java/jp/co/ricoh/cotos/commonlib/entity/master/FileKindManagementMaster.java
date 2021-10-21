package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.context.annotation.Description;

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

	@Description(value = "入出力区分")
	public enum ImportExportType {

		取込("1"), 出力("2");

		private final String text;

		private ImportExportType(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static ImportExportType fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst()
					.orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "エラー処理区分")
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
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst()
					.orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * ファイル種別管理マスタID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "file_kind_management_master_seq")
	@SequenceGenerator(name = "file_kind_management_master_seq", sequenceName = "file_kind_management_master_seq", allocationSize = 1)
	@ApiModelProperty(value = "ファイル種別管理マスタID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * ファイル操作関連商品マスタ
	 */
	@ManyToOne
	@JoinColumn(name = "file_operation_relation_product_master_id", referencedColumnName = "id")
	@ApiModelProperty(value = "ファイル操作関連商品マスタ", required = false, position = 2)
	private FileOperationRelationProductMaster fileOperationRelationProductMaster;

	/**
	 * ファイル種別名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "ファイル種別名", required = false, position = 3, allowableValues = "range[0,255]")
	private String fileKindName;

	/**
	 * 入出力区分
	 */
	@ApiModelProperty(value = "入出力区分", required = false, allowableValues = "取込(\"1\"), 出力(\"2\")", position = 4)
	private ImportExportType importExportType;

	/**
	 * 出力条件区分
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "出力条件区分", required = false, position = 5, allowableValues = "range[0,255]")
	private String exportConditionsType;

	/**
	 * エラー処理区分
	 */
	@ApiModelProperty(value = "エラー処理区分", required = false, allowableValues = "全件(\"1\"), 個別(\"2\")", position = 6)
	private ErrorProcessingType errorProcessingType;

	/**
	 * ファイル種別タイプ区分
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "ファイル種別タイプ区分", required = false, position = 8, allowableValues = "range[0,255]")
	private String fileKindTypeDiv;

	/**
	 * 出力ファイル名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "出力ファイル名", required = false, position = 9, allowableValues = "range[0,255]")
	private String outputFileName;

	/**
	 * CSVファイル設定マスタ
	 */
	@ManyToOne
	@JoinColumn(name = "csv_file_setting_master_id", referencedColumnName = "id")
	@ApiModelProperty(value = "CSVファイル設定マスタ", required = false, position = 10)
	private CsvFileSettingMaster csvFileSettingMaster;
}
