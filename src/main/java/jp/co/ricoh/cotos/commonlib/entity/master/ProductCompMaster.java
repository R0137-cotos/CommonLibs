package jp.co.ricoh.cotos.commonlib.entity.master;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 商品構成マスタ
 */
@Entity
@Data
@ToString(exclude = { "productGrpMaster", "productMaster" })
@EqualsAndHashCode(callSuper = true)
@Table(name = "product_comp_master")
public class ProductCompMaster extends EntityBaseMaster {

	/**
	 * 商品構成マスタID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_comp_master_seq")
	@SequenceGenerator(name = "product_comp_master_seq", sequenceName = "product_comp_master_seq", allocationSize = 1)
	@Schema(description = "商品構成マスタID", required = true, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 商品グループマスタ
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "product_grp_master_id", referencedColumnName = "id")
	@Schema(description = "商品グループマスタ", required = true)
	private ProductGrpMaster productGrpMaster;

	/**
	 * 商品マスタ
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "product_master_id", referencedColumnName = "id")
	@Schema(description = "商品マスタ", required = true)
	private ProductMaster productMaster;

}
