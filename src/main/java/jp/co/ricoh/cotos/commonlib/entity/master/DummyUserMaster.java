package jp.co.ricoh.cotos.commonlib.entity.master;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ダミーユーザーを管理するマスタ
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "dummy_user_master")
public class DummyUserMaster extends EntityBaseMaster{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dummy_user_master_seq")
	@SequenceGenerator(name = "dummy_user_master_seq", sequenceName = "dummy_user_master_seq", allocationSize = 1)
	@Schema(description = "ダミーユーザーマスタID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * ダミーMoM社員ID
	 */
	@Column(nullable = false)
	@Schema(description = "ダミーMoM社員ID", requiredMode = Schema.RequiredMode.REQUIRED)
	private String userId;

	@Size(max=255)
	@Schema(description = "ダミー社員名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String empName;

	@Size(max=255)
	@Schema(description = "ダミー組織名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String orgName;

	@Size(max=1000)
	@Schema(description = "ダミー住所", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,1000]")
	private String address;
}
