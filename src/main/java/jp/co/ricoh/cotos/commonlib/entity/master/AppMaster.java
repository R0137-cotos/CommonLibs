package jp.co.ricoh.cotos.commonlib.entity.master;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * アプリケーションマスタ
 */
@Entity
@Data
@ToString(exclude = { "systemMaster" })
@EqualsAndHashCode(callSuper = true)
@Table(name = "app_master")
public class AppMaster extends EntityBaseMaster {

	@Id
	@Schema(description = "アプリケーションID", required = true)
	private String appId;

	@Column(nullable = false)
	@Schema(description = "パスワード", required = true)
	private String password;

	@Column(nullable = false)
	@Schema(description = "オリジン", required = true)
	private String origin;

	@ManyToOne(optional = false)
	@JoinColumn(name = "system_id", referencedColumnName = "systemId")
	@Schema(description = "システムマスタ", required = true)
	@JsonIgnore
	private SystemMaster systemMaster;
}
