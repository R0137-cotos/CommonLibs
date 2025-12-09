package jp.co.ricoh.cotos.commonlib.dto.parameter.contract;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.DealerAbstractDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class DealerContractDto extends DealerAbstractDto {

	/**
	 * 販売店コード
	 */
	@Size(max = 255)
	@Schema(description = "販売店コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String distributorCd;

	/**
	 * OE届け先コード
	 */
	@Size(max = 255)
	@Schema(description = "OE届け先コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String oeDeliveryCd;

	/**
	 * 担当営業メールアドレス
	 */
	@Size(max = 255)
	@Schema(description = "担当営業メールアドレス", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String distributorEmployeeMailAddress;

	/**
	 * Rings得意先コード
	 */
	@Size(max = 255)
	@Schema(description = "Rings得意先コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ringsCustomerCd;

	/**
	 * 取引先コード（手数料用）
	 */
	@Size(max = 255)
	@Schema(description = "取引先コード（手数料用）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String distributorRtcCd;

	/**
	 * 更新案内メール送信フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "更新案内メール送信フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer sendUpdateMailFlg;

}
