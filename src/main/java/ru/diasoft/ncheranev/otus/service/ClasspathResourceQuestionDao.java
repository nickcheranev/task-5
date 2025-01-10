package ru.diasoft.ncheranev.otus.service;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import ru.diasoft.ncheranev.otus.config.TaskConfig;
import ru.diasoft.ncheranev.otus.model.Question;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * DAO (загрузка) вопросов викторины из ресурсов приложения
 */
@Service
@RequiredArgsConstructor
@ConditionalOnProperty(value = "task.dao", havingValue = "file")
@Slf4j
public class ClasspathResourceQuestionDao implements QuestionDao {
    private final TaskConfig taskConfig;

    /**
     * Чтение вопросов викторины из файла ресурсов
     *
     * @return список вопросов
     */
    @Override
    public List<Question> read() {
        try {
            log.debug("Load data from classpath file {}", taskConfig.getFileName());
            var classPathResource = new ClassPathResource(taskConfig.getFileName());
            var streamReader = new InputStreamReader(classPathResource.getInputStream(), StandardCharsets.UTF_8);
            return new CsvToBeanBuilder<Question>(streamReader)
                    .withType(Question.class)
                    .build()
                    .parse();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
