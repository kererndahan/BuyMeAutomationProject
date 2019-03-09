package CourseExamples;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MobileFindElements {
    private static AndroidDriver driver;
    @BeforeClass
    public static void setUp() throws MalformedURLException {
        File appDir = new File("C:\\Users...apk\\");
        File app = new File(appDir, "app-debug.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Android Device");
// capabilities.setCapability("platformVersion", "6.0.1"); //not must
        //capabilities.setCapability("app", app.getAbsolutePath());
        //capabilities.setCapability("newCommandTimeout", 120);
        capabilities.setCapability("appPackage", "com.android.chrome");
        capabilities.setCapability("appActivity", "com.google.android.apps.chrome.Main");
        driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub/"),
                capabilities);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    @Test
    public void test1_printPageSource(){
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement userNameElement =
                driver.findElement(By.id("com.dgotlieb.automationsample:id/userName"));
        wait.until(ExpectedConditions.visibilityOf(userNameElement));
        System.out.println(driver.getPageSource());
    }
    @Test
    public void test2_printClickableElements() {
//print all clickable elements
        List<WebElement> elements = driver.findElementsByAndroidUIAutomator("new UiSelector().clickable(true)");
                WebElement tempElement;
        for (WebElement element : elements) {
            tempElement = element;
            System.out.println(tempElement.getLocation());

        }
    }
    @Test
    public void test3_buttonByClassName() {
        WebElement button =
                driver.findElement(MobileBy.className("android.widget.Button"));
        System.out.println(button.getText());
    }
    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
