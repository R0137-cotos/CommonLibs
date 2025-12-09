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

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "違約金明細振替ID(作成時不要)", allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 原価
	 */
	@DecimalMin("-9999999999999999999.99")
	@Digits(integer = 19, fraction = 2)
	@Schema(description = "原価", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[-9999999999999999999.99,9999999999999999999.99]")
	private BigDecimal price;

	/**
	 * 振替先課所コード
	 */
	@Size(max = 255)
	@Schema(description = "振替先課所コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String transToServiceOrgCode;

	/**
	 * 振替先課所名
	 */
	@Size(max = 255)
	@Schema(description = "振替先課所名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String transToServiceOrgName;

	/**
	 * 違約金明細
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "penalty_detail_contract_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "違約金明細", requiredMode = Schema.RequiredMode.REQUIRED)
	private PenaltyDetailContract penaltyDetailContract;

	/**
	 * イニシャル/ランニング区分
	 */
	@Schema(description = "イニシャル/ランニング区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "イニシャル(\"1\"), ランニング(\"2\"), 期間売(\"3\")", example = "1")
	private InitialRunningDiv initialRunningDiv;
}
