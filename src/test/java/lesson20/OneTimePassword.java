package lesson20;
import org.openqa.selenium.By;

public class OneTimePassword {

    private static By oneTimePasswordInputField = By.xpath("//input[@id=\"otp-code\"]");
    private static By enterButton = By.xpath("//button[@id=\"login-otp-button\"]");
    private static By message = By.xpath("//div[@id=\"otp-code-text\" and text()]");

    private final static String URL = "https://idemo.bspb.ru/auth/otp?authOptionId=SMS%3A10005";
    private final static String OTP = "0000";

    public static By getOneTimePasswordInputField() {
        return oneTimePasswordInputField;
    }

    public static By getEnterButton() {
        return enterButton;
    }

    public static By getMessage() {
        return message;
    }

    public static String getURL() {
        return URL;
    }

    public static String getOTP() {
        return OTP;
    }
}
