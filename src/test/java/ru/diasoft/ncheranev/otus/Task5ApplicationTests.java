package ru.diasoft.ncheranev.otus;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import ru.diasoft.ncheranev.otus.service.UserInterface;

@SpringBootTest
class Task5ApplicationTests {
	@MockitoBean
	private UserInterface userInterface;

	@Test
	void contextLoads() {
	}
}
