package jp.co.ricoh.cotos.commonlib.entity.master;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(value = "ベンダー商品マスタID", required = true, position = 1, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * COTOS商品マスタID
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "product_master_id", referencedColumnName = "id")
	@JsonIgnore
	@ApiModelProperty(value = "COTOS商品マスタID", required = true, position = 2)
	private ProductMaster productMaster;

	/**
	 * ベンダーマスタID
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "vendor_master_id", referencedColumnName = "id")
	@JsonIgnore
	@ApiModelProperty(value = "ベンダーマスタID", required = true, position = 3)
	private VendorMaster vendorMaster;

	/**
	 * ベンダ管理番号名称
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "ベンダ管理番号名称", required = false, position = 4, allowableValues = "range[0,255]")
	private String vendorManageNumberName;
}
