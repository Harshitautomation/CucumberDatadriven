package utility;

import browsers.BrowserFactory;
import io.cucumber.java.eo.Se;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UIHelper {

    private static Logger logger = LogManager.getLogger(UIHelper.class);

    public void SelectListByValue(By by, String value)
    {
        Select s = new Select(BrowserFactory.getDriver().findElement(by));
        s.selectByValue(value);
        logger.log(Level.INFO,"Value selected is : "+value);
    }

    public static boolean isOptionDisplayedinDrpDwn(By by, String value)
    {
        Select s = new Select(BrowserFactory.getDriver().findElement(by));
        return s.getOptions().stream().map(we->we.getText()).anyMatch(t->t.equals(value));
    }

    public static String getAttribute(By by,String att)
    {
        return BrowserFactory.getDriver().findElement(by).getAttribute(att);
    }

    public static void SetElementViaActionClass( WebElement e,String text)
    {
        Actions act = new Actions(BrowserFactory.getDriver());
        act.click(e).sendKeys(e,text).build().perform();
    }

    public static boolean verifyCopyText(By by,String expectdtext) {
        boolean flag = false;
        Actions act = new Actions(BrowserFactory.getDriver());
        act.keyDown(BrowserFactory.getDriver().findElement(by), Keys.CONTROL).sendKeys("a").build().perform();
        act.keyDown(Keys.CONTROL).sendKeys("c").build().perform();
        String actualtext = "";

        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable contents = clipboard.getContents(null);
        boolean hasTransfertext = (contents != null) && contents.isDataFlavorSupported(DataFlavor.stringFlavor);
        if (hasTransfertext) {
            try {
                actualtext = (String) contents.getTransferData(DataFlavor.stringFlavor);
            } catch (IOException | UnsupportedFlavorException e) {
                e.printStackTrace();
            }

        }
        if(actualtext.contains(expectdtext))
        {
            flag=true;
        }
        return flag;
    }

    public  static String getTommorrowDate()
    {
        Date today = new Date();
        SimpleDateFormat formatdate= new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE,1); //number of days to add
        return formatdate.format(c.getTime());

    }

}
