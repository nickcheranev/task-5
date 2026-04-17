package ru.diasoft.ncheranev.otus.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.diasoft.ncheranev.otus.util.LocaleHolder;

/**
 * Команда Shell "Установить язык интерфейса"
 */
@ShellComponent
@RequiredArgsConstructor
public class LocaleCommand {

    @ShellMethod("Select language")
    public String locale(@ShellOption(defaultValue = "RU") String locale) {
        LocaleHolder.setLocale(locale);
        return "Выбран язык: " + locale;
    }
}
