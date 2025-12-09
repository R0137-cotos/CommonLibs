package jp.co.ricoh.cotos.commonlib.dto.parameter.estimation;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.DealerAbstractDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class DealerEstimationDto extends DealerAbstractDto {

	/**
	 * 担当者メールアドレス
	 */
	@Size(max = 255)
	@Schema(description = "担当者メールアドレス", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String picMailAddress;

	/**
	 * 更新案内メール送信フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "更新案内メール送信フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer sendUpdateMailFlg;
}
