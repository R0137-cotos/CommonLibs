package jp.co.ricoh.cotos.commonlib.entity.master;

import jakarta.persistence.Column;
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
	@Schema(description = "商品グループ識別子マスタID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/***
	 * 商品グループ識別子
	 */
	@Column(nullable = false)
	@Schema(description = "商品グループ識別子", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[255]")
	private String productGrpIdentifier;
	
	/***
	 * シーケンス名
	 */
	@Column(nullable = false)
	@Schema(description = "シーケンス名", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[255]")
	private String sequenceName;
}
