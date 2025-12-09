package jp.co.ricoh.cotos.commonlib.dto.parameter.estimation;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Lob;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.Parameter;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.ContractChangeTiming;
import lombok.Data;

@Data
public class EstimationRegisterParameter {

	/**
	 * 見積ID
	 */
	@Min(0)
	@Parameter(description = "見積ID", allowableValues = "range[0,9223372036854775807]", required = true)
	private long estimationId;

	/**
	 * 商品グループマスタID
	 */
	@Min(0)
	@Parameter(description = "商品グループマスタID", allowableValues = "range[0,9223372036854775807]", required = true)
	private long productGroupMasterId;

	/**
	 * 拡張項目
	 */
	@NotNull
	@Parameter(description = "拡張項目", required = true)
	@Lob
	private String extendsParameter;

	/**
	 * 登録者
	 */
	@NotNull
	@Size(max = 255)
	@Parameter(description = "登録者", allowableValues = "range[0,255]", required = true)
	private String createdUser;

	/**
	 * 見積明細
	 */
	@NotNull
	@Valid
	@Parameter(description = "見積明細", required = true)
	private List<EstimationDetailRegisterParameter> estimationDetailRegisterParameterList;

	/**
	 * サービス利用希望日
	 */
	@Temporal(TemporalType.DATE)
	@Parameter(description = "サービス利用希望日", required = false)
	private Date conclusionPreferredDate;

	/**
	 * 契約変更タイミング
	 */
	@Parameter(description = "契約変更タイミング", required = false)
	private ContractChangeTiming contractChangeTiming;
}
