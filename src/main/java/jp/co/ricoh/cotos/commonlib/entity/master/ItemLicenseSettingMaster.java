package jp.co.ricoh.cotos.commonlib.entity.master;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "品種ライセンス用設定マスタID(作成時不要)", allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 品種マスタ
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "item_master_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "品種マスタ", requiredMode = Schema.RequiredMode.REQUIRED)
	private ItemMaster itemMaster;

	/**
	 * 拡張項目
	 */
	@Schema(description = "拡張項目", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Lob
	private String extendsParameter;

}
