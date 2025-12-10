package jp.co.ricoh.cotos.commonlib.entity.master;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "配送仕分け用郵便番マスタID(作成時不要)", required = true, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 郵便番号
	 */
	@Size(max = 255)
	@NotNull
	@Schema(description = "郵便番号", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String postNumber;

	/**
	 * 地域名
	 */
	@Size(max = 1000)
	@Schema(description = "地域名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,1000]")
	private String areaName;

}
