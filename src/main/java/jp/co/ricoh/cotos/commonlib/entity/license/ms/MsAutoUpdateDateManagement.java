package jp.co.ricoh.cotos.commonlib.entity.license.ms;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * MS_自動更新日連携管理
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "ms_auto_update_date_management")
public class MsAutoUpdateDateManagement extends EntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ms_auto_update_date_management_seq")
	@SequenceGenerator(name = "ms_auto_update_date_management_seq", sequenceName = "ms_auto_update_date_management_seq", allocationSize = 1)
	@ApiModelProperty(value = "MS_自動更新日連携管理ID", required = true, position = 1, allowableValues = "range[0,9223372036854775807]")
	private long id;

	/**
	 * ライセンス終了日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "ライセンス終了日", required = false, position = 2)
	private Date licenseTermEnd;

	/**
	 * 更新前ライセンス終了日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "更新前ライセンス終了日", required = false, position = 3)
	private Date beforeUpdateLicenseTermEnd;

	/**
	 * 品種マスタID
	 */
	@Min(0)
	@ApiModelProperty(value = "品種マスタID", required = true, position = 4, allowableValues = "range[0,9223372036854775807]")
	private Long itemMasterId;

	/**
	 * サブスクリプションID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "サブスクリプションID", required = false, position = 5, allowableValues = "range[0,255]")
	private String subscriptionId;
}
