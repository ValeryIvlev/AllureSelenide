package org.example;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.attachment;
import static io.qameta.allure.Allure.step;
import static org.example.LambdaTest.ISSUE;
import static org.example.LambdaTest.REPOSITORY;
import static org.openqa.selenium.By.linkText;

public class AttachmentsTest {


    @Test
    void testSelenide() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу", () -> {
            open("https://github.com");
            attachment("Source", webdriver().driver().source());
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
        steps.attachScreen();


    }
}

