package BuyMeWebAutomation;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static BuyMeWebAutomation.Helper.takeScreenShot;

public class RegistrationScreen {
    private static WebElement notRegistered;
    private static WebElement elementFirstName;
    private static WebElement elementEmail;
    private static List<WebElement> elementPass;
    private static WebElement elementIAgree;
    private static WebElement submit;

    public void clickLoginWithoutDetails(WebDriver driver, ExtentTest test){
        String expacted = "כל המתנות מחכות לך! אבל קודם צריך מייל וסיסמה";
        driver.findElement(By.cssSelector("button.db")).click();
        WebElement error = driver.findElement(By.xpath("//li[contains(.,'כל המתנות מחכות לך! אבל קודם צריך מייל וסיסמה')]"));
        try {
            Assert.assertEquals(expacted, error.getText());
            test.log(LogStatus.PASS, "Check Error message when Tried to log in without credetilas");
            test.log(LogStatus.PASS, "Check Error message when Tried to log in without credetilas", test.addScreenCapture(takeScreenShot("R3",driver)));
        }
        catch (Exception e){
            test.log(LogStatus.ERROR, "Check Error message when Tried to log in without credetilas");
            test.log(LogStatus.ERROR, "Check Error message when Tried to log in without credetilas", test.addScreenCapture(takeScreenShot("R3",driver)));

        }
    }
    public void clickNewuser(WebDriver driver,WebDriverWait wait){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//strong[contains(.,'עוד לא נרשמת?')]")));
        notRegistered = driver.findElement(By.xpath("//strong[contains(.,'עוד לא נרשמת?')]"));
        notRegistered.click();
    }
    public void createNewUser(String firstName,String email,String pass,WebDriver driver,ExtentTest test){
        try {
            elementFirstName = driver.findElement(By.xpath("//input[@type='text']")); //email //password
            elementFirstName.sendKeys(firstName);
            elementEmail = driver.findElement(By.xpath("//input[@type='email']"));
            elementEmail.sendKeys(email);
            //if pass id will change - change it to list of passwords...
            elementPass = driver.findElements(By.xpath("//input[@type='password']"));
            elementPass.get(0).sendKeys(pass);
            elementPass.get(1).sendKeys(pass);
            elementIAgree = driver.findElement(By.xpath("//label[@for='register-consent']"));
            elementIAgree.click();
            test.log(LogStatus.PASS, "Registration details", test.addScreenCapture(takeScreenShot("R1",driver)));

            submit = driver.findElement(By.xpath("//button[@type='submit']"));
            submit.click();
            test.log(LogStatus.PASS, "Registered succsfully! you are logged in");
            test.log(LogStatus.PASS, "LOG IN", test.addScreenCapture(takeScreenShot("R2",driver)));
        }
        catch (Exception e){
            test.log(LogStatus.ERROR,"Registration FAILED in FAILED");
            test.log(LogStatus.ERROR,"LOG IN", test.addScreenCapture(takeScreenShot("R3",driver)));

        }
        }
}
