package jp.co.ricoh.cotos.commonlib.dto.parameter.contract.intramart;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 登録結果情報DTO
 */
@Data
public class RegisterResultInfoDto {

	/**
	 * 登録件数
	 */
	@JsonProperty("registration_count")
	private String registrationCount;

	/**
	 * エラー情報
	 */
	@JsonProperty("error_info")
	private ErrorInfo errorInfo;

	@Data
	public static class ErrorInfo {

		/**
		 * エラー件数
		 */
		@JsonProperty("error_count")
		private String errorCount;

		/**
		 * エラー内容詳細
		 */
		@JsonProperty("error_content")
		private ErrorContent[] errorContent;

		@Data
		public static class ErrorContent {

			/**
			 * エラー文書ID
			 */
			@JsonProperty("error_documentId")
			private String errorDocumentid;

			/**
			 * エラー内容
			 */
			@JsonProperty("error_msg")
			private String errorMsg;

		}
	}

}
