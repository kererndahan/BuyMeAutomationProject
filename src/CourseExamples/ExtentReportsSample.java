package CourseExamples;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.junit.rules.TestName;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.fail;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ExtentReportsSample {
    @Rule
    public TestName name = new TestName();
    private static ChromeDriver driver;
    // report location
    private static ExtentReports extent = new
            ExtentReports("C://Users//User//Desktop//report.html", false);
    // report details
    private static ExtentTest test = extent.startTest("My test", "Test Google Translate");
    @BeforeClass
    public static void beforeClass() {
        extent.addSystemInfo("Environment","Production");
        test.log(LogStatus.INFO, "@Before class", "before test method");
        boolean driverEstablish = false;
        try {
            System.setProperty("webdriver.chrome.driver",
                    "C:\\Users\\...chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-extensions");
            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            driverEstablish = true;
        } catch (Exception e) {
            e.printStackTrace();
            fail("Cant connect driver");
            test.log(LogStatus.FATAL, "Driver Connection Failed!",
                    e.getMessage());
            driverEstablish = false;
        }finally {
            if (driverEstablish){
                test.log(LogStatus.PASS, "Driver setup", "Driver established successfully");

            }
        }
    }

    @Test
    public void test1_openPage() {
        boolean pageOpened = false;
        try {
            driver.get("https://translate.google.com/");
            String firstWindowString = driver.getWindowHandle();
            System.out.println("Window String: " + firstWindowString);
            pageOpened = true;
        } catch (Exception e) {
            e.printStackTrace();
            test.log(LogStatus.FAIL, "Google Translate page was not found",
                    e.getMessage());
            pageOpened = false;
        }finally {
            if (pageOpened){
                test.log(LogStatus.PASS, "Open webpage", "Webpage opened successfully");
            }
        }
    }
    @Test
    public void test2_clickButton() {
        boolean pressed=false;
        try {
            test.log(LogStatus.FAIL, "Taking a screen shot",test.addScreenCapture(takeScreenShot(driver,
                    "C:\\Users\\User\\Desktop\\"+name.getMethodName())));
            driver.findElement(By.id("gt-submit")).click();
            pressed = true;
        } catch (Exception e) {
            e.printStackTrace();
            test.log(LogStatus.FAIL, "Translate button was not clicked",
                    e.getMessage());
            pressed = false;
        }finally {
            if (pressed){
                test.log(LogStatus.PASS, "Translate button click", "Translate button was clicked successfully");
            }
        }
    }
    @Test
    public void numberExceptionTest() {
        try {
            int a = 1 / 0;
        } catch (ArithmeticException e) {
            test.log(LogStatus.FAIL, "NumberException", e.getMessage());
        }

    }
    @AfterClass
    public static void afterClass() {
        test.log(LogStatus.INFO, "@After test", "After test method");
        driver.quit();
// build and flush report
        extent.endTest(test);
        extent.flush();
    }
    private static String takeScreenShot(WebDriver driver, String ImagesPath) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File screenShotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(ImagesPath+".jpg");
        try {
            FileUtils.copyFile(screenShotFile, destinationFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return ImagesPath+".jpg";
    }
}