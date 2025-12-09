package jp.co.ricoh.cotos.commonlib.dto.parameter.master;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AttachedFileProductClassCheckMasterSearchParameter {

	/**
	 * 商品グループマスタID
	 */
	@NotNull
	@Column(nullable = false)
	@Schema(description = "商品グループマスタID", required = true, allowableValues = "range[0,9999999999999999999]")
	private Long productGrpMasterId;

	/**
	 * 商品種類区分
	 */
	@NotNull
	@Size(max = 255)
	@Schema(description = "商品種類区分", required = true, allowableValues = "range[0,255]")
	private String productClassDiv;

	/**
	 * ドメイン
	 */
	@NotNull
	@Size(max = 255)
	@Schema(description = "ドメイン", required = true, allowableValues = "range[0,255]")
	private String domain;

	/**
	 * 見積/契約種別
	 */
	@NotNull
	@Size(max = 255)
	@Schema(description = "見積/契約種別", required = true, allowableValues = "range[0,255]")
	private String estimationContractType;

	/**
	 * ライフサイクル状態
	 */
	@NotNull
	@Size(max = 255)
	@Schema(description = "ライフサイクル状態", required = true, allowableValues = "range[0,255]")
	private String lifecycleStatus;

	/**
	 * 手配業務タイプマスタID
	 */
	@Schema(description = "手配業務タイプマスタID", required = false, allowableValues = "range[0,9223372036854775807]")
	private Long arrangementWorkTypeMasterId;

	/**
	 * V-UP連携チェック除外フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "V-UP連携チェック除外フラグ", required = false, allowableValues = "range[0,9]")
	private Integer vupLinkageCheckExcludeFlg;

}
