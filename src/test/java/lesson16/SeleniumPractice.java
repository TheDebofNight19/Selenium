package lesson16;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.Set;


public class SeleniumPractice {

    private static final Logger LOG = LoggerFactory.getLogger(SeleniumPractice.class);
    private WebDriver webDriver;

    @BeforeClass
    public void initDriver() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
    }

    /**
     * 1.На странице “Button” нажать на кнопку “CLICK ME!”, проверить,
     * что появились текст «Excellent!» и кнопка “CLICK ME TOO!”.
     * 2.Нажать на кнопку “CLICK ME TOO!”. Проверить, что появилась ссылка с текстом “Great! Return to menu”
     * и нажать на неё.
     */

    @Test
    public void testPage() {
        WebElement button;
        WebElement element;
        webDriver.get("https://savkk.github.io/selenium-practice/");
        (webDriver.findElement(new By.ById("button"))).click();
        (webDriver.findElement(new By.ById("first"))).click();
        element = webDriver.findElement(By.xpath("//label[text() = 'Excellent!' ]"));
        button = webDriver.findElement(By.xpath("//*[@type=\"button\" and @value='Click me too!']"));
        Assert.assertEquals("Excellent!", element.getText());
        (button).click();
        (webDriver.findElement(By.xpath("//a[text() = \"Great! Return to menu\"]"))).click();

    }

    /**
     * На странице “Checkboxes”выбрать один или несколько из представленных чек-боксов и нажать
     * на кнопку “GET RESULTS” под ними. Проверить, что появился текст,
     * соответствующий атрибуту value из выделенных чек-боксов.
     * На той же странице выбрать любую радио кнопку и нажать кнопку “GET RESULTS”,
     * находящуюся под ними. Проверить, что появился текст, соответствующий значению атрибута value,
     * выбеленной кнопки.
     * Проверить, что появилась ссылка с текстом “Great! Return to menu”и нажать на неё.
     */

    @Test
    public void testCheckBoxes() {

        WebElement element;
        webDriver.get("https://savkk.github.io/selenium-practice/");
        (webDriver.findElement(new By.ById("checkbox"))).click();
        WebElement checkbox1 = webDriver.findElement(By.xpath("//*[./span[contains(text(), 'One')]]" +
                "//*[@type='checkbox']"));
        checkbox1.click();
        WebElement checkbox2 = webDriver.findElement(By.xpath("//*[./span[contains(text(), 'Three')]]" +
                "//*[@type='checkbox']"));
        checkbox2.click();
        (webDriver.findElement(By.xpath("//*[@id=\"go\"]"))).click();
        element = webDriver.findElement(By.xpath("//*[@id=\"result\"]"));
        Assert.assertTrue(element.getText().contains(checkbox1.getText()));
        Assert.assertTrue(element.getText().contains(checkbox2.getText()));
        WebElement radioButton = webDriver.findElement(By.xpath("//*[./span[contains(text(), 'One')]]" +
                "//*[@type='radio']"));
        radioButton.click();
        (webDriver.findElement(By.xpath("//button[@id = 'radio_go']"))).click();
        element = webDriver.findElement(By.xpath("//*[@id=\"radio_result\"]"));
        Assert.assertTrue(element.getText().contains(radioButton.getText()));
        Set<Cookie> cookies = webDriver.manage().getCookies();
        (webDriver.findElement(By.xpath("//a[text()=\"Great! Return to menu\"]"))).click();
    }

    @Test
    public void cookieTest(){
        Set<Cookie> cookies = webDriver.manage().getCookies();
        ArrayList<String> cookieList= new ArrayList<>();
        cookieList.add("checkboxes");
        cookieList.add("button");
        SoftAssert softAssert = new SoftAssert();
        for(Cookie cookie: cookies){
            for (String s : cookieList) {
                softAssert.assertTrue(cookie.getName().contains(s));
            }
            Assert.assertTrue(cookie.getValue().contains("done"));
            LOG.info(cookie.getValue());
            LOG.info(cookie.getName());
        }
    }
        @AfterClass
        public void closeDriver(){
            webDriver.quit();
        }
    }
