package ru.diasoft.ncheranev.otus;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.diasoft.ncheranev.otus.service.QuizRunner;
import ru.diasoft.ncheranev.otus.util.LocaleHolder;

@SpringBootApplication
@RequiredArgsConstructor
public class Task5Application implements CommandLineRunner {

	@Autowired
	private final QuizRunner quizRunner;

	public static void main(String[] args) {
		SpringApplication.run(Task5Application.class, args);
	}

	@Override
	public void run(String... args) {
		LocaleHolder.setLocale((args.length > 0) ? args[0] : "");
		quizRunner.run();
	}
}
