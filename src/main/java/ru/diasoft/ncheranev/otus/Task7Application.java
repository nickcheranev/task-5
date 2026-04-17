package ru.diasoft.ncheranev.otus;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import ru.diasoft.ncheranev.otus.util.LocaleHolder;

@SpringBootApplication
@RequiredArgsConstructor
@EnableAspectJAutoProxy
public class Task7Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Task7Application.class, args);
	}

	@Override
	public void run(String... args) {
		LocaleHolder.setLocale((args.length > 0) ? args[0] : "");
	}
}
