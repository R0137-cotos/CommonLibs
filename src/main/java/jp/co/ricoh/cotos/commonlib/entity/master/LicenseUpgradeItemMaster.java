package jp.co.ricoh.cotos.commonlib.entity.master;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * アップグレード対象ライセンス品種対応マスタを表すEntity
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "license_upgrade_item_master")
public class LicenseUpgradeItemMaster extends EntityBaseMaster {

	/**
	 * アップグレード対象ライセンス品種対応マスタID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "license_upgrade_item_master_seq")
	@SequenceGenerator(name = "license_upgrade_item_master_seq", sequenceName = "license_upgrade_item_master_seq", allocationSize = 1)
	@Schema(description = "アップグレード対象ライセンス品種対応マスタID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 商品マスタID
	 */
	@Column(nullable = false)
	@Schema(description = "商品マスタID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private Long productMasterId;

	/**
	 * アップグレード元品種マスタID
	 */
	@Column(nullable = false)
	@Schema(description = "アップグレード元品種マスタID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private Long fromItemMasterId;

	/**
	 * アップグレード先品種マスタID
	 */
	@Column(nullable = false)
	@Schema(description = "アップグレード先品種マスタID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private Long toItemMasterId;
}
