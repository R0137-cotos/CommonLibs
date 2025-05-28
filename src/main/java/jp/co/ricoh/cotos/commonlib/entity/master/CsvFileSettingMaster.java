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

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(value = "CSVファイル設定マスタID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * ヘッダーフラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "ヘッダーフラグ", required = false, position = 2, allowableValues = "range[0,9]")
	private Integer csvHeaderFlg;

	/**
	 * セパレーター文字
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "セパレーター文字名", required = false, position = 3, allowableValues = "range[0,255]")
	private String csvSeparator;

	/**
	 * 文字コード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "文字コード", required = false, position = 4, allowableValues = "range[0,255]")
	private String csvCharset;

	/**
	 * 改行コード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "改行コード", required = false, position = 5, allowableValues = "range[0,255]")
	private String csvLineSeparator;

	/**
	 * 囲み文字有無フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "囲み文字有無フラグ", required = false, position = 6, allowableValues = "range[0,9]")
	private Integer csvQuote;

	/**
	 * 囲み文字有無フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "囲み文字有無フラグ", required = false, position = 7, allowableValues = "range[0,9]")
	private Integer csvWithoutQuoteChar;

	/**
	 * NULL値文字
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "NULL値文字", required = false, position = 8, allowableValues = "range[0,255]")
	private String csvNullValueString;

	/**
	 * BOM設定フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "BOM設定フラグ", required = false, position = 9, allowableValues = "range[0,9]")
	private Integer bomSettingFlg;

	/**
	 * ファイル種別管理マスタ
	 */
	@OneToMany(mappedBy = "csvFileSettingMaster")
	@JsonIgnore
	@ApiModelProperty(value = "ファイル種別管理マスタ", required = false, position = 9)
	private List<FileKindManagementMaster> fileKindManagementMasterList;
}
