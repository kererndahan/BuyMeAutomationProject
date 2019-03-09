package BuyMeWebAutomation;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;



import static BuyMeWebAutomation.Helper.takeScreenShot;


public class SenderReceiverInfoScreen {
    Helper myhelper = new Helper();

    public void SelectGiftAndclickOnBuy(WebDriver driver, ExtentTest test) throws InterruptedException {
        driver.findElement(By.className("card-info")).click();
        myhelper.staticScrollDown(driver);
//        WebElement scroolTo = driver.findElement(By.xpath("driver.findElement(By.xpath(\"//div[@class='row row--md row--with-gutter features']\"))"));

        try {
            List<WebElement> buy = driver.findElements(By.className("btn-purchase"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", buy.get(0));
            buy.get(0).click();
            test.log(LogStatus.PASS, "Purches step", test.addScreenCapture(takeScreenShot("bbb",driver)));
        }
        catch (Exception e){
            test.log(LogStatus.FAIL, "GOT Error on Purches step");//);
        }
    }

    public void sendTo (WebDriver driver ,ExtentTest test,String reciver,String sender,String pathToImage,int howToSend){
        WebElement forFriend = driver.findElement(By.xpath("//label[@for='for-friend-radio']"));
        forFriend.click();
        /****************Assrt text color*******************************************/
        // used FF add on () to find the color: Color Zila -> folowind command needs Jar to work
        //assertTrue(selenium.isElementPresent("css=span[bgcolor=#F7B336]"));
        //FOCUS ; OR for-friend
        Actions actions = new Actions(driver);
        driver.findElement(By.className("step-form")).click();
        List <WebElement> WeReciver= driver.findElements(By.xpath("//input[@maxlength<31]"));
        List <String> details = new ArrayList<>();
        details.add(reciver);
        details.add(sender);
        details.add("some text here...veryong....");
        int i=0;
        for (WebElement link : WeReciver) {
            System.out.println("Details element" + i +details.get(i));
            System.out.println("");
            WeReciver.get(i).clear();
            actions.moveToElement(WeReciver.get(i));
            actions.click();
            actions.sendKeys(details.get(i));
            actions.build().perform();
            System.out.println("WeReciver " + WeReciver.get(i) );
            i++;
        }
        driver.findElement(By.xpath("//textarea[@id=\"msg\"]")).sendKeys(details.get(2));
        //Verifications...
        List <WebElement> verifications = driver.findElements(By.xpath("//span[@class=\"name\"]"));
        WebElement verName = verifications.get(0);
        Assert.assertEquals(verName.getText(), details.get(0));
        System.out.println(verName.getText());

        WebElement verFrom = verifications.get(1);
        System.out.println(verFrom.getText());
        Assert.assertEquals(verFrom.getText(), details.get(1));

        WebElement verCardText = driver.findElement(By.className("card-text"));
        System.out.println(verCardText.getText());
        Assert.assertEquals(verCardText.getText(), details.get(2));

        WebElement chooseFile = driver.findElement(By.xpath("//input[@type='file']"));
        chooseFile.sendKeys("c:\\WebMaterials\\art.jpg");
        test.log(LogStatus.PASS, "taking screen AfterInsertDetails", test.addScreenCapture(takeScreenShot("ccc",driver)));

        List<WebElement> envelop = driver.findElements (By.className("icon-envelope"));
        //(By.xpath("//button[@class=\"btn-send-option\"]"));
        WebElement WeEmail = envelop.get(0);
        WeEmail.click();
        driver.findElement(By.className("input-mail")).sendKeys("keren@xmpie.com");
        driver.findElement(By.className("btn-save")).click();
        driver.findElement(By.xpath("//button[@data-toggle=\"modal\"]")).click();
    }
}
