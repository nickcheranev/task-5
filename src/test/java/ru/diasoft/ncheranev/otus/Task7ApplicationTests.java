package ru.diasoft.ncheranev.otus;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = "spring.shell.interactive.enabled=false")
class Task7ApplicationTests {

	@Test
	void contextLoads() {
	}
}
