package com.github.TheDebofNight19.lesson15;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class YandexTest {

        private static final Logger LOG = LoggerFactory.getLogger(YandexTest.class);

        private WebDriver webDriver;

        @BeforeSuite
        public void initDriver(){
            System.setProperty("webdriver.chrome.driver","C:\\Users\\ЮиЖе\\IdeaProjects\\SeleniumDemo1" +
                    "\\src\\test\\java\\resources\\suites\\chromedriver.exe");
            webDriver = new ChromeDriver();
        }

        @Test
        public void testPage(){
            webDriver.get("https://yandex.ru");
            WebElement input1 = webDriver.findElement(By.name("text"));
            input1.sendKeys("Руддщ цщкдв!"); //вбили Hello world
            input1.sendKeys(Keys.ENTER); //нажали Enter
            String s = webDriver.getTitle();
            WebElement input2 = webDriver.findElement(By.name("text"));
            String t = input2.getAttribute("value");
            Assert.assertTrue(s.contains("Hello world! — Яндекс"));
            Assert.assertTrue(t.contains("Hello world!"));
            LOG.info(s);
            LOG.info(t);

        }

        @AfterSuite
        public void closeDriver(){
            webDriver.quit();
        }
}
