package jp.co.ricoh.cotos.commonlib.entity.estimation;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.common.DealerAbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 見積情報の中で保持する販売店情報を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@EntityListeners(DealerEstimationListener.class)
@Data
@Table(name = "dealer_estimation")
public class DealerEstimation extends DealerAbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dealer_estimation_seq")
	@SequenceGenerator(name = "dealer_estimation_seq", sequenceName = "dealer_estimation_seq", allocationSize = 1)
	@Schema(description = "ID(作成時不要)", readOnly = true)
	private long id;

	/**
	 * 見積
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "estimation_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "見積", requiredMode = Schema.RequiredMode.REQUIRED)
	private Estimation estimation;

	/**
	 * 担当者メールアドレス
	 */
	@Size(max = 255)
	@Schema(description = "担当者メールアドレス", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String picMailAddress;

	/**
	 * 更新案内メール送信フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "更新案内メール送信フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer sendUpdateMailFlg;

}
