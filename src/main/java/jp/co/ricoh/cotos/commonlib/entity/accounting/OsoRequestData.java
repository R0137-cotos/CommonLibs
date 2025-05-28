package jp.co.ricoh.cotos.commonlib.entity.accounting;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.common.OsoRequestDataAbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * OSO申込データ
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "oso_request_data")
public class OsoRequestData extends OsoRequestDataAbstractEntity {
	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "oso_request_data_seq")
	@SequenceGenerator(name = "oso_request_data_seq", sequenceName = "oso_request_data_seq", allocationSize = 1)
	@ApiModelProperty(value = "ID", required = true, position = 1, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 再連携フラグ
	 * 1: 再連携対象
	 * @deprecated SEによるリカバリの際のみ使用
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "再連携フラグ", required = false, position = 101, allowableValues = "range[0,9]")
	private Integer recoordinateFlg;
}
