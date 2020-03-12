package lesson16;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;



public class SeleniumPractice {

    private static final Logger LOG = LoggerFactory.getLogger(SeleniumPractice.class);

    public static void buttonClick(WebElement button) {
        button.click();
    }

    private static WebElement button;
    private static WebElement element;

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
        webDriver.get("https://savkk.github.io/selenium-practice/");
        buttonClick(webDriver.findElement(new By.ById("button")));
        buttonClick(webDriver.findElement(new By.ById("first")));
        element = webDriver.findElement(By.xpath("//label[text() = 'Excellent!' ]"));
        button = webDriver.findElement(By.xpath("//*[@type=\"button\" and @value='Click me too!']"));
        Assert.assertEquals("Excellent!", element.getText());
        buttonClick(button);
        button = webDriver.findElement(By.xpath("//a[text() = \"Great! Return to menu\"]"));
        buttonClick(button);
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
        webDriver.get("https://savkk.github.io/selenium-practice/");
        buttonClick(webDriver.findElement(new By.ById("checkbox")));
        WebElement checkbox1 = webDriver.findElement(By.xpath("//*[@id=\"one\"]"));
        checkbox1.click();
        WebElement checkbox2 = webDriver.findElement(By.xpath("//*[@id=\"three\"]"));
        checkbox2.click();
        buttonClick(webDriver.findElement(By.xpath("//*[@id=\"go\"]")));
        element = webDriver.findElement(By.xpath("//*[@id=\"result\"]"));
        Assert.assertTrue(element.getText().contains(checkbox1.getText()));
        Assert.assertTrue(element.getText().contains(checkbox2.getText()));
        WebElement radioButton = webDriver.findElement(By.xpath("//*[@id=\"radio_three\"]"));
        radioButton.click();
        buttonClick(webDriver.findElement(By.xpath("//*[@id=\"radio_go\"]")));
        element = webDriver.findElement(By.xpath("//*[@id=\"radio_result\"]"));
        Assert.assertTrue(element.getText().contains(radioButton.getText()));
        buttonClick(webDriver.findElement(By.xpath("//a[text()=\"Great! Return to menu\"]")));
    }
        @AfterClass
        public void closeDriver(){
            webDriver.quit();
        }
    }
