package org.example;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;


public class WebSteps {

    @Step("Открываем главную страницу")
    public void openMainPage() {
        open("https://github.com");
    }
    
    @Step("Поиск {repo}")
    public void sreachForRepo(String repo) {
        $(".header-search-input").click();
        $(".header-search-input").sendKeys(repo);
        $(".header-search-input").submit();
        $(linkText(repo)).click();
    }
    
    @Step("Поиск {issue}")
    public void sreachIssue(int issue) {

        $("#issues-tab").click();
        $(withText("#" + issue)).should(Condition.exist);
    }

    @Attachment(value = "Srcreenshot", type ="image/png", fileExtension = "png")
    public byte[] attachScreen() {
        return ((TakesScreenshot)WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
