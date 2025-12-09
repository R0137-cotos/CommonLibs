package jp.co.ricoh.cotos.commonlib.entity.contract;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品（契約用）を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@EntityListeners(ProductContractListener.class)
@Data
@Table(name = "product_contract")
public class ProductContract extends EntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_contract_seq")
	@SequenceGenerator(name = "product_contract_seq", sequenceName = "product_contract_seq", allocationSize = 1)
	@Schema(description = "ID(作成時不要)", allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 商品マスタID
	 */
	@Min(0)
	@Column(nullable = false)
	@Schema(description = "商品マスタID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private long productMasterId;

	/**
	 * 商品名
	 */
	@Column(nullable = false)
	@Schema(description = "商品名(作成時不要)", allowableValues = "range[0,255]", readOnly = true)
	private String productContractName;

	/**
	 * 代表品種マスタID
	 */
	@Min(0)
	@Schema(description = "代表品種マスタID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private Long repItemMasterId;

	/**
	 * サービス識別番号
	 */
	@Column(nullable = false)
	@Schema(description = "サービス識別番号(作成時不要)", allowableValues = "range[0,255]", readOnly = true)
	private String serviceIdentNumber;

	/**
	 * 契約
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "contract_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "契約", requiredMode = Schema.RequiredMode.REQUIRED)
	private Contract contract;

	/**
	 * 拡張項目
	 */
	@Schema(description = "拡張項目", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Lob
	private String extendsParameter;
	
	/**
	 * 拡張項目繰返
	 */
	@Schema(description = "拡張項目繰返", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Lob
	private String extendsParameterIterance;

}
