package jp.co.ricoh.cotos.commonlib.dto.parameter.master;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AttachedFileProductGrpCheckMasterSearchParameter {

	/**
	 * 商品グループマスタID
	 */
	@NotNull
	@Column(nullable = false)
	@Schema(description = "商品グループマスタID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private Long productGrpMasterId;

	/**
	 * ドメイン
	 */
	@NotNull
	@Size(max = 255)
	@Schema(description = "ドメイン", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String domain;

	/**
	 * 見積/契約種別
	 */
	@NotNull
	@Size(max = 255)
	@Schema(description = "見積/契約種別", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String estimationContractType;

	/**
	 * ライフサイクル状態
	 */
	@NotNull
	@Size(max = 255)
	@Schema(description = "ライフサイクル状態", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String lifecycleStatus;

	/**
	 * 品種マスタIDリスト
	 */
	@NotNull
	@Schema(description = "品種マスタIDリスト", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private List<Long> itemMasterIdList;

	/**
	 * 手配業務タイプマスタID
	 */
	@Schema(description = "手配業務タイプマスタID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private Long arrangementWorkTypeMasterId;

	/**
	 * V-UP連携チェック除外フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "V-UP連携チェック除外フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer vupLinkageCheckExcludeFlg;

}
