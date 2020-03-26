package lesson20;


import org.openqa.selenium.WebDriver;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestSteps {

    private WebDriver webDriver;
    private WebDriverWait webDriverWait;




    public TestSteps(WebDriver webDriver) {
        this.webDriver = webDriver;
    }


    public void login(){

        webDriver.get("https://idemo.bspb.ru");
        webDriver.findElement(LoginPage.getLoginField()).clear();
        webDriver.findElement(LoginPage.getPasswordField()).clear();
        webDriver.findElement(LoginPage.getLoginField()).sendKeys(LoginPage.getLOGIN());
        webDriver.findElement(LoginPage.getPasswordField()).sendKeys(LoginPage.getPASSWORD());
        webDriver.findElement(LoginPage.getEnterButton()).click();
    }

    public void assertOneTimePasswordPage(){

        webDriver.get("https://idemo.bspb.ru/auth/otp?authOptionId=SMS%3A10005");
        String currentUrl = webDriver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains((OneTimePassword.getURL())));
    }

    public void inputOneTimePassword(){

        webDriver.findElement(OneTimePassword.getOneTimePasswordInputField()).clear();
        webDriver.findElement(OneTimePassword.getOneTimePasswordInputField()).sendKeys(OneTimePassword.getOTP());
        webDriver.findElement(OneTimePassword.getEnterButton()).click();
    }

    public void assertMainMenuPage(){

        String currentURL = webDriver.getCurrentUrl();
        Assert.assertEquals(currentURL, MainMenu.getURL());
    }


    public void interactWithMainMenu(){
        webDriver.findElement(MainMenu.getOverview()).click();

    }

    public void compareText(){
        webDriverWait = new WebDriverWait(webDriver, 20);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(MainMenu.getText()));
        Assert.assertEquals(webDriver.findElement(MainMenu.getText()).getText(), MainMenu.getTEXT());
        Pattern pat = Pattern.compile("[1-9]\\s[0-9]{3}\\s[0-9]{3}\\.[0-9]{2}\\s₽");
        Matcher mat = pat.matcher(webDriver.findElement(MainMenu.getAmount()).getText());
        Assert.assertTrue(mat.matches());
    }

    public void checkMyBalanceElement(){
        Actions actions = new Actions(webDriver);
        actions.moveToElement(webDriver.findElement(MainMenu.getMyBalance())).build().perform();
        String s = webDriver.findElement(MainMenu.getMyBalance()).getText();
        Pattern pat = Pattern.compile("Моих\\sсредств\\s[1-9]\\s[0-9]{3}\\s[0-9]{3}\\.[0-9]{2}\\s₽");
        Matcher mat = pat.matcher(s);
        Assert.assertTrue(mat.matches());
    }

}