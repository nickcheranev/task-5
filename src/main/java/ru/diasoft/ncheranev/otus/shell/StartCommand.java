package ru.diasoft.ncheranev.otus.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.diasoft.ncheranev.otus.service.QuizRunner;

/**
 * Команда Shell "Запустить викторину"
 */
@ShellComponent
@RequiredArgsConstructor
public class StartCommand {
    private final QuizRunner quizRunner;

    @ShellMethod("Start quiz")
    public void start() {
        quizRunner.run();
    }
}
