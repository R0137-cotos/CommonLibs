package jp.co.ricoh.cotos.commonlib.dto.result;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * ルート条件式実行結果用パラメーター
 *
 */
@Data
public class RouteFormulaResult {

	public enum RouteFormulaStatus {
		正常, 異常, 警告;

		@JsonValue
		public String toValue() {
			return this.name();
		}

		@JsonCreator
		public static Enum<RouteFormulaStatus> fromValue(String name) {
			return Arrays.stream(values()).filter(v -> v.name() == name).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(name)));
		}
	}

	/**
	 * 条件式実行結果
	 */
	@Schema(description = "条件式判定結果", requiredMode = Schema.RequiredMode.REQUIRED)
	private boolean applyRoute;

	/**
	 * 条件式実行結果ステータス
	 */
	@Schema(description = "条件式実行結果ステータス", requiredMode = Schema.RequiredMode.REQUIRED)
	private RouteFormulaStatus status;

	/**
	 * エラー発生項目
	 */
	@Schema(description = "エラー発生項目", requiredMode = Schema.RequiredMode.REQUIRED)
	private List<String> errorPropertyList;
}
