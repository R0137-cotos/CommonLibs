package jp.co.ricoh.cotos.commonlib.entity.contract.order;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 注文サービス固有情報
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "order_service_inner_info")
public class OrderServiceInnerInfo extends EntityBase {

	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_service_inner_info_seq")
	@SequenceGenerator(name = "order_service_inner_info_seq", sequenceName = "order_service_inner_info_seq", allocationSize = 1)
	@Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED)
	private long id;

	/**
	 * 注文基本情報ID
	 */
	@OneToOne(optional = false)
	@JoinColumn(name = "order_basic_info_id", referencedColumnName = "id")
	@JsonIgnore
	private OrderBasicInfo orderBasicInfo;

	/**
	 * 固有項目１
	 */
	@Column
	@Schema(description = "固有項目１", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,]")
	private String item1;

	/**
	 * 固有項目２
	 */
	@Column
	@Schema(description = "固有項目２", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,]")
	private String item2;

	/**
	 * 固有項目３
	 */
	@Column
	@Schema(description = "固有項目３", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,]")
	private String item3;

	/**
	 * 固有項目４
	 */
	@Column
	@Schema(description = "固有項目４", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,]")
	private String item4;

	/**
	 * 固有項目５
	 */
	@Column
	@Schema(description = "固有項目５", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,]")
	private String item5;

	/**
	 * 固有項目６
	 */
	@Column
	@Schema(description = "固有項目６", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,]")
	private String item6;

	/**
	 * 固有項目７
	 */
	@Column
	@Schema(description = "固有項目７", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,]")
	private String item7;

	/**
	 * 固有項目８
	 */
	@Column
	@Schema(description = "固有項目８", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,]")
	private String item8;

	/**
	 * 固有項目９
	 */
	@Column
	@Schema(description = "固有項目９", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,]")
	private String item9;

	/**
	 * 固有項目１０
	 */
	@Column
	@Schema(description = "固有項目１０", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,]")
	private String item10;

	/**
	 * 固有項目１１
	 */
	@Column
	@Schema(description = "固有項目１１", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,]")
	private String item11;

	/**
	 * 固有項目１２
	 */
	@Column
	@Schema(description = "固有項目１２", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,]")
	private String item12;

	/**
	 * 固有項目１３
	 */
	@Column
	@Schema(description = "固有項目１３", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,]")
	private String item13;

	/**
	 * 固有項目１４
	 */
	@Column
	@Schema(description = "固有項目１４", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,]")
	private String item14;

	/**
	 * 固有項目１５
	 */
	@Column
	@Schema(description = "固有項目１５", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,]")
	private String item15;

	/**
	 * 固有項目１６
	 */
	@Column
	@Schema(description = "固有項目１６", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,]")
	private String item16;

	/**
	 * 固有項目１７
	 */
	@Column
	@Schema(description = "固有項目１７", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,]")
	private String item17;

	/**
	 * 固有項目１８
	 */
	@Column
	@Schema(description = "固有項目１８", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,]")
	private String item18;

	/**
	 * 固有項目１９
	 */
	@Column
	@Schema(description = "固有項目１９", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,]")
	private String item19;

	/**
	 * 固有項目２０
	 */
	@Column
	@Schema(description = "固有項目２０", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,]")
	private String item20;

	/**
	 * 固有項目２１
	 */
	@Column
	@Schema(description = "固有項目２１", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,]")
	private String item21;

	/**
	 * 固有項目２２
	 */
	@Column
	@Schema(description = "固有項目２２", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,]")
	private String item22;

	/**
	 * 固有項目２３
	 */
	@Column
	@Schema(description = "固有項目２３", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,]")
	private String item23;

	/**
	 * 固有項目２４
	 */
	@Column
	@Schema(description = "固有項目２４", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,]")
	private String item24;

	/**
	 * 固有項目２５
	 */
	@Column
	@Schema(description = "固有項目２５", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,]")
	private String item25;

	/**
	 * 固有項目２６
	 */
	@Column
	@Schema(description = "固有項目２６", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,]")
	private String item26;

	/**
	 * 固有項目２７
	 */
	@Column
	@Schema(description = "固有項目２７", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,]")
	private String item27;

	/**
	 * 固有項目２８
	 */
	@Column
	@Schema(description = "固有項目２８", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,]")
	private String item28;

	/**
	 * 固有項目２９
	 */
	@Column
	@Schema(description = "固有項目２９", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,]")
	private String item29;

	/**
	 * 固有項目３０
	 */
	@Column
	@Schema(description = "固有項目３０", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,]")
	private String item30;

}
