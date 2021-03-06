package browsers;

import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class BrowserFactory
{

    public WebDriver selectedDriver;
    private String desiredbrowser;
    private String runwhere="local";

    public static ThreadLocal<RemoteWebDriver> t1driver = new ThreadLocal<>();

    public WebDriver configuredBrowser()
    {
        this.desiredbrowser="chrome";
        if(runwhere.equalsIgnoreCase("local"))
        try{
            {
                selectedDriver=  configureLocalBrowser();
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
          //  System.setProperty("webdriver.chrome.driver","D:\\Downloads\\chromedriver_win32\\chromedriver.exe");
            ChromeOptions chromeOptions = new ChromeOptions();
            LoggingPreferences logPrefs = new LoggingPreferences();
            logPrefs.enable(LogType.PERFORMANCE, Level.SEVERE);
            logPrefs.enable(LogType.BROWSER, Level.SEVERE);
            logPrefs.enable(LogType.DRIVER, Level.SEVERE);
            chromeOptions.setCapability( "goog:loggingPrefs", logPrefs );
          //  chromeOptions.addArguments("--headless");
            WebDriverManager.chromedriver().setup();
            t1driver.set(new ChromeDriver(chromeOptions));
        }
        return getDriver();
    }

    public static synchronized RemoteWebDriver getDriver()
    {
        return t1driver.get();
    }

    @AfterStep
    public void stepafter(Scenario sc)
    {
        sc.log("Aftr every Step");
    }

    public static void getLogger( String url) {
        try {

            String formattedDate = new SimpleDateFormat("yyyy-mm-dd").format(new Date());
            LogEntries logEntries = getDriver().manage().logs().get(LogType.BROWSER);
            for (LogEntry entry : logEntries) {
                String debugLevel = entry.getLevel().toString().trim();
                String debugMessage = entry.getMessage().trim();
                System.out.println((formattedDate + " : " + debugLevel + ": " + debugMessage));
            }
            System.out.println(("-------------------END------------------"));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
