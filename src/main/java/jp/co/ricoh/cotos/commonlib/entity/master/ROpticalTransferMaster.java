package jp.co.ricoh.cotos.commonlib.entity.master;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * リコーひかり請求情報変換マスタ
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "r_optical_transfer_master")
public class ROpticalTransferMaster extends EntityBaseMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "r_optical_transfer_master_seq")
	@SequenceGenerator(name = "r_optical_transfer_master_seq", sequenceName = "r_optical_transfer_master_seq", allocationSize = 1)
	@Schema(description = "リコーひかり請求情報変換マスタID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 疑似請求内訳コード
	 */
	@Column(nullable = false)
	@Schema(description = "疑似請求内訳コード", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String billingStatementCd;

	/**
	 * リコー品種コード
	 */
	@Column(nullable = false)
	@Schema(description = "リコー品種コード", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String ricohItemCode;

	/**
	 * 売上指示作成区分
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "売上指示作成区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer createAccountingCd;

	/**
	 * 課税区分
	 */
	@Size(max = 255)
	@Schema(description = "課税区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String taxationCd;
}
