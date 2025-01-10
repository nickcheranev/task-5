package ru.diasoft.ncheranev.otus.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Locale;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LocaleHolderTest {

    public static Stream<Arguments> getLocaleMethodSource() {
        return Stream.of(
                Arguments.of(null, Locale.forLanguageTag("ru")),
                Arguments.of("ru", Locale.forLanguageTag("ru")),
                Arguments.of("RU", Locale.forLanguageTag("ru")),
                Arguments.of("en", Locale.forLanguageTag("en")),
                Arguments.of("EN", Locale.forLanguageTag("en"))
        );
    }

    @ParameterizedTest
    @MethodSource("getLocaleMethodSource")
    void whenCorrectTextThenReturnTargetLocale(String textLocale, Locale expected) {
        LocaleHolder.setLocale(textLocale);
        assertThat(LocaleHolder.getLocale()).isEqualTo(expected);
    }

    @Test
    void whenIncorrectTextThenThrowException() {
        assertThatThrownBy(() -> LocaleHolder.setLocale("de"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Unsupported locale argument: de. Use RU, EN or nothing");
    }
}