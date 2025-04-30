package jp.co.ricoh.cotos.commonlib.entity.master;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(value = "アップグレード対象ライセンス品種対応マスタID", required = true, position = 1, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 商品マスタID
	 */
	@Column(nullable = false)
	@ApiModelProperty(value = "商品マスタID", required = true, position = 2, allowableValues = "range[0,9999999999999999999]")
	private Long productMasterId;

	/**
	 * アップグレード元品種マスタID
	 */
	@Column(nullable = false)
	@ApiModelProperty(value = "アップグレード元品種マスタID", required = true, position = 3, allowableValues = "range[0,9999999999999999999]")
	private Long fromItemMasterId;

	/**
	 * アップグレード先品種マスタID
	 */
	@Column(nullable = false)
	@ApiModelProperty(value = "アップグレード先品種マスタID", required = true, position = 4, allowableValues = "range[0,9999999999999999999]")
	private Long toItemMasterId;
}
