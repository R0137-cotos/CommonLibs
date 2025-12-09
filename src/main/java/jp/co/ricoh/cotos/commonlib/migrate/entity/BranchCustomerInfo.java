package jp.co.ricoh.cotos.commonlib.migrate.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * 契約担当者情報を表すエンティティ
 * 
 */

@Entity
@Table(name = "branch_customer_info")
@Data
@EqualsAndHashCode(callSuper = true)
public class BranchCustomerInfo extends EntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "branch_customer_info_seq")
	@SequenceGenerator(name = "branch_customer_info_seq", sequenceName = "branch_customer_info_seq", allocationSize = 1)
	private long id;

	/**基本情報*/
	@ManyToOne(optional = false)
	@JoinColumn(name = "basic_contents_id", referencedColumnName = "id")
	@JsonIgnore
	private BasicContents basicContents;

	/**支社コード*/
	private String branchCustoemrCd;

	/**支社名*/
	private String branchCustoemrNm;

	/**課所コード*/
	private String officeCd;

	/**課所名*/
	private String officeNm;

	/**営業コード*/
	private String employeeCd;

	/**営業名*/
	private String employeeNm;

	/**担当営業電話番号*/
	private String employeePhoneNumber;

	/**担当営業メールアドレス*/
	private String employeeMailAddress;

	/**販売店コード*/
	private String distributorCd;

	/**販売店名*/
	private String distributorNm;

	/**OE届け先コード*/
	private String oeDeliveryCd;

	/**販売店区分*/
	private String distributorCustomerType;

	/**販売店担当営業*/
	private String distributorEmployeeNm;

	/**販売店担当営業メールアドレス*/
	private String distributorEmployeeMailAddress;

}
