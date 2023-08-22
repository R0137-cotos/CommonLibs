package jp.co.ricoh.cotos.commonlib.dto.parameter.master;

import java.util.List;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AttachedFileProductGrpCheckMasterSearchParameter {

	/**
	 * 商品グループマスタID
	 */
	@NotNull
	@Column(nullable = false)
	@ApiModelProperty(value = "商品グループマスタID", required = true, position = 1, allowableValues = "range[0,9999999999999999999]")
	private Long productGrpMasterId;

	/**
	 * ドメイン
	 */
	@NotNull
	@Size(max = 255)
	@ApiModelProperty(value = "ドメイン", required = true, position = 2, allowableValues = "range[0,255]")
	private String domain;

	/**
	 * 見積/契約種別
	 */
	@NotNull
	@Size(max = 255)
	@ApiModelProperty(value = "見積/契約種別", required = true, position = 3, allowableValues = "range[0,255]")
	private String estimationContractType;

	/**
	 * ライフサイクル状態
	 */
	@NotNull
	@Size(max = 255)
	@ApiModelProperty(value = "ライフサイクル状態", required = true, position = 4, allowableValues = "range[0,255]")
	private String lifecycleStatus;

	/**
	 * 品種マスタIDリスト
	 */
	@NotNull
	@ApiModelProperty(value = "品種マスタIDリスト", required = true, position = 5, allowableValues = "range[0,9223372036854775807]")
	private List<Long> itemMasterIdList;

	/**
	 * 手配業務タイプマスタID
	 */
	@ApiModelProperty(value = "手配業務タイプマスタID", required = false, position = 6, allowableValues = "range[0,9223372036854775807]")
	private Long arrangementWorkTypeMasterId;

	/**
	 * V-UP連携チェック除外フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "V-UP連携チェック除外フラグ", required = false, position = 7, allowableValues = "range[0,9]")
	private Integer vupLinkageCheckExcludeFlg;

}
