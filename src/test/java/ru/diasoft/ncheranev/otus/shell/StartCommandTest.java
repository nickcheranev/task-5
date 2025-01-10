package ru.diasoft.ncheranev.otus.shell;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.diasoft.ncheranev.otus.service.QuizRunner;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StartCommandTest {
    @Mock
    private QuizRunner quizRunner;
    @InjectMocks
    private StartCommand sut;

    @Test
    void start() {
        assertThatNoException().isThrownBy(() -> sut.start());

        verify(quizRunner).run();
    }
}