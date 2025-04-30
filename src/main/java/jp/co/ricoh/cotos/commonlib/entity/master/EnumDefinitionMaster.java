package jp.co.ricoh.cotos.commonlib.entity.master;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ENUM定義マスタを表すEntity
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "enum_definition_master")
public class EnumDefinitionMaster extends EntityBaseMaster {

	/**
	 * ENUM定義マスタID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "enum_definition_master_seq")
	@SequenceGenerator(name = "enum_definition_master_seq", sequenceName = "enum_definition_master_seq", allocationSize = 1)
	@ApiModelProperty(value = "ENUM定義マスタID", required = true, position = 1, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * エンティティ名
	 */
	@ApiModelProperty(value = "エンティティ名", required = false, position = 2, allowableValues = "range[0,255]")
	private String className;

	/**
	 * フィールド名
	 */
	@ApiModelProperty(value = "フィールド名", required = false, position = 3, allowableValues = "range[0,255]")
	private String fieldName;

	/**
	 * テーブル名
	 */
	@ApiModelProperty(value = "テーブル名", required = false, position = 4, allowableValues = "range[0,255]")
	private String tableName;

	/**
	 * カラム名
	 */
	@ApiModelProperty(value = "カラム名", required = false, position = 5, allowableValues = "range[0,255]")
	private String columnName;

	/**
	 * enum定義名(論理名)
	 */
	@ApiModelProperty(value = "enum定義名(論理名)", required = false, position = 6, allowableValues = "range[0,255]")
	private String enumName;

	/**
	 * enum値名
	 */
	@ApiModelProperty(value = "enum値名", required = false, position = 7, allowableValues = "range[0,255]")
	private String enumValueName;

	/**
	 * enumコード値
	 */
	@ApiModelProperty(value = "enumコード名", required = false, position = 8, allowableValues = "range[0,255]")
	private String enumValueCode;

	/**
	 * enum定義名(物理名)
	 */
	@ApiModelProperty(value = "enum定義名(物理名)", required = false, position = 9, allowableValues = "range[0,255]")
	private String enumClassName;
}
