package ru.diasoft.ncheranev.otus.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import ru.diasoft.ncheranev.otus.model.Question;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BundleResourceQuestionDaoTest {
    @Mock
    private MessageSource messageSource;
    @InjectMocks
    private BundleResourceQuestionDao sut;

    @Test
    @DisplayName("Успешное чтение вопросов из локализованного ресурса")
    void shouldReadSuccess() {
        String question = "'ANSWERS','TEXT'|'2.true 1.false 3.false','1 + 1'|'5.false 4.true','2 * 2'|'3.false 1.true','2 / 2'|";
        when(messageSource.getMessage("data.questions", null, null)).thenReturn(question);

        List<Question> questions = sut.read();
        assertThat(questions).hasSize(3);
    }

    @Test
    @DisplayName("Если ошибка чтения вопросов из локализованного ресурса, то RuntimeException")
    void shouldThrowRuntimeExceptionWhenReadError() {
        when(messageSource.getMessage("data.questions", null, null))
                .thenThrow(new NoSuchMessageException("code"));

        assertThatThrownBy(() -> sut.read())
                .isInstanceOf(RuntimeException.class)
                .hasMessage("org.springframework.context.NoSuchMessageException: No message found under code 'code' for locale 'ru_RU'.");
    }
}