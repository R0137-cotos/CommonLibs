package jp.co.ricoh.cotos.commonlib.entity.master;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "品種別チェック項目マスタID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 受付担当必須フラグ
	 */
	@Max(9)
	@Schema(description = "受付担当必須フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private int picAccRequiredFlg;

	/**
	 * 導入担当必須フラグ
	 */
	@Max(9)
	@Schema(description = "導入担当必須フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private int picIntRequiredFlg;

	/**
	 * 保守担当必須フラグ
	 */
	@Max(9)
	@Schema(description = "保守担当必須フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private int picMntRequiredFlg;

	/**
	 * 設置先必須フラグ
	 */
	@Max(9)
	@Schema(description = "設置先必須フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private int installationLocationRequiredFlg;

	/**
	 * 得意先必須フラグ
	 */
	@Max(9)
	@Schema(description = "得意先必須フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private int billingCustomerRequiredFlg;
}
