package pages;

import browsers.BrowserFactory;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;

public class BasePage
{
    public WebDriver driver;
    public Scenario scenario;

    public void intialize()
    {
        BrowserFactory browserFactory = new BrowserFactory();
        driver=browserFactory.configuredBrowser();
        openURL();

    }

    public void openURL()
    {
        driver.get("https://www.techlistic.com/p/selenium-practice-form.html");
    }
}
