package jp.co.ricoh.cotos.commonlib.entity.master;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ベンダー商品マスタ
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "vendor_product_master")
public class VendorProductMaster extends EntityBaseMaster {

	/**
	 * ベンダー商品マスタID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vendor_product_master_seq")
	@SequenceGenerator(name = "vendor_product_master_seq", sequenceName = "vendor_product_master_seq", allocationSize = 1)
	@Schema(description = "ベンダー商品マスタID", required = true, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * COTOS商品マスタ
	 */
	@OneToOne(optional = false)
	@JoinColumn(name = "product_master_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "COTOS商品マスタ", required = true)
	private ProductMaster productMaster;

	/**
	 * ベンダーマスタ
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "vendor_master_id", referencedColumnName = "id")
	@Schema(description = "ベンダーマスタ", required = true)
	private VendorMaster vendorMaster;

	/**
	 * ベンダー管理番号名称
	 */
	@Size(max = 255)
	@Schema(description = "ベンダー管理番号名称", required = false, allowableValues = "range[0,255]")
	private String vendorManageNumberName;

	/**
	 * サービス提供会社名
	 */
	@Size(max = 255)
	@Schema(description = "サービス提供会社名", required = false, allowableValues = "range[0,255]")
	private String serviceProviderCompanyName;
}
