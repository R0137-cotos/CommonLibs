package jp.co.ricoh.cotos.commonlib.entity.license.tm;

import java.util.Arrays;

import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * トレンドマイクロレスポンスWORK共通項目用抽象クラス
 */
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
@Data
public abstract class AbstractTmResponseWork extends EntityBase {

	/**
	 * TMライセンス反映状態
	 */

	@Description(value = "送信状態(トレンドマイクロレスポンスWORK)")
	public enum TmLicenceMappedStatus {

		未反映("0"), 反映済("1"), 反映不要("2");

		private final String text;

		private TmLicenceMappedStatus(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static TmLicenceMappedStatus fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * HTTPステータス
	 */
	@Size(max = 255)
	@Schema(description = "HTTPステータス", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String httpStatus;

	/**
	 * エラーメッセージ
	 */
	@Size(max = 255)
	@Schema(description = "HTTPステータス", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String errorMessage;

	/**
	 * ライセンス反映状態
	 */
	@Schema(description = "送信状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未反映(\"0\"), 反映済(\"1\"), 反映不要(\"2\")", example = "0")
	private TmLicenceMappedStatus licenceMappedStatus;

}
