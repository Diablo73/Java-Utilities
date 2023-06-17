package io.diablo73.common.enums;

import lombok.Getter;

/**
 * @author Diablo73
 * @version 1.0 <br> 11/03/2022
 * @since 11/03/2022
 */

@Getter
public enum SortEnum {

	ASC(true, false),
	DSC(false, true),
	;

	private final boolean isAscending;
	private final boolean isDescending;


	SortEnum(boolean isAscending, boolean isDescending) {
		this.isAscending = isAscending;
		this.isDescending = isDescending;
	}
}
