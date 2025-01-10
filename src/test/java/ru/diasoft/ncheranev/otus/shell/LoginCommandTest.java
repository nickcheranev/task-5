package ru.diasoft.ncheranev.otus.shell;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.diasoft.ncheranev.otus.service.UserNameRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LoginCommandTest {
    @Mock
    private UserNameRepository userNameRepository;
    @InjectMocks
    private LoginCommand sut;

    @Test
    void login() {
        var message = sut.login("User");

        assertThat(message).isEqualTo("Привет, User");

        verify(userNameRepository).setUserName("User");
    }
}