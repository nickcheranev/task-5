package ru.diasoft.ncheranev.otus.util;

import lombok.Getter;
import lombok.experimental.UtilityClass;

import java.util.Locale;

import static org.apache.commons.lang3.StringUtils.isBlank;

@UtilityClass
public class LocaleHolder {
    @Getter
    private static Locale locale;

    public static void setLocale(String localeArg) {
        if (isBlank(localeArg)) {
            LocaleHolder.locale = Locale.forLanguageTag("ru");
        } else if ("ru".equalsIgnoreCase(localeArg) || "en".equalsIgnoreCase(localeArg)) {
            LocaleHolder.locale = Locale.forLanguageTag(localeArg);
        } else {
            throw new IllegalArgumentException("Unsupported locale argument: " + localeArg + ". Use RU, EN or nothing");
        }
    }
}
