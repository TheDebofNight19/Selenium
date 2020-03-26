package lesson20;

import org.openqa.selenium.By;

public class MainMenu {


    private static By overview = By.xpath("//a[@id=\"bank-overview\"]");
    private static By amount = By.xpath("//div[@id=\"can-spend\"]//span//span");
    private static By text = By.xpath("//div[@id=\"can-spend\"]//span");
    private static By myBalance = By.xpath("//span[@class=\"amount-holder\"]//small[@class=\"my-assets\"]");

    private static final String URL = "https://idemo.bspb.ru/welcome";
    private static final String TEXT = "Финансовая свобода";

    public static By getOverview() {
        return overview;
    }

    public static By getAmount() {
        return amount;
    }


    public static By getText() {
        return text;
    }


    public static String getURL() {
        return URL;
    }

    public static By getMyBalance() {
        return myBalance;
    }


    public static String getTEXT() {
        return TEXT;
    }
}