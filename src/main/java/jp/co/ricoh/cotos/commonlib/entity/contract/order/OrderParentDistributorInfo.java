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
 * 注文母店情報
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "order_parent_distributor_info")
public class OrderParentDistributorInfo extends EntityBase {

	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_parent_distributor_info_seq")
	@SequenceGenerator(name = "order_parent_distributor_info_seq", sequenceName = "order_parent_distributor_info_seq", allocationSize = 1)
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
	 * 販売店コード
	 */
	@Column
	@Schema(description = "販売店コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,]")
	private String distributorCd;

	/**
	 * 販売店名
	 */
	@Column
	@Schema(description = "販売店名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,]")
	private String distributorName;

	/**
	 * OE届け先コード
	 */
	@Column
	@Schema(description = "OE届け先コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,]")
	private String oeDeliveryCd;

	/**
	 * 販売店区分
	 */
	@Column
	@Schema(description = "販売店区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,]")
	private String distributorCustomerType;

	/**
	 * 販売店担当営業
	 */
	@Column
	@Schema(description = "販売店担当営業", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,]")
	private String distributorEmployeeName;

	/**
	 * 販売店担当営業メールアドレス
	 */
	@Column
	@Schema(description = "販売店担当営業メールアドレス", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,]")
	private String distributorEmployeeMailAddress;

	/**
	 * Rings得意先コード
	 */
	@Column
	@Schema(description = "Rings得意先コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,]")
	private String ringsCustomerCd;

	/**
	 * 販売店郵便番号
	 */
	@Column
	@Schema(description = "販売店郵便番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,]")
	private String distributorPostNumber;

	/**
	 * 販売店住所
	 */
	@Column
	@Schema(description = "販売店住所", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,]")
	private String distributorAddress;

}
