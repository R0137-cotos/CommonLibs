package jp.co.ricoh.cotos.commonlib.entity.contract;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.common.CustomerAbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 契約情報の中で保持する顧客情報の洗い替え履歴Entity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "customer_contract_refresh_his")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class CustomerContractRefreshHis extends CustomerAbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_contract_refresh_his_seq")
	@SequenceGenerator(name = "customer_contract_refresh_his_seq", sequenceName = "customer_contract_refresh_his_seq", allocationSize = 1)
	@Schema(description = "ID(作成時不要)", allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 契約
	 */
	@OneToOne(optional = false)
	@JoinColumn(name = "contract_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "契約", requiredMode = Schema.RequiredMode.REQUIRED)
	private Contract contract;

	/**
	 * NetRicoh会員ID
	 */
	@Size(max = 255)
	@Schema(description = "NetRicoh会員ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String netricohAccount;

	/**
	 * 設置先名
	 */
	@Size(max = 255)
	@Schema(description = "設置先名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String setupCorpNm;

	/**
	 * 設置先郵便番号
	 */
	@Size(max = 255)
	@Schema(description = "設置先郵便番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String setupPostCd;

	/**
	 * 設置先住所
	 */
	@Size(max = 255)
	@Schema(description = "設置先住所", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String setupAddr;

	/**
	 * 設置先電話番号
	 */
	@Size(max = 255)
	@Schema(description = "設置先電話番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String setupTel;

	/**
	 * 洗替日
	 */
	@Schema(description = "洗替日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date refreshedAt;
}