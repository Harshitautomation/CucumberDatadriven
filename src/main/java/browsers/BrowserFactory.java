package browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.concurrent.TimeUnit;

public class BrowserFactory
{

    public RemoteWebDriver selectedDriver;
    private String desiredbrowser;
    private String runwhere="local";

    public static ThreadLocal<RemoteWebDriver> t1driver = new ThreadLocal<>();

    public WebDriver configuredBrowser()
    {
        this.desiredbrowser="chrome";
        if(runwhere.equalsIgnoreCase("local"))
        try{
            {
                selectedDriver= (RemoteWebDriver) configureLocalBrowser();
                selectedDriver.manage().window().maximize();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        selectedDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        return selectedDriver;

    }

    private WebDriver configureLocalBrowser()
    {
        System.out.println("local browser");

        if(desiredbrowser.equalsIgnoreCase("chrome"))
        {
            System.setProperty("webdriver.chrome.driver","F:\\chromedriver\\chromedriver.exe");
            t1driver.set(new ChromeDriver());
        }
        return getDriver();
    }

    public static synchronized RemoteWebDriver getDriver()
    {
        return t1driver.get();
    }

}
