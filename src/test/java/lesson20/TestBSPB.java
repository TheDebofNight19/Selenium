package lesson20;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;




public class TestBSPB {

    private WebDriver webDriver;
    private static final Logger LOG = LoggerFactory.getLogger(TestBSPB.class);


    @BeforeTest
    public void initDriver() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
    }


    @Test
    public void testLogin() {

        TestSteps testSteps = new TestSteps(webDriver);

        testSteps.login();
        testSteps.assertOneTimePasswordPage();
        testSteps.inputOneTimePassword();
        testSteps.assertMainMenuPage();
        testSteps.interactWithMainMenu();
        testSteps.compareText();
        testSteps.checkMyBalanceElement();
    }

    @AfterClass
    public void closeDriver(){
        webDriver.quit();
    }
}
