package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * CSVファイル設定マスタ
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "csv_file_setting_master")
public class CsvFileSettingMaster extends EntityBaseMaster {

	/**
	 * CSVファイル設定マスタID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "csv_file_setting_master_seq")
	@SequenceGenerator(name = "csv_file_setting_master_seq", sequenceName = "csv_file_setting_master_seq", allocationSize = 1)
	@Schema(description = "CSVファイル設定マスタID(作成時不要)", allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * ヘッダーフラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "ヘッダーフラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer csvHeaderFlg;

	/**
	 * セパレーター文字
	 */
	@Size(max = 255)
	@Schema(description = "セパレーター文字名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String csvSeparator;

	/**
	 * 文字コード
	 */
	@Size(max = 255)
	@Schema(description = "文字コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String csvCharset;

	/**
	 * 改行コード
	 */
	@Size(max = 255)
	@Schema(description = "改行コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String csvLineSeparator;

	/**
	 * 囲み文字有無フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "囲み文字有無フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer csvQuote;

	/**
	 * 囲み文字有無フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "囲み文字有無フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer csvWithoutQuoteChar;

	/**
	 * NULL値文字
	 */
	@Size(max = 255)
	@Schema(description = "NULL値文字", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String csvNullValueString;

	/**
	 * BOM設定フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "BOM設定フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer bomSettingFlg;

	/**
	 * ファイル種別管理マスタ
	 */
	@OneToMany(mappedBy = "csvFileSettingMaster")
	@JsonIgnore
	@Schema(description = "ファイル種別管理マスタ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<FileKindManagementMaster> fileKindManagementMasterList;
}
