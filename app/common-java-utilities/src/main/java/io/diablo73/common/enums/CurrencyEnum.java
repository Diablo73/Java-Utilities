package io.diablo73.common.enums;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Currency;
import java.util.Objects;

@Getter
public enum CurrencyEnum {

	INR("INR", "356", "₹", "Indian Rupee"),
	USD("USD", "840", "$", "US Dollar"),
	GBP("GBP", "826", "£", "British Pound"),
	EUR("EUR", "978", "€", "Euro"),
	RUB("RUB", "643", "RUB", "Russian Rouble"),
	PKR("PKR", "586", "PKR", "Pakistani Rupee"),
	CAD("CAD", "124", "CA$", "Canadian Dollar"),
	AUD("AUD", "36", "A$", "Australian Dollar"),
	XAU("XAU", "959", "XAU", "Gold"),
	JPY("JPY", "392", "JP¥", "Japanese Yen"),
	CNY("CNY", "156", "CN¥", "Chinese Yuan"),
	KRW("KRW", "410", "₩", "South Korean Won"),
	AED("AED", "784", "AED", "United Arab Emirates Dirham"),
	THB("THB", "764", "THB", "Thai Baht"),
	IDR("IDR", "360", "Rp", "Indonesian Rupiah"),
	;

	private final String currencyCode;
	private final String currencyValue;
	private final String currencySymbol;
	private final String currencyName;

	CurrencyEnum(String currencyCode, String currencyValue, String currencySymbol, String currencyName) {
		this.currencyCode = currencyCode;
		this.currencyValue = currencyValue;
		this.currencySymbol = currencySymbol;
		this.currencyName = currencyName;
	}

	public static CurrencyEnum getCurrencyEnumByCodeOrValue(String var) {
		if (Objects.isNull(var)) {
			return null;
		}
		boolean isCurrencyValue = StringUtils.isNumeric(var);
		for (CurrencyEnum currencyEnum : values()) {
			if (var.equals(isCurrencyValue ? currencyEnum.getCurrencyValue() : currencyEnum.getCurrencyCode())) {
				return currencyEnum;
			}
		}
		return null;
	}

	private static void listAllCurrenciesNotInEnum() {
		for (Currency c : Currency.getAvailableCurrencies()) {
			if (Objects.isNull(getCurrencyEnumByCodeOrValue(c.getCurrencyCode()))) {
				System.out.println(c.getCurrencyCode() + "(\"" + c.getCurrencyCode() + "\", \"" + c.getNumericCode() + "\", \"" + c.getSymbol() + "\", \"" + c.getDisplayName() + "\"),");
			}
		}
	}
}
