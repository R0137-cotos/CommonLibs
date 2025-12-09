package jp.co.ricoh.cotos.commonlib.migrate.entity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * 契約情報を表すエンティティ
 * 
 */

@Entity
@Table(name = "contract_info")
@Data
@EqualsAndHashCode(callSuper = true)
public class ContractInfo extends EntityBase {

	/**
	 * 注文識別フラグ
	 */
	public enum OrderType {

		新規("1"), 変更("2"), 解約("3");

		private final String text;

		private OrderType(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static OrderType fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contract_info_seq")
	@SequenceGenerator(name = "contract_info_seq", sequenceName = "contract_info_seq", allocationSize = 1)
	private long id;

	/**基本情報*/
	@ManyToOne(optional = false)
	@JoinColumn(name = "basic_contents_id", referencedColumnName = "id")
	@JsonIgnore
	private BasicContents basicContents;

	/**注文識別フラグ*/
	private OrderType orderType;

	/**契約ID*/
	private String contractId;

	/**オプション識別フラグ*/
	@JsonProperty("optionFlag")
	private Integer optionFlg;

	/**親オプション識別フラグ*/
	private String parentOptionProductCd;

	/**サービス開始日*/
	@Column(name = "service_start_date_2")
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
	@JsonProperty("serviceStartDate")
	private Date serviceStartDate2;

	/**サービス終了日*/
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
	private Date serviceEndDate;

	/**サービスコード*/
	private String serviceCd;

	/**サービス名*/
	private String serviceNm;

	/**サービス提供会社名*/
	private String serviceCorpNm;

	/**商品コード*/
	private String productCd;

	/**商品名*/
	private String productNm;

	/**提供方法*/
	private String provideMethod;

	/**現在の数量*/
	private Integer presentAmt;

	/**減数予定数*/
	private Integer expectMinusAmt;

	/**変更前数量*/
	@JsonProperty("amtBeforeChange")
	private Integer amtbeforeChange;

	/**売価単価*/
	private BigDecimal unitPrice;

	/**仕切額*/
	private BigDecimal dividingAmount;

	/**サブスクリプション番号*/
	private String subscriptionNumber;

	/**プロダクトID*/
	private String productId;

	/**料金プランID*/
	private String productRatePlanId;

	/**料金プランチャージID*/
	private String productRatePlanChargeId;

	/**レートプランチャージID*/
	private String ratePlanChargeId;

	/**仕入取引先コード*/
	private String bpCd;

	/**販事本仕切り*/
	private BigDecimal storedividingAmount1;

	/**RJ本部仕切り*/
	private BigDecimal storedividingAmount2;

	/**母店仕切り*/
	private BigDecimal storedividingAmount3;

	/**接点店仕切り*/
	private BigDecimal storedividingAmount4;

	/**消費税区分*/
	@JsonProperty("taxFlag")
	private String taxFlg;

}
