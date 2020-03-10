package com.github.TheDebofNight19.lesson15;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class YandexTest {

        private WebDriver webDriver;

        @BeforeSuite
        public void initDriver(){
            System.setProperty("webdriver.gecko.driver","C:\\Geckodriver\\geckodriver.exe");
            webDriver = new FirefoxDriver();
        }


        @Test
        public void testPage(){
            webDriver.get("https://yandex.ru");
            WebElement input = webDriver.findElement(By.name("text"));
            input.sendKeys("Руддщ цщкдв!"); //вбили Hello world
            input.sendKeys(Keys.ENTER); //нажали Enter
            webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            WebElement spanElement = webDriver.findElement(By.className("misspell__message"));
            String s = spanElement.getText();
            Assert.assertEquals(s, "Исправлена раскладка клавиатуры с «Руддщ цщкдв!»");

        }

        @AfterSuite
        public void closeDriver(){
            webDriver.quit();
        }
}
