package jp.co.ricoh.cotos.commonlib.migrate.entity;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * 基本情報を表すエンティティ
 *
 */

@Entity
@Table(name = "basic_contents")
@Data
@EqualsAndHashCode(callSuper = true)
public class BasicContents extends EntityBase {

	/**基本情報ID*/
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "basic_contents_seq")
	@SequenceGenerator(name = "basic_contents_seq", sequenceName = "basic_contents_seq", allocationSize = 1)
	private long id;

	/**対象システム*/
	private String targetSys;

	/**bplats注文番号*/
	private String bplatsRequestNo;

	/**メーカー管理番号*/
	private String makerManageNo;

	/**契約名称サービス名*/
	private String contractNm;

	/**申込日*/
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
	private Date orderDate;

	/**サービス開始日*/
	@Temporal(TemporalType.DATE)
	@Column(name = "service_start_date_1")
	@JsonProperty("serviceStartDate")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
	private Date serviceStartDate1;

	/**解約予定日*/
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
	private Date cancelExpectDate;

	/**申請ステータス*/
	private String applicationStatus;

	/**契約作成日*/
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
	private Date contractCreateDate;

	/**更新期間*/
	private String updateInterval;

	/**課金開始日*/
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
	private Date paymentStartDate;

	/**商流区分*/
	private String commercialDistribute;

	/**NetRicoh会員ID*/
	@JsonProperty("netRicohAccount")
	private String netricohAccount;

	/**テナントID*/
	private String tenantId;

	/**zuoraアカウントId*/
	private String zuoraAccountId;

	/**契約開始日*/
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
	private Date contractStartDate;

	/**申請作成日*/
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd HH:mm:ss", timezone = "Asia/Tokyo")
	private Date createDateTime;

	/**申請更新日*/
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd HH:mm:ss", timezone = "Asia/Tokyo")
	private Date updateDateTime;

	/**API連携ステータス*/
	private String apiOrderStatus;

	/**申込ステータス*/
	private String orderStatus;

	/**
	 * 契約情報
	 */
	@OneToMany(mappedBy = "basicContents")
	private List<ContractInfo> contractInfoList;

	/**
	 * 契約者情報
	 */
	@OneToMany(mappedBy = "basicContents")
	private List<ContractorInfo> contractorInfoList;

	/**
	 * 契約担当者情報
	 */
	@OneToMany(mappedBy = "basicContents")
	private List<BranchCustomerInfo> branchCustomerInfoList;

	/**
	 * 販売店情報
	 */
	@OneToMany(mappedBy = "basicContents")
	private List<DistributorInfo> distributorInfoList;

	/**
	 * 母店情報
	 */
	@OneToMany(mappedBy = "basicContents")
	private List<ParentDistributorInfo> parentDistributorInfoList;

}
