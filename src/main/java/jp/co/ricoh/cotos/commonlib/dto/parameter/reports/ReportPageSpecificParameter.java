package jp.co.ricoh.cotos.commonlib.dto.parameter.reports;

import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.DetailStatus;
import lombok.Data;

/**
 * 帳票ページ特定パラメーター
 */
@Data
public class ReportPageSpecificParameter {

	/**
	 * 状態
	 */
	@Schema(description = "状態", required = false, allowableValues = "NOUPDATE(\"1\"), ADD(\"2\"), DELETE(\"3\"), UPDATE(\"4\")", example = "1")
	private DetailStatus status;

	/**
	 * リコー品種コード
	 */
	@Size(max = 255)
	@Schema(description = "リコー品種コード", required = false, allowableValues = "range[0,255]")
	private String ricohItemCode;
}
