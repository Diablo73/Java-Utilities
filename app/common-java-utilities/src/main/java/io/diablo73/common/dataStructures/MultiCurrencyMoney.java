package io.diablo73.common.dataStructures;

import io.diablo73.common.enums.CurrencyEnum;
import io.diablo73.common.utils.CheckOverflowUtil;
import io.diablo73.common.utils.MapperUtil;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.List;
import java.util.Objects;

@Getter
public class MultiCurrencyMoney {

	public static final String DEFAULT_CURRENCY_CODE 					= CurrencyEnum.INR.getCurrencyCode();
	public static final Currency DEFAULT_CURRENCY 						= Currency.getInstance(DEFAULT_CURRENCY_CODE);
	private static final RoundingMode DEFAULT_ROUNDING_MODE 			= RoundingMode.HALF_DOWN;
	private static final List<Integer> CENT_FACTORS = List.of(1, 10, 100, 1000, 10000, 100000, 1000000);

	private final long cent;
	private final Currency currency;
	private final String currencyNumericCode;

	public MultiCurrencyMoney() {
		this(new BigDecimal(0));
	}

	public MultiCurrencyMoney(int amount) {
		this(new BigDecimal(amount));
	}

	public MultiCurrencyMoney(long cent) {
		this(cent, DEFAULT_CURRENCY);
	}

	public MultiCurrencyMoney(long cent, Currency currency) {
		this(new BigDecimal(cent).movePointLeft(currency.getDefaultFractionDigits()), currency);
	}

	public MultiCurrencyMoney(String amount) {
		this(new BigDecimal(amount));
	}

	public MultiCurrencyMoney(String amount, Currency currency) {
		this(new BigDecimal(amount), currency);
	}

	public MultiCurrencyMoney(String amount, String currencyCodeOrNumericValue) {
		this(new BigDecimal(amount), Currency.getInstance(
				CurrencyEnum.getCurrencyEnumByCodeOrValue(currencyCodeOrNumericValue).getCurrencyCode()));
	}

	public MultiCurrencyMoney(BigDecimal amount) {
		this(amount, DEFAULT_CURRENCY);
	}

	public MultiCurrencyMoney(BigDecimal amount, Currency currency) {
		this(amount, currency, DEFAULT_ROUNDING_MODE);
	}

	public MultiCurrencyMoney(BigDecimal amount, RoundingMode roundingMode) {
		this(amount, DEFAULT_CURRENCY, roundingMode);
	}

	public MultiCurrencyMoney(BigDecimal amount, Currency currency, RoundingMode roundingMode) {
		CurrencyEnum isSupportedCurrencyEnum = CurrencyEnum.getCurrencyEnumByCodeOrValue(currency.getCurrencyCode());
		if (Objects.isNull(isSupportedCurrencyEnum)) {
			throw new RuntimeException();
		} else {
			this.currency = currency;
			this.currencyNumericCode = isSupportedCurrencyEnum.getCurrencyNumericCode();
		}
		this.cent = CheckOverflowUtil.rounding(amount.movePointRight(currency.getDefaultFractionDigits()), roundingMode);
	}

	public BigDecimal getAmount() {
		return BigDecimal.valueOf(cent, currency.getDefaultFractionDigits());
	}

	public int getCentFactor() {
		return CENT_FACTORS.get(currency.getDefaultFractionDigits());
	}

	@Override
	public String toString() {
		return MapperUtil.convertObject2JsonString(this);
	}
}
