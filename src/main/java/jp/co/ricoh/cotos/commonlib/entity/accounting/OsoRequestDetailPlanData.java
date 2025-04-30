package jp.co.ricoh.cotos.commonlib.entity.accounting;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.common.OsoRequestDetailDataAbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * OSO申込明細予定データ
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "oso_request_detail_plan_data")
public class OsoRequestDetailPlanData extends OsoRequestDetailDataAbstractEntity {

	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "oso_request_detail_plan_data_seq")
	@SequenceGenerator(name = "oso_request_detail_plan_data_seq", sequenceName = "oso_request_detail_plan_data_seq", allocationSize = 1)
	@ApiModelProperty(value = "ID", required = true, position = 1, allowableValues = "range[0,9999999999999999999]")
	private long id;
}
