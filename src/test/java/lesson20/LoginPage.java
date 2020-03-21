package lesson20;

import org.openqa.selenium.By;


public class LoginPage {

    /**
     * объявляем необходимиые для работы со страницей переменные и константы
     */

    private static By loginField = By.xpath("//input[@name=\"username\"]");
    private static By passwordField = By.xpath("//input[@name=\"password\"]");
    private static By enterButton = By.xpath("//button[@id=\"login-button\"]");

    private final static String LOGIN = "demo";
    private final static String PASSWORD = "demo";



    public static By getLoginField() {
        return loginField;
    }

    public static By getPasswordField() {
        return passwordField;
    }


    public static By getEnterButton() {
        return enterButton;
    }

    public static String getLOGIN() {
        return LOGIN;
    }

    public static String getPASSWORD() {
        return PASSWORD;
    }
}
