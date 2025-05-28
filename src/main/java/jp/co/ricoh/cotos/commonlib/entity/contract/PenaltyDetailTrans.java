package jp.co.ricoh.cotos.commonlib.entity.contract;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.InitialRunningDiv;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 違約金明細振替を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "penalty_detail_trans")
public class PenaltyDetailTrans extends EntityBase {

	/**
	 * 違約金明細振替ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "penalty_detail_trans_seq")
	@SequenceGenerator(name = "penalty_detail_trans_seq", sequenceName = "penalty_detail_trans_seq", allocationSize = 1)
	@ApiModelProperty(value = "違約金明細振替ID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 原価
	 */
	@DecimalMin("-9999999999999999999.99")
	@Digits(integer = 19, fraction = 2)
	@ApiModelProperty(value = "原価", required = false, position = 2, allowableValues = "range[-9999999999999999999.99,9999999999999999999.99]")
	private BigDecimal price;

	/**
	 * 振替先課所コード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "振替先課所コード", required = false, position = 3, allowableValues = "range[0,255]")
	private String transToServiceOrgCode;

	/**
	 * 振替先課所名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "振替先課所名", required = false, position = 4, allowableValues = "range[0,255]")
	private String transToServiceOrgName;

	/**
	 * 違約金明細
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "penalty_detail_contract_id", referencedColumnName = "id")
	@JsonIgnore
	@ApiModelProperty(value = "違約金明細", required = true, position = 5)
	private PenaltyDetailContract penaltyDetailContract;

	/**
	 * イニシャル/ランニング区分
	 */
	@ApiModelProperty(value = "イニシャル/ランニング区分", required = false, allowableValues = "イニシャル(\"1\"), ランニング(\"2\"), 期間売(\"3\")", example = "1", position = 6)
	private InitialRunningDiv initialRunningDiv;
}
