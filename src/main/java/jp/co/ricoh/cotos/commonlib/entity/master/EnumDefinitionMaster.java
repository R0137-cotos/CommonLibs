package jp.co.ricoh.cotos.commonlib.entity.master;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "ENUM定義マスタID", required = true, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * エンティティ名
	 */
	@Schema(description = "エンティティ名", required = false, allowableValues = "range[0,255]")
	private String className;

	/**
	 * フィールド名
	 */
	@Schema(description = "フィールド名", required = false, allowableValues = "range[0,255]")
	private String fieldName;

	/**
	 * テーブル名
	 */
	@Schema(description = "テーブル名", required = false, allowableValues = "range[0,255]")
	private String tableName;

	/**
	 * カラム名
	 */
	@Schema(description = "カラム名", required = false, allowableValues = "range[0,255]")
	private String columnName;

	/**
	 * enum定義名(論理名)
	 */
	@Schema(description = "enum定義名(論理名)", required = false, allowableValues = "range[0,255]")
	private String enumName;

	/**
	 * enum値名
	 */
	@Schema(description = "enum値名", required = false, allowableValues = "range[0,255]")
	private String enumValueName;

	/**
	 * enumコード値
	 */
	@Schema(description = "enumコード名", required = false, allowableValues = "range[0,255]")
	private String enumValueCode;

	/**
	 * enum定義名(物理名)
	 */
	@Schema(description = "enum定義名(物理名)", required = false, allowableValues = "range[0,255]")
	private String enumClassName;
}
