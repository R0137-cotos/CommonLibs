package jp.co.ricoh.cotos.commonlib.dto.parameter.arrangementDelegation;

import java.util.Arrays;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * ISP手配結果登録APIリクエストパラメータDTO
 */

@Data
public class IspArrangementResultRegistrationDto {

	@Description(value = "手配結果コード")
	public enum ResultCode {

		正常終了("1"), 異常終了("E");

		private final String text;

		private ResultCode(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static ResultCode fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * 手配業務ID
	 */
	@NotNull
	@Schema(description = "手配業務ID", requiredMode = Schema.RequiredMode.REQUIRED)
	private String id;

	/**
	 * 手配結果コード
	 */
	@NotNull
	@Schema(description = "手配結果コード", requiredMode = Schema.RequiredMode.REQUIRED)
	private ResultCode resultCode;

	/**
	 * エラー情報リスト
	 */
	@Valid
	@Schema(description = "エラー情報リスト", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<IspErrorInfo> errorList;

	/**
	 * IspUrlリスト
	 */
	@Valid
	@Schema(description = "IspUrlリスト", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<IspUrl> ispUrlList;

}