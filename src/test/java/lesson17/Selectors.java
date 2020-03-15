package lesson17;

import io.github.bonigarcia.wdm.WebDriverManager;

import lesson16.SeleniumPractice;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Set;

public class Selectors {

    private static WebDriver webDriver;
    private static final Logger LOG = LoggerFactory.getLogger(Selectors.class);

    @BeforeClass
    public void initDriver() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
    }

    /**
     * 1.На странице “Select” выбрать одно значение в выпадающем списка и несколько в списке,
     * поддерживающем множественный выбор.
     * 2.Нажать на кнопку “GET RESULT”. Проверить, что на странице отобразились выбранные значения
     * и ссылка с текстом “Great! Return to menu”и нажать на неё.
     */


    @Test
    public void testPage() {
        webDriver.get("https://savkk.github.io/selenium-practice/");
        (webDriver.findElement(new By.ByCssSelector("#select"))).click();
        WebElement heroElement = webDriver.findElement(By.name("hero"));
        Select heroSelect =  new Select(heroElement);
        heroSelect.selectByIndex(2);
        WebElement languageElement = webDriver.findElement(By.name("languages"));
        Select languageSelect = new Select(languageElement);
        if(languageSelect.isMultiple()){
            languageSelect.selectByIndex(0);
            languageSelect.selectByIndex(1);
            languageSelect.selectByIndex(3);
        }
        (webDriver.findElement(new By.ById("go"))).click();
        Set<Cookie> cookies = webDriver.manage().getCookies();
        for(Cookie cookie: cookies){
            Assert.assertEquals("done", cookie.getValue());
            Assert.assertEquals("select", cookie.getName());
            LOG.info(cookie.getValue());
            LOG.info(cookie.getName());
        }
        (webDriver.findElement(By.xpath("//div[@id='content']//" +
                "select[@class='u-full-width']/following-sibling::label[@id='back']//a"))).click();
    }

    /**
     * 3.На странице “Form” заполнить все обязательные поля и нажать на кнопку «ОТПРАВИТЬ».
     * 4.Проверить, что появилась ссылка с текстом “Great! Return to menu”и нажать на неё.

     */

    @Test
    public void testForm(){
         webDriver.get("https://savkk.github.io/selenium-practice/");
         (webDriver.findElement(new By.ById("form"))).click();
         WebElement inputFirstName = webDriver.findElement(By.xpath("//label[text()=\"First Name:\"]" +
                 "/following-sibling::input"));
         inputFirstName.sendKeys("Daenerys");
         WebElement inputLastName = webDriver.findElement(By.xpath("//label[text()=\"Last Name:\"]" +
                "/following-sibling::input"));
         inputLastName.sendKeys("Targaryen");
         WebElement inputEmail = webDriver.findElement(By.xpath("//label[text()=\"Email:\"]" +
                "/following-sibling::input"));
         inputEmail.sendKeys("dany@dracarys.ws");
        WebElement inputAddress = webDriver.findElement(By.xpath("//label[text()=\"Address:\"]" +
                "/following-sibling::input"));
        inputAddress.sendKeys("Dragonstone");
        WebElement inputAvatar = webDriver.findElement(By.xpath("//label[text()=\"Avatar:\"]" +
                "/following-sibling::input"));
        inputAvatar.sendKeys("C:\\Users\\ЮиЖе\\Desktop\\original.jpg");
        WebElement inputText = webDriver.findElement(By.xpath("//label[text()=" +
                "\"Tell me something about yourself\"]" +
                "/following-sibling::textarea"));
        inputText.sendKeys("Where are my dragons?");
       (webDriver.findElement(By.xpath("//div/following-sibling::input"))).click();
        Set<Cookie> cookies = webDriver.manage().getCookies();
        for(Cookie cookie: cookies){
            Assert.assertEquals("done", cookie.getValue());
            Assert.assertEquals("form", cookie.getName());
            LOG.info(cookie.getValue());
            LOG.info(cookie.getName());
        }
       (webDriver.findElement(By.xpath("//div//form[@id='testform']" +
               "/following-sibling::label//a"))).click();
    }

    /**
     *5.На странице “IFrame”ввести код, выведенный на этой странице,в поле ввода и нажать на кнопку «VERIFY».
     * 6.Проверить, что появилась ссылка с текстом “Great! Return to menu”и нажать на неё.
     */
    @Test
    public void testIframe(){
        webDriver.get("https://savkk.github.io/selenium-practice/");
        (webDriver.findElement(new By.ById("iframe"))).click();
        WebElement iFrame = webDriver.findElements(By.tagName("iframe")).get(0);
        webDriver.switchTo().frame(iFrame);
        WebElement inputAddress = webDriver.findElement(By.xpath("//label[@id=\"code\"]"));
        String code = (inputAddress.getText()).replaceAll("Your code is: ", "");
        webDriver.switchTo().defaultContent();
        WebElement inputCode = webDriver.findElement(By.xpath("//input[@name='code']"));
        inputCode.sendKeys(code);
        (webDriver.findElement(By.xpath("//input[@name=\"ok\"and @type=\"button\"]"))).click();
        Set<Cookie> cookies = webDriver.manage().getCookies();
        for(Cookie cookie: cookies){
            Assert.assertEquals("done", cookie.getValue());
            Assert.assertEquals("iframe", cookie.getName());
            LOG.info(cookie.getValue());
            LOG.info(cookie.getName());
        }
        (webDriver.findElement(By.xpath("//input[@name='ok']/following-sibling::label//a"))).click();

    }
    @AfterClass
    public void closeDriver(){
        webDriver.quit();
    }
}



