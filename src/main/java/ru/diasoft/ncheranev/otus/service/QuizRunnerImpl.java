package ru.diasoft.ncheranev.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.diasoft.ncheranev.otus.model.Quiz;
import ru.diasoft.ncheranev.otus.util.LocaleHolder;

/**
 * Реализация проигрывателя викторины
 */
@Service
@RequiredArgsConstructor
public class QuizRunnerImpl implements QuizRunner {
    private final QuestionDao questionDao;
    private final QuizShow quizShow;
    private final UserInterface userInterface;
    private final MessageSource messageSource;

    @Override
    public void run() {
        var locale = LocaleHolder.getLocale();
        userInterface.outLine(messageSource.getMessage("dialog.enterYourName", null, locale));
        var name = userInterface.getName();
        userInterface.outLine(messageSource.getMessage("dialog.greetings", new Object[] {name}, locale));

        var questions = questionDao.read();

        quizShow.show(new Quiz()
                .setName(name)
                .setQuestions(questions));
    }
}