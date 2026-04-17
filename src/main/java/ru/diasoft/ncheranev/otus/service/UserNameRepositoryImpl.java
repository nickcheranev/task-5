package ru.diasoft.ncheranev.otus.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

@Repository
@Getter
@Setter
public class UserNameRepositoryImpl implements UserNameRepository {
    private String userName;
}
