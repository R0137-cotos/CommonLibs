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

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "MS_自動更新日連携管理ID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private long id;

	/**
	 * ライセンス終了日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "ライセンス終了日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date licenseTermEnd;

	/**
	 * 更新前ライセンス終了日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "更新前ライセンス終了日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date beforeUpdateLicenseTermEnd;

	/**
	 * 品種マスタID
	 */
	@Min(0)
	@Schema(description = "品種マスタID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private Long itemMasterId;

	/**
	 * サブスクリプションID
	 */
	@Size(max = 255)
	@Schema(description = "サブスクリプションID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String subscriptionId;
}
