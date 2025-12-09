package jp.co.ricoh.cotos.commonlib.dto.parameter.estimation;

import java.util.Date;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.DtoBase;
import jp.co.ricoh.cotos.commonlib.entity.estimation.Estimation.LifecycleStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class EstimationCheckResultDto extends DtoBase {

	/**
	 * 対象ライフサイクル状態
	 */
	@NotNull
	@Schema(description = "対象ライフサイクル状態", required = true, allowableValues = "作成中(\"1\"), 作成完了(\"2\"), 受注(\"3\"), 失注(\"4\"), 破棄(\"5\")", example = "1")
	private LifecycleStatus targetLifecycleStatus;

	/**
	 * チェック事項コード
	 */
	@NotNull
	@Size(max = 255)
	@Schema(description = "チェック事項コード", required = true, allowableValues = "range[0,255]")
	private String checkMatterCode;

	/**
	 * チェック事項文面
	 */
	@NotNull
	@Size(max = 255)
	@Schema(description = "チェック事項文面", required = true, allowableValues = "range[0,255]")
	private String checkMatterText;

	/**
	 * 表示順
	 */
	@Min(0)
	@Max(999)
	@Schema(description = "表示順", required = true, allowableValues = "range[0,999]")
	private int displayOrder;

	/**
	 * チェック実施者
	 */
	@Size(max = 255)
	@Schema(description = "チェック実施者", required = false, allowableValues = "range[0,255]")
	private String checkedUserId;

	/**
	 * チェック実施者氏名
	 */
	@Size(max = 255)
	@Schema(description = "チェック実施者氏名", required = false, allowableValues = "range[0,255]")
	private String checkedUserName;

	/**
	 * チェック実施者組織名
	 */
	@Size(max = 255)
	@Schema(description = "チェック実施者組織名", required = false, allowableValues = "range[0,255]")
	private String checkedOrgName;

	/**
	 * チェック実施者日時
	 */
	@Schema(description = "チェック実施者日時", required = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date checkedAt;
}
