package jp.co.ricoh.cotos.commonlib.entity.master;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 配送仕分け用郵便番号マスタ
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "shipping_post_number_master")
public class ShippingPostNumberMaster extends EntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shipping_post_number_master_seq")
	@SequenceGenerator(name = "shipping_post_number_master_seq", sequenceName = "shipping_post_number_master_seq", allocationSize = 1)
	@ApiModelProperty(value = "配送仕分け用郵便番マスタID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 郵便番号
	 */
	@Size(max = 255)
	@NotNull
	@ApiModelProperty(value = "郵便番号", required = true, position = 3, allowableValues = "range[0,255]")
	private String postNumber;

	/**
	 * 地域名
	 */
	@Size(max = 1000)
	@ApiModelProperty(value = "地域名", required = false, position = 3, allowableValues = "range[0,1000]")
	private String areaName;

}
