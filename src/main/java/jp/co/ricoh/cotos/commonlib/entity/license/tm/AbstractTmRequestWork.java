package jp.co.ricoh.cotos.commonlib.entity.license.tm;

import java.util.Arrays;
import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * トレンドマイクロリクエストWORK共通項目用抽象クラス
 */
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
@Data
public abstract class AbstractTmRequestWork extends EntityBase {

	/**
	 * TM送信状態
	 */

	@Description(value = "送信状態(トレンドマイクロリクエストWORK)")
	public enum TmRequestStatus {

		未連携("0"), 連携済("1"), 連携エラー("2"), 連携対象外("3");

		private final String text;

		private TmRequestStatus(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static TmRequestStatus fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * 送信状態
	 */
	@ApiModelProperty(value = "送信状態", required = false, allowableValues = "未連携(\"0\"), 連携済(\"1\"), 連携エラー(\"2\"), 連携対象外(\"3\")", example = "0", position = 2)
	private TmRequestStatus requestStatus;

	/**
	 * 送信時刻
	 */
	@ApiModelProperty(value = "送信時刻", required = false, position = 3)
	@Temporal(TemporalType.TIMESTAMP)
	private Date requestTime;

	/**
	 * URL
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "URL", required = false, position = 4, allowableValues = "range[0,255]")
	private String url;

	/**
	 * Http Header
	 */
	@Size(max = 4000)
	@ApiModelProperty(value = "Http Header", required = false, position = 5, allowableValues = "range[0,4000]")
	private String httpHeader;

	/**
	 * Http Body
	 */
	@Size(max = 4000)
	@ApiModelProperty(value = "Http Body", required = false, position = 6, allowableValues = "range[0,4000]")
	private String httpBody;
}
