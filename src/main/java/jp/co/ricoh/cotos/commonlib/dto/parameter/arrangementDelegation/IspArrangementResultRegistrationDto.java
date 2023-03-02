package jp.co.ricoh.cotos.commonlib.dto.parameter.arrangementDelegation;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;
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
	@Valid
	@NotNull
	@ApiModelProperty(value = "手配業務ID", required = true, position = 1)
	private String id;

	/**
	 * 手配結果コード
	 */
	@Valid
	@NotNull
	@ApiModelProperty(value = "手配結果コード", required = true, position = 2)
	private ResultCode resultCode;

	/**
	 * エラー情報リスト
	 */
	@Valid
	@ApiModelProperty(value = "エラー情報リスト", required = false, position = 3)
	private List<IspErrorInfo> errorList;

	/**
	 * IspUrlリスト
	 */
	@Valid
	@ApiModelProperty(value = "IspUrlリスト", required = false, position = 4)
	private List<IspUrl> ispUrlList;

}