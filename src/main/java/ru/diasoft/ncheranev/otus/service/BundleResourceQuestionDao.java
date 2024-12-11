package ru.diasoft.ncheranev.otus.service;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.diasoft.ncheranev.otus.model.Question;
import ru.diasoft.ncheranev.otus.util.LocaleHolder;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * DAO (загрузка) вопросов викторины из локализованных ресурсов
 */
@Service
@RequiredArgsConstructor
@ConditionalOnProperty(value = "task.dao", havingValue = "bundle", matchIfMissing = true)
@Slf4j
public class BundleResourceQuestionDao implements QuestionDao {
    @Autowired
    private final MessageSource messageSource;

    /**
     * Чтение вопросов викторины из файла ресурсов
     *
     * @return список вопросов
     */
    @Override
    public List<Question> read() {
        try {
            log.info("Load data from localized bundle");
            var questions = messageSource.getMessage("data.questions", null, LocaleHolder.getLocale()).replaceAll("\\|", "\n");
            var inputStream = new ByteArrayInputStream(questions.getBytes());
            var streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            return new CsvToBeanBuilder<Question>(streamReader)
                    .withType(Question.class)
                    .withQuoteChar('\'')
                    .build()
                    .parse();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
