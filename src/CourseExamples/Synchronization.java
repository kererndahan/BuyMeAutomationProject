package CourseExamples;

import java.util.concurrent.TimeUnit;

import com.paulhammant.ngwebdriver.ByAngular;
import com.paulhammant.ngwebdriver.NgWebDriver;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import static org.junit.Assert.assertEquals;
    @FixMethodOrder (MethodSorters.NAME_ASCENDING)
    public class Synchronization {
        private static NgWebDriver ngWebDriver;
        public static ChromeDriver driver;

        @BeforeClass
        public static void openBrowser() {
            System.setProperty("webdriver.chrome.driver", "C:\\...chromedriver.exe");
            driver = new ChromeDriver();
            ngWebDriver = new NgWebDriver(driver);
            driver.manage().window().maximize();
            driver.navigate().to("https://dgotlieb.github.io/Selenium/synchronization.html");
        }

        @Test
        public void Test1_ImplicitWait() {
// Implicit Wait
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.findElement(By.id("checkbox")).isDisplayed();
            driver.findElement(By.id("btn")).click();
            driver.findElement(By.id("message")).isDisplayed();
        }

        @Test
        public void Test2_Sleep() throws InterruptedException {
// Sleep
            driver.findElement(By.id("hidden")).click();
            Thread.sleep(10000);
            driver.findElement(By.cssSelector("div[style='']")).isDisplayed();
        }

        @Test
        public void Test3_ExplicitWait() {
// Explicit Wait
            driver.findElement(By.id("rendered")).click();
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish2")));
            String output = driver.findElement(By.id("finish2")).getText();
            assertEquals(output, "This is a new element");
        }

        @Test
        public void angularTest() {
            driver.get("https://dgotlieb.github.io/AngularJS/main.html");
            ngWebDriver.waitForAngularRequestsToFinish();
            WebElement firstname = driver.findElement(ByAngular.model("firstName"));
            firstname.clear();
            firstname.sendKeys("Daniel");
            assertEquals(driver.findElement(By.xpath("//input")).getAttribute("value"), "Daniel");
        }

        @AfterClass
        public static void closeBrowser() {
            driver.quit();
        }
    }
