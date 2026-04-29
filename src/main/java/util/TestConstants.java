package util;

import config.ConfProperties;

public class TestConstants {

    public static final String USERNAME = ConfProperties.getProperty("username");
    public static final String PASSWORD = ConfProperties.getProperty("password");
    public static final String ISSUE_NAME = "Тестовая задача";
    public static final String TASK_NAME = "TestSeleniumATHomework";
    public static final String TASK_STATUS_TODO = "СДЕЛАТЬ";
    public static final String EXPECTED_VERSION = "Version 2.0";
    public static final String BUG_NAME = "Тестовый баг-репорт";
    public static final String BUG_DESCRIPTION = "Описание бага";
    public static final String ENVIRONMENT = "Google Chrome";
    public static final String SEVERITY = "Trivial";
    public static final String STATUS_IN_PROGRESS = "В РАБОТЕ";
    public static final String STATUS_DONE = "ГОТОВО";
}

