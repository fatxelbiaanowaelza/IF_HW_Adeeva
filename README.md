### UI Automation Project (Selenide + JUnit5 + Allure)
### Обзор

Данный проект содержит автоматизированные UI тесты для веб-приложения: https://edujira.ifellow.ru               

Фреймворк разработан с использованием Java 25, Selenide, JUnit 5, Maven и Allure Reports


### Запуск

1. Запуск тестов  
   `mvn clean test`

2. Сборка Allure отчета  
   `mvn allure:report`

3. Просмотр Allure отчета  
   `mvn allure:serve`

### Требования

- Java 25
- Maven
- Google Chrome

### Настройка

base url хранится в файле `config.properties` в resources  
учетные данные (логин и пароль) берутся из `config.properties`  
настройка браузера и параметров Selenide выполняется в классе `WebHooks`  
там задаются браузер, таймауты, размер окна и подключается Allure listener

### Что тестируется

- авторизация пользователя
- открытие проекта Test
- проверка увеличения счетчика задач при создании новой задачи
- проверка статуса задачи TestSeleniumATHomework
- создание бага
- перевод задачи по статусам до состояния Готово  