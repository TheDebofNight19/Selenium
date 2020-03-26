package lesson17;

import io.github.bonigarcia.wdm.WebDriverManager;


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
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.util.ArrayList;
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
        (webDriver.findElement(By.id("go"))).click();
        String s = (webDriver.findElement(By.xpath("//label[@name='result' " +
                "and text()='Alan Mathison Turing']"))).getText();
        String t = (webDriver.findElement(By.xpath("//label[@name='result' " +
                "and text()='Java, Python, C#']"))).getText();
        Assert.assertEquals("Alan Mathison Turing", s);
        Assert.assertEquals("Java, Python, C#", t);
        (webDriver.findElement(By.id("back"))).click();
    }

    /**
     * 3.На странице “Form” заполнить все обязательные поля и нажать на кнопку «ОТПРАВИТЬ».
     * 4.Проверить, что появилась ссылка с текстом “Great! Return to menu”и нажать на неё.

     */

    @Test
    public void testForm(){
         webDriver.get("https://savkk.github.io/selenium-practice/");
         File file = new File("src\\original.jpg");
         (webDriver.findElement(By.id("form"))).click();
         WebElement inputFirstName = webDriver.findElement(By.xpath("//div[label = 'First Name:']//input"));
         inputFirstName.sendKeys("Daenerys");
         WebElement inputLastName = webDriver.findElement(By.xpath("//div[label = 'Last Name:']//input"));
         inputLastName.sendKeys("Targaryen");
         WebElement inputEmail = webDriver.findElement(By.xpath("//div[label = 'Email:']//input"));
         inputEmail.sendKeys("dany@dracarys.ws");
        WebElement inputAddress = webDriver.findElement(By.xpath("//div[label = 'Address:']//input"));
        inputAddress.sendKeys("Dragonstone");
        WebElement inputAvatar = webDriver.findElement(By.xpath("//div[label = 'Avatar:']//input"));
        inputAvatar.sendKeys(file.getAbsolutePath());
        WebElement inputText = webDriver.findElement(By.xpath("//div//textarea[@cols='50']"));
        inputText.sendKeys("Where are my dragons?");
       (webDriver.findElement(By.xpath("//div//input[@type='submit']"))).click();
        String s = (webDriver.findElement(By.id("back"))).getText();
        Assert.assertEquals("Great! Return to menu", s);
       (webDriver.findElement(By.id("back"))).click();
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
        String s = (webDriver.findElement(By.id("back"))).getText();
        Assert.assertEquals("Great! Return to menu", s);
        (webDriver.findElement(By.id("back"))).click();

    }
    @AfterTest
    public void cookieTest(){
        Set<Cookie> cookies = webDriver.manage().getCookies();
        ArrayList<String> cookieList= new ArrayList<>();
        cookieList.add("iframe");
        cookieList.add("form");
        cookieList.add("select");
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
    @AfterSuite
    public void closeDriver(){
        webDriver.quit();
    }
}



