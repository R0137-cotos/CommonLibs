package jp.co.ricoh.cotos.commonlib.entity.master;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 品種別チェック項目マスタを表すEntity
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "check_by_item_master")
public class CheckByItemMaster extends EntityBaseMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "check_by_item_master_seq")
	@SequenceGenerator(name = "check_by_item_master_seq", sequenceName = "check_by_item_master_seq", allocationSize = 1)
	@ApiModelProperty(value = "品種別チェック項目マスタID", required = true, position = 1, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 受付担当必須フラグ
	 */
	@Max(9)
	@ApiModelProperty(value = "受付担当必須フラグ", required = false, position = 2, allowableValues = "range[0,9]")
	private int picAccRequiredFlg;

	/**
	 * 導入担当必須フラグ
	 */
	@Max(9)
	@ApiModelProperty(value = "導入担当必須フラグ", required = false, position = 3, allowableValues = "range[0,9]")
	private int picIntRequiredFlg;

	/**
	 * 保守担当必須フラグ
	 */
	@Max(9)
	@ApiModelProperty(value = "保守担当必須フラグ", required = false, position = 4, allowableValues = "range[0,9]")
	private int picMntRequiredFlg;

	/**
	 * 設置先必須フラグ
	 */
	@Max(9)
	@ApiModelProperty(value = "設置先必須フラグ", required = false, position = 5, allowableValues = "range[0,9]")
	private int installationLocationRequiredFlg;
}
