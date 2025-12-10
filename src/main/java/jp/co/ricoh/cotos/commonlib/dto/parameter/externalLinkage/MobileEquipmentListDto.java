package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage;

import java.util.List;

import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * モバイル機器リストを設定するためのキー項目クラスを表します。
 */

@Data
public class MobileEquipmentListDto {

	/**
	 * モバイル機器リスト
	 */
	@Schema(description = "モバイル機器リスト", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Valid
	private List<MobileEquipmentDto> mobileEquipmentList;
}
