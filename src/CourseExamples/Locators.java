package CourseExamples;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.util.Map;
public class Locators {
    private static WebDriver driver;
    @BeforeClass
    public static void beforeMyClass() {
        System.setProperty("webdriver.chrome.driver","C:\\Users...\\chromedriver.exe");
//initialize the driver
        driver = new ChromeDriver();
        driver.get("https://www.amazon.com/");
        driver.manage().window().maximize();
    }
    //@Test
    public void googleTranslateTest() {
        WebElement translateButton = driver.findElement(By.linkText("Translate"));
        System.out.println(translateButton);
    }
    // @Test
    public void youTubeTest() {
        WebElement youTubeButton = driver.findElement(By.id("logo-icon"));
        System.out.println(youTubeButton);
    }
    // @Test
    public void seleniumTest() {
        WebElement seleniumElement = driver.findElement(By.name("q"));
        System.out.println(seleniumElement);

// WebElement firstElement = chromeDriver.findElement(By.id("gt-submit"));

// WebElement thirdElement =chromeDriver.findElement(By.xpath("//input[@value='Translate']"));
//
// System.out.println(secondElement);
// System.out.println(thirdElement);
// String userAgent = (String) ((JavascriptExecutor)driver).executeScript("return navigator.userAgent;");
// System.out.println(userAgent);
    }
    @Test

    public void amazonTest() {
        String expectedAmazonTitle = "Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more";
        assertEquals(expectedAmazonTitle,driver.getTitle());
        WebElement amazonSearchBar = driver.findElement(By.id("twotabsearchtextbox"));
        amazonSearchBar.sendKeys("Leather shoes");
        amazonSearchBar.submit();
    }

    @AfterClass
    public static void tearDown(){
        driver.quit();
    }
}
//Locator Referance http://www.seleniumhq.org/docs/
