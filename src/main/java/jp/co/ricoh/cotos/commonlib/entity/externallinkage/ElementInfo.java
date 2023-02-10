package jp.co.ricoh.cotos.commonlib.entity.externallinkage;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 部材情報を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "element_info")
public class ElementInfo extends EntityBase {

	/**
	 * 部材情報ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "element_info_seq")
	@SequenceGenerator(name = "element_info_seq", sequenceName = "element_info_seq", allocationSize = 1)
	@ApiModelProperty(value = "部材情報ID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 契約ID
	 */
	@Min(0)
	@ApiModelProperty(value = "契約ID", required = false, position = 2, allowableValues = "range[0,9223372036854775807]")
	private Long contractId;

	/**
	 * 注文なしフラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "注文なしフラグ", required = false, position = 3, allowableValues = "range[0,9]")
	private Integer noOrderFlg;

	/**
	 * 原価合計金額
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@ApiModelProperty(value = "原価合計金額", required = false, position = 4, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal totalPrice;

	/**
	 * 部材情報明細
	 */
	@OneToMany(mappedBy = "elementInfo")
	@ApiModelProperty(value = "部材情報明細", required = false, position = 5)
	private List<ElementInfoDetail> elementInfoDetailList;
}
