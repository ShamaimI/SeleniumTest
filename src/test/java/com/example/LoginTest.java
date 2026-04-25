package com.example;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class LoginTest {

    @Test
    void test_login_with_incorrect_credentials() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        WebDriver driver = new ChromeDriver(options);

        driver.navigate().to("http://103.139.122.250:4000/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement emailField = wait.until(
            ExpectedConditions.presenceOfElementLocated(By.name("email"))
        );

        emailField.sendKeys("wrong@email.com");
        driver.findElement(By.name("password")).sendKeys("wrongpassword");
        driver.findElement(By.id("m_login_signin_submit")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        String errorText = driver.findElement(
            By.xpath("/html/body/div/div/div[1]/div/div/div/div[2]/form/div[1]")
        ).getText();

        assert(errorText.contains("Incorrect email or password"));

        driver.quit();
    }
}