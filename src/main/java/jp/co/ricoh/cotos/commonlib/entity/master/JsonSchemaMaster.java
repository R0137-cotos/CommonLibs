package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * JSONスキーママスタ
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "json_schema_master")
public class JsonSchemaMaster extends EntityBaseMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "json_schema_master_seq")
	@SequenceGenerator(name = "json_schema_master_seq", sequenceName = "json_schema_master_seq", allocationSize = 1)
	@Schema(description = "JSONスキーママスタID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 商品拡張項目マスタ
	 */
	@OneToMany(mappedBy = "jsonSchemaMaster")
	@JsonIgnore
	@Schema(description = "商品拡張項目マスタ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<ProductExtendsParameterMaster> productExtendsParameterMasterList;

	/**
	 * JSONスキーマ
	 */
	@Column(nullable = false)
	@Schema(description = "JSONスキーマ", requiredMode = Schema.RequiredMode.REQUIRED)
	@Lob
	private String jsonSchema;

	/**
	 * JSONスキーマ初期値
	 */
	@Column(nullable = true)
	@Schema(description = "JSONスキーマ初期値", requiredMode = Schema.RequiredMode.REQUIRED)
	@Lob
	private String jsonSchemaInitial;

}
