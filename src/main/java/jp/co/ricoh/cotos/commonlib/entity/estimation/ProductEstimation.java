package jp.co.ricoh.cotos.commonlib.entity.estimation;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品を表すEntity
 */

@Entity
@EqualsAndHashCode(callSuper = true)
@EntityListeners(ProductEstimationListener.class)
@Data
@Table(name = "product_estimation")
public class ProductEstimation extends EntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_estimation_seq")
	@SequenceGenerator(name = "product_estimation_seq", sequenceName = "product_estimation_seq", allocationSize = 1)
	@Schema(description = "ID(作成時不要)", allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 商品マスタID
	 */
	@Min(0)
	@Column(nullable = false)
	@Schema(description = "商品マスタID<br/>※POST時「商品マスタ」存在チェック実施", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private long productMasterId;

	/**
	 * 商品名
	 */
	@Column(nullable = false)
	@NotNull
	@Size(max = 255)
	@Schema(description = "商品名", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String productEstimationName;

	/**
	 * 代表品種マスタID
	 */
	@Min(0)
	@Schema(description = "代表品種マスタID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private Long repItemMasterId;

	/**
	 * サービス識別番号
	 */
	@Size(max = 255)
	@Schema(description = "サービス識別番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String serviceIdentNumber;

	/**
	 * 見積
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "estimation_id", referencedColumnName = "id")
	@Schema(description = "見積", requiredMode = Schema.RequiredMode.REQUIRED)
	@JsonIgnore
	private Estimation estimation;

	/**
	 * 拡張項目
	 */
	@Schema(description = "拡張項目", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Lob
	private String extendsParameter;

	/**
	 * 拡張項目繰返
	 */
	@Schema(description = "拡張項目繰返", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Lob
	private String extendsParameterIterance;

	@PrePersist
	public void prePersist() {
		super.setCreatedAt(new Date());
	}
}
