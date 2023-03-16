package org.example;


import com.codeborne.selenide.Condition;

import com.codeborne.selenide.logevents.SelenideLogger;

import io.qameta.allure.selenide.AllureSelenide;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class LambdaTest {

    public static final String REPOSITORY = "eroshenkoam/allure-example";
    public static final int ISSUE = 80;

    @Test
    void testSelenide() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу", () -> {
            open("https://github.com");
        });
        step("Поиск " + REPOSITORY, () -> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(REPOSITORY);
            $(".header-search-input").submit();
            $(linkText(REPOSITORY)).click();
        });
        step("Проверка наличия" + ISSUE + "в выдаче", () -> {
            $("#issues-tab").click();
            $(withText("#" + ISSUE)).should(Condition.exist);
        });
    }

    @Test
    void testAnnotatedSteps() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps steps = new WebSteps();

        steps.openMainPage();
        steps.sreachForRepo(REPOSITORY);
        steps.sreachIssue(ISSUE);

    }
}
