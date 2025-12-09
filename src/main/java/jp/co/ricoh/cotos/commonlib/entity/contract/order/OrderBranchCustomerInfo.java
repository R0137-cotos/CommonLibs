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
 * 注文担当支社情報
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "order_branch_customer_info")
public class OrderBranchCustomerInfo extends EntityBase {

	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_branch_customer_info_seq")
	@SequenceGenerator(name = "order_branch_customer_info_seq", sequenceName = "order_branch_customer_info_seq", allocationSize = 1)
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
	 * 支社コード
	 */
	@Column
	@Schema(description = "支社コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,]")
	private String branchCustomerCd;

	/**
	 * 支社名
	 */
	@Column
	@Schema(description = "支社名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,]")
	private String branchCustomerName;

	/**
	 * 課所コード
	 */
	@Column
	@Schema(description = "課所コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,]")
	private String officeCd;

	/**
	 * 課所名
	 */
	@Column
	@Schema(description = "課所名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,]")
	private String officeName;

	/**
	 * 営業コード
	 */
	@Column
	@Schema(description = "営業コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,]")
	private String employeeCd;

	/**
	 * 営業名
	 */
	@Column
	@Schema(description = "営業名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,]")
	private String employeeName;

	/**
	 * 担当営業電話番号
	 */
	@Column
	@Schema(description = "担当営業電話番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,]")
	private String employeePhoneNumber;

	/**
	 * 担当営業メールアドレス
	 */
	@Column
	@Schema(description = "担当営業メールアドレス", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,]")
	private String employeeMailAddress;

}
