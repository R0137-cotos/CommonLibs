package jp.co.ricoh.cotos.commonlib.dto.parameter.common;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * COTOSDTO共通項目 COTOSの更新用DTOはこのクラスのサブクラスとしてください。
 */
@MappedSuperclass
@Data
public class DtoBase {

	/**
	 * ID
	 */
	@Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * version
	 */
	@Version
	@Schema(description = "version", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private long version;

}

