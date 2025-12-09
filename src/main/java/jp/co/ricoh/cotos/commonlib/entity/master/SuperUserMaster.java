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
 * スーパーユーザーを管理するマスター
 */

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "super_user_master")
public class SuperUserMaster extends EntityBaseMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "super_user_master_seq")
	@SequenceGenerator(name = "super_user_master_seq", sequenceName = "super_user_master_seq", allocationSize = 1)
	@Schema(description = "スーパーユーザーマスタID", required = true, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * MoM社員ID
	 */
	@Column(nullable = false)
	@Schema(description = "MoM社員ID", required = true)
	private String userId;
}
