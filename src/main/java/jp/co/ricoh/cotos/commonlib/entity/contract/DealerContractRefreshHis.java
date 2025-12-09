package jp.co.ricoh.cotos.commonlib.entity.contract;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.common.DealerAbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 契約情報の中で保持する販売店情報の洗い替え履歴Entity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "dealer_contract_refresh_his")
public class DealerContractRefreshHis extends DealerAbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dealer_contract_refresh_his_seq")
	@SequenceGenerator(name = "dealer_contract_refresh_his_seq", sequenceName = "dealer_contract_refresh_his_seq", allocationSize = 1)
	@Schema(description = "ID(作成時不要)", required = true, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 契約
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "contract_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "契約", required = true)
	private Contract contract;

	/**
	 * 販売店コード
	 */
	@Size(max = 255)
	@Schema(description = "販売店コード", required = false, allowableValues = "range[0,255]")
	private String distributorCd;

	/**
	 * OE届け先コード
	 */
	@Size(max = 255)
	@Schema(description = "OE届け先コード", required = false, allowableValues = "range[0,255]")
	private String oeDeliveryCd;

	/**
	 * 担当営業メールアドレス
	 */
	@Size(max = 255)
	@Schema(description = "担当営業メールアドレス", required = false, allowableValues = "range[0,255]")
	private String distributorEmployeeMailAddress;

	/**
	 * Rings得意先コード
	 */
	@Size(max = 255)
	@Schema(description = "Rings得意先コード", required = false, allowableValues = "range[0,255]")
	private String ringsCustomerCd;

	/**
	 * 取引先コード（手数料用）
	 */
	@Size(max = 255)
	@Schema(description = "取引先コード（手数料用）", required = false, allowableValues = "range[0,255]")
	private String distributorRtcCd;

	/**
	 * 洗替日
	 */
	@Schema(description = "洗替日", required = false)
	private Date refreshedAt;
}