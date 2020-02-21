package jp.co.ricoh.cotos.commonlib.migrate.entity;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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

	/**
	 * 申請ステータス
	 */
	public enum ApplicationStatus {

		作成("1"), 申請中("2"), 承認済み("3"), 確定("4"), 解約確定("7");

		private final String text;

		private ApplicationStatus(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static ApplicationStatus fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

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
	private ApplicationStatus applicationStatus;

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
