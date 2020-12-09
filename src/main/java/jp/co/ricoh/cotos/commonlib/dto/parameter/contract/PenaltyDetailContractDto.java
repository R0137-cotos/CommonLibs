package jp.co.ricoh.cotos.commonlib.dto.parameter.contract;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.DtoBase;
import jp.co.ricoh.cotos.commonlib.entity.contract.PenaltyDetailContract.SalesToType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class PenaltyDetailContractDto extends DtoBase {

	/**
	 * 計上先区分
	 */
	@ApiModelProperty(value = "計上先区分", required = false, allowableValues = "エンドユーザ(\"1\"), 課所止め(\"2\")", example = "1", position = 1)
	private SalesToType salesToType;
}
