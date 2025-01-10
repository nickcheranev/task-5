package ru.diasoft.ncheranev.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.diasoft.ncheranev.otus.model.Answer;
import ru.diasoft.ncheranev.otus.model.Question;
import ru.diasoft.ncheranev.otus.util.LocaleHolder;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

/**
 * Форматер для вывода в текстовую консоль
 */
@Service
@RequiredArgsConstructor
public class TextQuestionFormatter implements QuestionFormatter {
    @Autowired
    private final MessageSource messageSource;

    @Override
    public String format(Question question) {
        var locale = LocaleHolder.getLocale();
        var questionLabel = messageSource.getMessage("dialog.question", null, locale);
        var answersLabel = messageSource.getMessage("dialog.answers", null, locale);
        return String.format("\n%s\n  %s\n%s\n  %s",
                questionLabel,
                question.getText(),
                answersLabel,
                formatAnswers(question.getAnswers()));
    }

    private String formatAnswers(List<Answer> answers) {
        AtomicInteger n = new AtomicInteger(1);
        return isNull(answers) ? null : answers.stream()
                .map(answer -> n.getAndIncrement() + ". " + answer.getText())
                .collect(Collectors.joining("\n  "));
    }
}