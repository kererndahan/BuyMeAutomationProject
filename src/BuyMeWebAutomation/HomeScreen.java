package BuyMeWebAutomation;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;
import java.util.List;
import static BuyMeWebAutomation.Helper.takeScreenShot;

public class HomeScreen {
    private static Helper myHelper = new Helper();
    private static WebElement openLoginWindow;
    private static List<WebElement> elementAmount;// = driver.findElements(By.className("active-result"));
    private static List<WebElement> elementArea;
    private static List<WebElement> elementCategory;
    private static WebElement elementSearchWork;
    private static WebElement findButton;
    private static List<WebElement> searchResultLinks = new ArrayList<>();
    private static List<WebElement> searchResultxxx;

    public void ClickOnLoginButton(WebDriver driver,WebDriverWait wait){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='seperator-link']")));
        driver.findElement(By.xpath("//span[@class='seperator-link']")).click();
    }

    public  void Search4Gift(int amountIndex, int areaIndex, int categoreyIndex, WebDriver driver, ExtentTest test) throws InterruptedException {
        //click on the element make element visable
        try {
            Thread.sleep(1000);
            test.log(LogStatus.INFO, "Searching for Your gift with selected elements....");
//            myHelper.staticScrollDown();
            driver.findElement(By.className("chosen-single")).click();
            /********************Price Range************************************/
            elementAmount = driver.findElements(By.className("active-result"));
            elementAmount.get(amountIndex).click();
            driver.findElement(By.xpath("//a[contains(.,'אזור')]")).click();
            /********************Area****************************************/
            elementArea = driver.findElements(By.className("active-result"));
            elementArea.get(areaIndex).click();
            /********************Category****************************************/
            driver.findElement(By.xpath("//span[contains(.,'קטגוריה')]")).click();
            elementCategory = driver.findElements(By.className("active-result"));
            elementCategory.get(categoreyIndex).click();
            /********************Search****************************************/
            findButton = driver.findElement(By.className("btn-primary"));
            findButton.click();
            test.log(LogStatus.PASS, "Search Gift completed succsfully!");
            WebElement numOfResults = driver.findElement(By.className("page-title"));
            System.out.println("num of result" + numOfResults.getText());
            test.log(LogStatus.INFO,numOfResults.getText());
            test.log(LogStatus.PASS, "Search Gift", test.addScreenCapture(takeScreenShot("SP",driver)));
        }
        catch (Exception e){
            test.log(LogStatus.ERROR,"Search Gift FAILED");
            test.log(LogStatus.ERROR,"SearchFAIL", test.addScreenCapture(takeScreenShot("SF",driver)));

        }

    }

}
