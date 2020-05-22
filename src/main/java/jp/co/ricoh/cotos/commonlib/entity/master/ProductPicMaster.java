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
 * 商品担当者マスタ
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "product_pic_master")
public class ProductPicMaster extends EntityBaseMaster {

	/**
	 * 商品担当者マスタID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_pic_master_seq")
	@SequenceGenerator(name = "product_pic_master_seq", sequenceName = "product_pic_master_seq", allocationSize = 1)
	@ApiModelProperty(value = "商品担当者マスタID", required = true, position = 1, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 商品マスタID
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "product_master_id", referencedColumnName = "id")
	@JsonIgnore
	@ApiModelProperty(value = "商品マスタID", required = true, position = 2)
	private ProductMaster productMaster;

	/**
	 * MoM社員ID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "MoM社員ID", required = true, position = 3, allowableValues = "range[0,255]")
	private String momEmployeeId;

}
