package jp.co.ricoh.cotos.commonlib.migrate.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
