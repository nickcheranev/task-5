package ru.diasoft.ncheranev.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.diasoft.ncheranev.otus.model.Quiz;
import ru.diasoft.ncheranev.otus.util.LocaleHolder;

import static org.apache.commons.lang3.StringUtils.isBlank;

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
    private final UserNameRepository userNameRepository;

    @Override
    public void run() {
        var locale = LocaleHolder.getLocale();
        var savedUserName = userNameRepository.getUserName();
        String name;
        if (isBlank(savedUserName)) {
            userInterface.outLine(messageSource.getMessage("dialog.enterYourName", null, locale));
            name = userInterface.getName();
            userNameRepository.setUserName(name);
        } else {
            name = savedUserName;
        }
        userInterface.outLine(messageSource.getMessage("dialog.greetings", new Object[] {name}, locale));

        var questions = questionDao.read();

        quizShow.show(new Quiz()
                .setQuestions(questions));
    }
}