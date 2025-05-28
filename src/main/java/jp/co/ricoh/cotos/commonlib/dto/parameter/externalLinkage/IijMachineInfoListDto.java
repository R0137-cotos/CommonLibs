package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage;

import java.util.List;

import jakarta.validation.Valid;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * IIJ機器情報リストを設定するためのキー項目クラスを表します。
 */

@Data
public class IijMachineInfoListDto {

	/**
	 * IIJ機器情報リスト
	 */
	@ApiModelProperty(value = "IIJ機器情報リスト", required = false, position = 1)
	@Valid
	private List<IijMachineInfoDto> iijMachineInfoList;
}
