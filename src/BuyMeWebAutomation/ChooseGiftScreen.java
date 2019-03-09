package BuyMeWebAutomation;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static BuyMeWebAutomation.Helper.takeScreenShot;


public class ChooseGiftScreen {
    WebDriver driver;
    Helper myHelper = new Helper();
    public void selectGiftSupplier(int index, WebDriver driver, ExtentTest test) throws InterruptedException {
        Thread.sleep(1000);
        List <WebElement> supplier = driver.findElements(By.cssSelector(".promo-item a"));
        try{
            System.out.println(index);
            myHelper.ScrollToElement(supplier.get(index),driver);
            supplier.get(index).click();
            test.log(LogStatus.PASS, "Select Gift:");
        }
        catch(Exception e){
            test.log(LogStatus.WARNING, "Select Gift is out of bound - selected first one:");
            myHelper.ScrollToElement(supplier.get(0),driver);
            supplier.get(0).click();
        }
        finally {
            test.log(LogStatus.PASS, "Select Gift number: " + index, test.addScreenCapture(takeScreenShot("R2",driver)));
        }
    }

}
