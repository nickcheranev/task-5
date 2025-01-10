**Домашнее задание 5. (7)**

Перенести приложение для тестирования студентов на Spring Boot

Цель: использовать возможности Spring Boot, чтобы разрабатывать современные приложения, так, как их сейчас и разрабатывают. Результат: Production-ready приложение на Spring Boot

Это домашнее задание выполняется на основе предыдущего.

1) Создать проект, используя Spring Boot Initializr (https://start.spring.io)
2) Перенести приложение проведения опросов из прошлого домашнего задания.
3) Перенести все свойства в application.yml
4) Локализовать выводимые сообщения и вопросы (в CSV-файле). MessageSource должен быть из автоконфигурации Spring Boot.
5) Сделать собственный баннер для приложения.
6) Перенести тесты и использовать spring-boot-test-starter для тестирования

Опционально:

1) Использовать ANSI-цвета для баннера.
2) Если Ваш язык отличается от русского и английского - локализовать в нём.
3) Коммитить wrapper или нет в репозиторий - решать Вам.

Задание сдаётся в виде ссылки на pull-request в чат с преподавателем. Вопросы можно задавать в чате, но для оперативности рекомендуем Slack.

Написанное приложение будет использоваться в ДЗ №4 (к занятию №5). Данное задание засчитывает ДЗ №1 (к занятию №1) и ДЗ №2 (к занятию №2). Если Вы хотите засчитать, то обязательно пришлите ссылку в чат соответствующего предыдущего занятия.

**Домашнее задание 7.**

Перевести приложение для проведения опросов на Spring Shell

Цель:

Цель: После выполнения ДЗ вы сможете использовать Spring Shell, чтобы писать интерфейс приложения без Web. Результат: Приложение на Spring Shell

Домашнее задание выполняется на основе предыдущего.

Необходимо:

Подключить Spring Shell, используя spring-starter.
Написать набор команд, позволяющий проводить опрос.
Написать Unit-тесты с помощью spring-boot-starter-test, учесть, что Spring Shell в тестах нужно отключить.
Набор команд зависит только от Вашего желания. Вы можете сделать одну команду, запускающую Ваш Main, а можете построить полноценный интерфейс на Spring Shell.

Локализовывать команды Spring Shell НЕ НУЖНО (хотя можно, но это долго и непросто).

Задание сдаётся в виде ссылки на pull-request в чат с преподавателем. Вопросы можно задавать в чате, но для оперативности рекомендуем Slack.

Данное задание НЕ засчитывает предыдущие!

Это домашнее задание больше нигде не будет использоваться. Но интерфейс Spring Shell мы будет использовать в дальнейшем.

**Также реализован LoggingAspect для логирования сервисов приложения.**