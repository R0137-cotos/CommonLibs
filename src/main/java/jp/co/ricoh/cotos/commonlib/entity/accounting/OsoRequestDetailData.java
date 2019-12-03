package jp.co.ricoh.cotos.commonlib.entity.accounting;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.common.OsoRequestDetailDataAbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * OSO申込明細データ
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "oso_request_detail_data")
public class OsoRequestDetailData extends OsoRequestDetailDataAbstractEntity {
	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "oso_request_detail_data_seq")
	@SequenceGenerator(name = "oso_request_detail_data_seq", sequenceName = "oso_request_detail_data_seq", allocationSize = 1)
	@ApiModelProperty(value = "ID", required = true, position = 1, allowableValues = "range[0,9999999999999999999]")
	private long id;
}
