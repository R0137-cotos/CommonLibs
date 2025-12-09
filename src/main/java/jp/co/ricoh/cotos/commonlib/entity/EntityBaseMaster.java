package jp.co.ricoh.cotos.commonlib.entity;

import java.util.Date;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Version;

import org.springframework.security.core.context.SecurityContextHolder;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.security.CotosAuthenticationDetails;
import lombok.Data;

/**
 * COTOSマスタエンティティー共通項目
 * COTOSのマスタエンティティー（COTOSでデータを管理するテーブルを持つエンティティー）はこのクラスのサブクラスとしてください。
 */
@MappedSuperclass
@Data
public class EntityBaseMaster {
	@Schema(description = "登録日時", required = true, readOnly = true)
	private Date createdAt;

	@Schema(description = "登録者", required = true, allowableValues = "range[0,255]", readOnly = true)
	private String createdUserId;

	@Schema(description = "更新日時", required = true, readOnly = true)
	private Date updatedAt;

	@Schema(description = "更新者", required = true, allowableValues = "range[0,255]", readOnly = true)
	private String updatedUserId;

	@Version
	@Schema(description = "version", required = true, allowableValues = "range[0,9999999999999999999]")
	private long version;

	@PrePersist
	public void prePersist() {
		CotosAuthenticationDetails userInfo = (CotosAuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		this.createdUserId = userInfo.getMomEmployeeId();
		this.createdAt = new Date();
	}

	@PreUpdate
	public void preUpdate() {
		CotosAuthenticationDetails userInfo = (CotosAuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		this.updatedUserId = userInfo.getMomEmployeeId();
		this.updatedAt = new Date();
	}
}
