package jp.co.ricoh.cotos.commonlib.dto.parameter.contract.order;

import java.util.List;

import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 注文基本情報DTO
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class OrderBasicInfoDto {

	@Schema(description = "注文基底情報", required = false)
	@Valid
	private OrderBasicContentsDto basicContents;
	
	@Schema(description = "注文商品グループ情報", required = false)
	@Valid
	private OrderProductGroupInfoDto productGroupInfo;

	@Schema(description = "注文サービス固有情報", required = false)
	@Valid
	private OrderServiceInnerInfoDto serviceInnerInfo;

	@Schema(description = "注文販売店情報", required = false)
	@Valid
	private OrderDistributorInfoDto distributorInfo;

	@Schema(description = "注文セットアップ先情報", required = false)
	@Valid
	private OrderSetupInfoDto setupInfo;

	@Schema(description = "注文者情報", required = false)
	@Valid
	private OrdererInfoDto ordererInfo;

	@Schema(description = "注文商品情報", required = false)
	@Valid
	private List<OrderProductInfoDto> productInfo;

	@Schema(description = "注文担当支社情報", required = false)
	@Valid
	private OrderBranchCustomerInfoDto branchCustomerInfo;

	@Schema(description = "注文顧客情報", required = false)
	@Valid
	private OrderContractorInfoDto contractorInfo;

}
