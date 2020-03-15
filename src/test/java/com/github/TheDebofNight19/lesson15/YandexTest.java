package com.github.TheDebofNight19.lesson15;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class YandexTest {

        private static final Logger LOG = LoggerFactory.getLogger(YandexTest.class);

        private WebDriver webDriver;

    @BeforeClass
    public void initDriver() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
    }
        @Test
        public void testPage(){
            webDriver.get("https://yandex.ru");
            webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            WebElement input1 = webDriver.findElement(By.name("text"));
            input1.sendKeys("Руддщ цщкдв!"); //вбили Hello world
            input1.submit(); //нажали Enter
            String s = webDriver.getTitle();
            WebElement input2 = webDriver.findElement(By.name("text"));
            String t = input2.getAttribute("value");
            Assert.assertTrue(s.contains("Hello world! — Яндекс"));
            Assert.assertTrue(t.contains("Hello world!"));
            LOG.info(s);
            LOG.info(t);

        }

        @AfterClass
        public void closeDriver(){
            webDriver.quit();
        }
}
