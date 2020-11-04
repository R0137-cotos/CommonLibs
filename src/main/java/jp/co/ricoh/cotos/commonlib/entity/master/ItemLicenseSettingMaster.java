package jp.co.ricoh.cotos.commonlib.entity.master;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 品種ライセンス用設定マスタ
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "item_license_setting_master")
public class ItemLicenseSettingMaster extends EntityBaseMaster {

	/**
	 * 品種ライセンス用設定マスタID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_license_setting_master_seq")
	@SequenceGenerator(name = "item_license_setting_master_seq", sequenceName = "item_license_setting_master_seq", allocationSize = 1)
	@ApiModelProperty(value = "品種ライセンス用設定マスタID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 品種マスタID
	 */
	@NotNull
	@Column(nullable = false)
	@Min(0)
	@ApiModelProperty(value = "品種マスタID", required = true, position = 2, allowableValues = "range[0,9223372036854775807]")
	private Long itemMasterId;

	/**
	 * 拡張項目
	 */
	@ApiModelProperty(value = "拡張項目", required = true, position = 3)
	@Lob
	private String extendsParameter;

}
