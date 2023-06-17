package io.diablo73.common.enums;

import lombok.Getter;

/**
 * @author Diablo73
 * @version 1.0 <br> 13/03/2022
 * @since 27/02/2022
 */

@Getter
public enum CommonInfoEnum {

	DATE_PARSING_ERROR("0001", "DATE_ERROR", ""),
	INVALID_STRING("0002", "INVALID_STRING", ""),
	INVALID_LIST("0003", "INVALID_LIST", ""),
	EMAIL_FAILURE("0004", "EMAIL_FAILURE", ""),
	INVALID_NUMBER("0005", "INVALID_NUMBER", ""),

	FILE_NOT_FOUND("0404", "FILE_NOT_FOUND", ""),


	SUCCESS("1000", "SUCCESS", "Successful!!!"),

	;

	private final String resultCode;
	private final String resultStatus;
	private String resultMessage;


	CommonInfoEnum(String resultCode, String resultStatus, String resultMessage) {
		this.resultCode = resultCode;
		this.resultStatus = resultStatus;
		this.resultMessage = resultMessage;
	}

	public CommonInfoEnum setResultInfoEnumWithMessage(String ...resultMessage) {
		this.resultMessage = String.join(" : ", resultMessage);
		return this;
	}

}
