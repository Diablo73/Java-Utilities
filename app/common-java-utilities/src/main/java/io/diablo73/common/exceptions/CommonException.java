package io.diablo73.common.exceptions;

import io.diablo73.common.enums.CommonInfoEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CommonException extends RuntimeException {

	public CommonInfoEnum commonInfoEnum;


	public CommonException(CommonInfoEnum commonInfoEnum) {
		super(commonInfoEnum.getResultMessage());
		this.commonInfoEnum = commonInfoEnum;
	}

	public CommonException(CommonInfoEnum commonInfoEnum, Exception e) {
		super(commonInfoEnum.getResultMessage());
		this.commonInfoEnum = commonInfoEnum.setResultInfoEnumWithMessage(e.toString());
	}
}
