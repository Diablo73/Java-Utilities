package io.diablo73.common.exceptions;

import io.diablo73.common.enums.ResultInfoEnum;
import lombok.Data;

/**
 * @author Diablo73
 * @version 1.0 <br> 27/02/2022
 * @since 27/02/2022
 */

@Data
public class ValidatorException extends RuntimeException {

	public ResultInfoEnum resultInfoEnum;


	public ValidatorException(ResultInfoEnum resultInfoEnum) {
		super(resultInfoEnum.getResultMessage());
		this.resultInfoEnum = resultInfoEnum;
	}
}
