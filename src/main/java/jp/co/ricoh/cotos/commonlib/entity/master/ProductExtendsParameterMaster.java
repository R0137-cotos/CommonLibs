package jp.co.ricoh.cotos.commonlib.entity.master;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品拡張項目マスタ
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "product_extends_parameter_master")
public class ProductExtendsParameterMaster extends EntityBaseMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_extends_parameter_master_seq")
	@SequenceGenerator(name = "product_extends_parameter_master_seq", sequenceName = "product_extends_parameter_master_seq", allocationSize = 1)
	@Schema(description = "商品拡張項目マスタID", required = true, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 商品マスタ
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "product_master_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "商品マスタ", required = true)
	private ProductMaster productMaster;

	/**
	 * JSONスキーママスタ
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "json_schema_master_id", referencedColumnName = "id")
	@Schema(description = "JSONスキーママスタ", required = true)
	private JsonSchemaMaster jsonSchemaMaster;

	/**
	 * 登録区分
	 */
	@Size(max = 255)
	@Schema(description = "登録区分", required = true, allowableValues = "range[0,255]")
	private String registryType;

}