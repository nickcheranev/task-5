package ru.diasoft.ncheranev.otus.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.diasoft.ncheranev.otus.service.UserNameRepository;

/**
 * Команда Shell "Ввести мя пользователя"
 */
@ShellComponent
@RequiredArgsConstructor
public class LoginCommand {
    private final UserNameRepository userNameRepository;

    @ShellMethod("Enter your name")
    public String login(@ShellOption(defaultValue = "Пользователь без имени") String arg) {
        userNameRepository.setUserName(arg);
        return "Привет, " + arg;
    }
}
