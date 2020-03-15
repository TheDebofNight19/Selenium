package lesson18;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class Additional {

    private static WebDriver webDriver;
    private static final Logger LOG = LoggerFactory.getLogger(Additional.class);

    @BeforeClass
    public void initDriver() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
    }

    /**
     * 1.На странице “Prompt, Alert and Confirm” получить, а затем ввести полученный пароль
     * в диалоговое окно ввода. Проверить, что после успешного ввода пароля
     * появляется сообщение «Great!»и кнопка «Return to Menu».
     * Перейти на главную страницу нажав на кнопку “Return to Menu”.
     */

    @Test
    public void testAlert() {
        webDriver.get("https://savkk.github.io/selenium-practice/");
        (webDriver.findElement(new By.ByCssSelector("#alerts"))).click();
       (webDriver.findElement(By.xpath("//button[@class='get' " +
                "and text()='Get password']"))).click();
       Alert alert = webDriver.switchTo().alert();
        String code = (alert.getText()).replaceAll("Your password: ", "");
        alert.accept();
        webDriver.switchTo().parentFrame();
        (webDriver.findElement(By.xpath("//button[@class='set' " +
                "and text()='Enter password']"))).click();
       webDriver.switchTo().alert();
        alert.sendKeys(code);
        alert.accept();
        WebElement element = webDriver.findElement(By.xpath("//div[@id=\"content\"]" +
                "//label[text()='Great!']"));
        Assert.assertEquals("Great!", element.getText());
        (webDriver.findElement(By.xpath("//button[@class='return' " +
                "and text()='Return to menu']"))).click();
        webDriver.switchTo().alert();
        alert.accept();

    }

    /**
     * Написать негативный тест на первое задание.
     * Проверяем, чт о в случае ввода неверного пароля не появляется
     * кнопка "Return to menu", ловим NoSuchElementException
     */

    @Test
    public void testAlertNegative() {
        webDriver.get("https://savkk.github.io/selenium-practice/");
        (webDriver.findElement(new By.ByCssSelector("#alerts"))).click();
        (webDriver.findElement(By.xpath("//button[@class='get' " +
                "and text()='Get password']"))).click();
        Alert alert = webDriver.switchTo().alert();
        LOG.info(alert.getText());
        alert.accept();
        webDriver.switchTo().parentFrame();
        (webDriver.findElement(By.xpath("//button[@class='set' " +
                "and text()='Enter password']"))).click();
        webDriver.switchTo().alert();
        LOG.info(alert.getText());
        alert.sendKeys("Xxxx0000111");
        alert.accept();
        try{(webDriver.findElement(By.xpath("//button[@class='return' " +
                "and text()='Return to menu']"))).click();
        }catch(NoSuchElementException e){
            LOG.info("Wrong password!");
            webDriver.get("https://savkk.github.io/selenium-practice/");
        }
    }

    /**
     * На странице Table удалить и добавить несколько записей в таблицу.
     * Проверить, что после выполнения этих действий появляется кнопка “Return to Menu”.
     * Перейти на главную страницу нажав на кнопку “Return to Menu”.
     */

    @Test
    public void testTable(){
        webDriver.get("https://savkk.github.io/selenium-practice/");
        (webDriver.findElement(new By.ByCssSelector("#table"))).click();
        List<WebElement> elements = webDriver.findElements(By.xpath("//table//input[@type='checkbox']"));
        for (int i = 0; i < elements.size(); i++) {
            if(i % 2 != 0){
                elements.get(i).click();
            }
        }
        webDriver.findElement(By.xpath("//input[@type='button' and @value='Delete']")).click();
        List<WebElement> inputs = webDriver.findElements(By.xpath("//div//input[@type = 'text']"));
        for(int i =0; i < inputs.size(); i++){
            for (int j = 0; j < inputs.size(); j++) {
                inputs.get(j).sendKeys("RU");
            }
            webDriver.findElement(By.xpath("//input[@type='button' and @value='Add']")).click();
        }
        webDriver.findElement(By.xpath("//label[@id='back']//a[text()]")).click();

        }
    }