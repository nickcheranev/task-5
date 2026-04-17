package ru.diasoft.ncheranev.otus.shell;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class LocaleCommandTest {
    @InjectMocks
    private LocaleCommand sut;

    @Test
    void locale() {
        var selectedLocale = sut.locale("RU");

        assertThat(selectedLocale).isEqualTo("Выбран язык: RU");
    }
}