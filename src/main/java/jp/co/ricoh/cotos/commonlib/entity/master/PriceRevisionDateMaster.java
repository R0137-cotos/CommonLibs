package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 価格改定日マスタを表すEntity
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "price_revision_date_master")
public class PriceRevisionDateMaster extends EntityBaseMaster {

	/**
	 * 価格改定日マスタID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "price_revision_date_master_seq")
	@SequenceGenerator(name = "price_revision_date_master_seq", sequenceName = "price_revision_date_master_seq", allocationSize = 1)
	@ApiModelProperty(value = "価格改定日マスタID", required = true, position = 1, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 商品マスタID
	 */
	@Column(nullable = false)
	@ApiModelProperty(value = "商品マスタID", required = true, position = 2, allowableValues = "range[0,9999999999999999999]")
	private Long productMasterId;

	/**
	 * 価格改定日
	 */
	@Column(nullable = false)
	@ApiModelProperty(value = "価格改定日", required = true, position = 3)
	@Temporal(TemporalType.DATE)
	private Date priceRevisionDate;
}
