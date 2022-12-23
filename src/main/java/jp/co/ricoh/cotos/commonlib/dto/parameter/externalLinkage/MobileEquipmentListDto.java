package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage;

import java.util.List;

import javax.validation.Valid;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * モバイル機器リストを設定するためのキー項目クラスを表します。
 */

@Data
public class MobileEquipmentListDto {

	/**
	 * モバイル機器リスト
	 */
	@ApiModelProperty(value = "モバイル機器リスト", required = false, position = 1)
	@Valid
	private List<MobileEquipmentDto> mobileEquipmentList;
}
