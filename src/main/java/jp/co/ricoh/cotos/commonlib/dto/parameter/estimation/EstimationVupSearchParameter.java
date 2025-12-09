package jp.co.ricoh.cotos.commonlib.dto.parameter.estimation;

import java.util.List;

import jakarta.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class EstimationVupSearchParameter {

	/**
	 * 見積番号＋見積番号枝番(ハイフン繋ぎ)
	 */
	@NotNull
	@Schema(description = "見積番号＋見積番号枝番(ハイフン繋ぎ)")
	private List<String> estimationNumberList;
}
