package jp.co.ricoh.cotos.commonlib.entity.master;

import jakarta.persistence.Column;
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
 * 商品グループ識別子マスタ
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "product_grp_identifier_master")
public class ProductGrpIdentifierMaster extends EntityBaseMaster {

	/**
	 * 商品グループ識別子マスタID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_grp_identifier_master_seq")
	@SequenceGenerator(name = "product_grp_identifier_master_seq", sequenceName = "product_grp_identifier_master_seq", allocationSize = 1)
	@ApiModelProperty(value = "商品グループ識別子マスタID", required = true, position = 1, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/***
	 * 商品グループ識別子
	 */
	@Column(nullable = false)
	@ApiModelProperty(value = "商品グループ識別子", required = true, position = 2, allowableValues = "range[255]")
	private String productGrpIdentifier;
	
	/***
	 * シーケンス名
	 */
	@Column(nullable = false)
	@ApiModelProperty(value = "シーケンス名", required = true, position = 3, allowableValues = "range[255]")
	private String sequenceName;
}
