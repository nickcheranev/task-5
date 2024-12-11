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
import ru.diasoft.ncheranev.otus.model.Quiz;
import ru.diasoft.ncheranev.otus.util.AnswerValidatorException;
import ru.diasoft.ncheranev.otus.util.LocaleHolder;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("Класс QuizShowImpl")
@ExtendWith(MockitoExtension.class)
class QuizShowImplTest {
    @Mock
    private QuestionFormatter questionFormatter;
    @Mock
    private AnswerValidator answerValidator;
    @Mock
    private UserInterface userInterface;
    @Mock
    private MessageSource messageSource;
    @InjectMocks
    private QuizShowImpl tester;

    @BeforeEach
    void beforeEach() {
        LocaleHolder.setLocale("EN");
    }

    @DisplayName("Правильные ответы")
    @Test
    void shouldShowScoreOneBall() {
        var answers = List.of(new Answer()
                .setText("answer")
                .setRight(true));
        var question = new Question()
                .setText("dialog.question")
                .setAnswers(answers);
        Quiz quiz = new Quiz()
                .setName("User")
                .setQuestions(List.of(question));

        when(questionFormatter.format(question)).thenReturn("formattedQuestion");
        when(userInterface.getLine()).thenReturn("1");
        when(answerValidator.validate(answers, "1")).thenReturn(true);
        when(messageSource.getMessage("dialog.score", new Object[] {1, "User"}, LocaleHolder.getLocale()))
                .thenReturn("Score: 1");

        tester.show(quiz);

        verify(userInterface).outLine("formattedQuestion");
        verify(userInterface).outLine("\nScore: 1");
    }

    @DisplayName("Неправильные ответы")
    @Test
    void shouldShowScoreZeroBall() {
        var answers = List.of(new Answer()
                .setText("answer")
                .setRight(false));
        var question = new Question()
                .setText("dialog.question")
                .setAnswers(answers);
        Quiz quiz = new Quiz()
                .setName("User")
                .setQuestions(List.of(question));

        when(questionFormatter.format(question)).thenReturn("formattedQuestion");
        when(userInterface.getLine()).thenReturn("1");
        when(answerValidator.validate(answers, "1")).thenReturn(false);
        when(messageSource.getMessage("dialog.score", new Object[] {0, "User"}, LocaleHolder.getLocale()))
                .thenReturn("Score: 0");

        tester.show(quiz);

        verify(userInterface).outLine("formattedQuestion");
        verify(userInterface).outLine("\nScore: 0");
    }

    @DisplayName("Ошибка при валидации ответа")
    @Test
    void shouldThrowAnswerValidatorException() {
        var answers = List.of(new Answer()
                .setText("answer")
                .setRight(false));
        var question = new Question()
                .setText("dialog.question")
                .setAnswers(answers);
        var quiz = new Quiz()
                .setName("User")
                .setQuestions(List.of(question));

        when(questionFormatter.format(question)).thenReturn("formattedQuestion");
        when(userInterface.getLine()).thenReturn("1");
        when(answerValidator.validate(answers, "1")).thenThrow(new AnswerValidatorException("Ошибка"));
        when(messageSource.getMessage("dialog.score", new Object[] {0, "User"}, LocaleHolder.getLocale()))
                .thenReturn("Score: 0");

        tester.show(quiz);

        verify(userInterface).outLine("formattedQuestion");
        verify(userInterface).outLine("Ошибка");
        verify(userInterface).outLine("\nScore: 0");
    }
}