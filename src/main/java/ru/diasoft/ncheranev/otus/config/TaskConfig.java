package ru.diasoft.ncheranev.otus.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("task")
@Data
public class TaskConfig {
    private String fileName;
}
