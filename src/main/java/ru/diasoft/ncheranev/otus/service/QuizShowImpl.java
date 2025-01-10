package ru.diasoft.ncheranev.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.diasoft.ncheranev.otus.model.Quiz;
import ru.diasoft.ncheranev.otus.util.AnswerValidatorException;
import ru.diasoft.ncheranev.otus.util.LocaleHolder;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Демонстратор викторины
 */
@Service
@RequiredArgsConstructor
public class QuizShowImpl implements QuizShow {
    private final QuestionFormatter questionFormatter;
    private final AnswerValidator answerValidator;
    private final UserInterface userInterface;
    private final MessageSource messageSource;
    private final UserNameRepository userNameRepository;

    @Override
    public void show(Quiz quiz) {
        var balls = new AtomicInteger(0);
        quiz.getQuestions()
                .forEach(question -> {
                    userInterface.outLine(questionFormatter.format(question));
                    var userData = userInterface.getLine();

                    try {
                        var answerStatus = answerValidator.validate(question.getAnswers(), userData);
                        if (answerStatus) {
                            balls.getAndIncrement();
                        }
                    } catch (AnswerValidatorException e) {
                        userInterface.outLine(e.getMessage());
                    }
                });

        var dialogScore = "\n" + messageSource.getMessage("dialog.score", new Object[] {balls.get(), userNameRepository.getUserName()},
                LocaleHolder.getLocale());
        userInterface.outLine(dialogScore);
    }
}
