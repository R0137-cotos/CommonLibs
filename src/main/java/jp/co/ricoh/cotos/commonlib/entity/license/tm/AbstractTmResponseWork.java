package jp.co.ricoh.cotos.commonlib.entity.license.tm;

import java.util.Arrays;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(value = "HTTPステータス", required = false, position = 3, allowableValues = "range[0,255]")
	private String httpStatus;

	/**
	 * エラーメッセージ
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "HTTPステータス", required = false, position = 4, allowableValues = "range[0,255]")
	private String errorMessage;

	/**
	 * ライセンス反映状態
	 */
	@ApiModelProperty(value = "送信状態", required = false, allowableValues = "未反映(\"0\"), 反映済(\"1\"), 反映不要(\"2\")", example = "0", position = 5)
	private TmLicenceMappedStatus licenceMappedStatus;

}
