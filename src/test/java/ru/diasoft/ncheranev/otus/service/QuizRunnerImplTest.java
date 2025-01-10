package ru.diasoft.ncheranev.otus.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import ru.diasoft.ncheranev.otus.model.Question;
import ru.diasoft.ncheranev.otus.model.Quiz;
import ru.diasoft.ncheranev.otus.util.LocaleHolder;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("Класс QuizRunnerImpl")
@ExtendWith(MockitoExtension.class)
class QuizRunnerImplTest {
    @Mock
    private QuestionDao questionDao;
    @Mock
    private QuizShow quizShow;
    @Mock
    private UserInterface userInterface;
    @Mock
    private MessageSource messageSource;
    @InjectMocks
    private QuizRunnerImpl tester;

    @Test
    @DisplayName("Должен выполнить запуск викторины без ошибок")
    void mustRunWithoutException() {
        LocaleHolder.setLocale("EN");
        when(userInterface.getName()).thenReturn("User");
        var questions = List.of(new Question());
        when(questionDao.read()).thenReturn(questions);
        var quiz = new Quiz().setName("User").setQuestions(questions);
        when(messageSource.getMessage("dialog.enterYourName", null, LocaleHolder.getLocale()))
                .thenReturn("Enter your name:");
        when(messageSource.getMessage("dialog.greetings", new Object[] {"User"}, LocaleHolder.getLocale()))
                .thenReturn("Hello, User! Answer our questions");

        tester.run();

        verify(userInterface).outLine("Enter your name:");
        verify(userInterface).outLine("Hello, User! Answer our questions");
        verify(quizShow).show(quiz);
    }
}