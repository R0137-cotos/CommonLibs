package jp.co.ricoh.cotos.commonlib.entity.master;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(value = "リコーひかり請求情報変換マスタID", required = true, position = 1, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 疑似請求内訳コード
	 */
	@Column(nullable = false)
	@ApiModelProperty(value = "疑似請求内訳コード", required = true, position = 2, allowableValues = "range[0,255]")
	private String billingStatementCd;

	/**
	 * リコー品種コード
	 */
	@Column(nullable = false)
	@ApiModelProperty(value = "リコー品種コード", required = true, position = 3, allowableValues = "range[0,255]")
	private String ricohItemCode;

	/**
	 * 売上指示作成区分
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "売上指示作成区分", required = false, position = 4, allowableValues = "range[0,9]")
	private Integer createAccountingCd;

	/**
	 * 課税区分
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "課税区分", required = false, position = 5, allowableValues = "range[0,255]")
	private String taxationCd;
}
