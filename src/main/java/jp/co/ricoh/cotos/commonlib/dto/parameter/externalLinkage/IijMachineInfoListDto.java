package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage;

import java.util.List;

import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * IIJ機器情報リストを設定するためのキー項目クラスを表します。
 */

@Data
public class IijMachineInfoListDto {

	/**
	 * IIJ機器情報リスト
	 */
	@Schema(description = "IIJ機器情報リスト", required = false)
	@Valid
	private List<IijMachineInfoDto> iijMachineInfoList;
}
