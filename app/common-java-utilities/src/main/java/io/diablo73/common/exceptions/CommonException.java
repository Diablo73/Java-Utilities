package io.diablo73.common.exceptions;

import io.diablo73.common.enums.CommonInfoEnum;
import lombok.Data;

/**
 * @author Diablo73
 * @version 1.0 <br> 10/03/2022
 * @since 10/03/2022
 */

@Data
public class CommonException extends RuntimeException {

	public CommonInfoEnum commonInfoEnum;


	public CommonException(CommonInfoEnum commonInfoEnum) {
		super(commonInfoEnum.getResultMessage());
		this.commonInfoEnum = commonInfoEnum;
	}
}
