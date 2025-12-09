package jp.co.ricoh.cotos.commonlib.exception;

import lombok.Data;

@Data
public class ErrorInfo {

	private String errorId;

	private String errorMessage;

	private String errorField;

	private String errorEntity;
}
