package io.diablo73.common.enums;

import lombok.Getter;

/**
 * @author Diablo73
 * @version 1.0 <br> 10/03/2022
 * @since 27/02/2022
 */

@Getter
public enum ResultInfoEnum {

	AUTH_CHECK_FAILED("0000", "INTRUDER", "Basic authentication failed!!!"),
	WRONG_FUNCTION("0001", "ILLEGAL_API", "Mismatch in API name and function!!!"),
	WRONG_DOCUMENT_ID("0002", "ILLEGAL_DOCUMENT_ID", "DocumentId provided is wrong!!!"),
	DOCUMENT_NOT_FOUND("0003", "NO_DOCUMENT", "Document not found!!!"),


	SEARCH_PARAM_EMPTY("0101", "NO_PARAMETERS", "No parameters for searching provided!!!"),
	WRONG_SEARCH_PARAM("0102", "ILLEGAL_SEARCH_PARAM", "Search parameter provided is wrong!!!"),


	SUCCESS("1000", "SUCCESS", "Successful!!!"),
	EMAIL_SUCCESS("1001", "EMAIL_SUCCESS", ""),

	;

	private final String resultCode;
	private final String resultStatus;
	private final String resultMessage;


	ResultInfoEnum(String resultCode, String resultStatus, String resultMessage) {
		this.resultCode = resultCode;
		this.resultStatus = resultStatus;
		this.resultMessage = resultMessage;
	}

}
