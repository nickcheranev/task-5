package ru.diasoft.ncheranev.otus.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import ru.diasoft.ncheranev.otus.model.Answer;
import ru.diasoft.ncheranev.otus.model.Question;
import ru.diasoft.ncheranev.otus.util.LocaleHolder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DisplayName("Класс TextQuestionFormatter")
@ExtendWith(MockitoExtension.class)
class TextQuestionFormatterTest {
    @Mock
    private MessageSource messageSource;
    @InjectMocks
    private TextQuestionFormatter sut;

    @BeforeEach
    void beforeEach() {
        LocaleHolder.setLocale("ru");
    }

    @Test
    @DisplayName("Должен преобразовать объект вопроса в форматированный текст для вывода")
    void shouldFormatQuestionToText() {
        var question = new Question()
                .setText("Текст вопроса")
                .setAnswers(List.of(new Answer()
                        .setText("Ответ")));

        when(messageSource.getMessage("dialog.question", null, LocaleHolder.getLocale()))
                .thenReturn("Вопрос:");
        when(messageSource.getMessage("dialog.answers", null, LocaleHolder.getLocale()))
                .thenReturn("Ответы:");

        var formatted = sut.format(question);

        assertThat(formatted).isEqualTo(
                """
                        
                        Вопрос:
                          Текст вопроса
                        Ответы:
                          1. Ответ""");
    }

    @Test
    @DisplayName("Если вопросы == null, то не должно быть исключения")
    void whenNullAnswersThenNotShouldException() {
        var question = new Question()
                .setText("Текст вопроса");

        when(messageSource.getMessage("dialog.question", null, LocaleHolder.getLocale()))
                .thenReturn("Вопрос:");
        when(messageSource.getMessage("dialog.answers", null, LocaleHolder.getLocale()))
                .thenReturn("Ответы:");

        var formatted = sut.format(question);

        assertThat(formatted).isEqualTo(
                """
                        
                        Вопрос:
                          Текст вопроса
                        Ответы:
                          null""");
    }
}