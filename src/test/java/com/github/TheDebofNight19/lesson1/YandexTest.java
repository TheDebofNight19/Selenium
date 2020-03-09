package com.github.TheDebofNight19.lesson1;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;



public class YandexTest {
        @Test
        public void testYandex(){
            System.setProperty("webdriver.gecko.driver","C:\\Geckodriver\\geckodriver.exe");
            WebDriver webDriver = new FirefoxDriver();
            webDriver.get("https://yandex.ru/ncr");

        }
}
