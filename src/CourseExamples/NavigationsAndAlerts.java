package CourseExamples;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.junit.runners.MethodSorters;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NavigationsAndAlerts {
    private static WebDriver driver;
    String output;
    @BeforeClass
    public static void openBrowser() throws InterruptedException {
        System.setProperty("webdriver.chrome.chromeDriver",
                "C:\\Users...chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://dgotlieb.github.io/Navigation/Navigation.html");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @Test
    public void Test01_alert() {
        driver.findElement(By.id("MyAlert")).click();
        Alert alert = driver.switchTo().alert();
        System.out.println(alert.getText());
        alert.accept();
    }
    @Test
    public void Test02_prompt() {
// ------------- Handling Prompt ---------------------------------
        driver.findElement(By.id("MyPrompt")).click();
        Alert prompt = driver.switchTo().alert();
        prompt.sendKeys("Daniel");
        prompt.accept();
        output = "Daniel";
        assertEquals(output, driver.findElement(By.id("output")).getText());
    }
    @Test
    public void Test03_confirmBox() {
        driver.findElement(By.id("MyConfirm")).click();
        Alert confirmbox = driver.switchTo().alert();
        confirmbox.accept();
        output = "Confirmed";
        assertEquals(output, driver.findElement(By.id("output")).getText());
        driver.findElement(By.id("MyConfirm")).click();

        confirmbox.dismiss();
        output = "canceled";
        assertEquals(output, driver.findElement(By.id("output")).getText());
    }
    @Test
    public void Test04_iFrame() {
        WebElement iFrameElement =
                driver.findElement(By.cssSelector("iframe[src='newFrame.html']"));
        driver.switchTo().frame(iFrameElement);
        System.out.println("IFrame text is: " +
                driver.findElement(By.id("iframe_container")).getText());
        driver.switchTo().defaultContent();
        assertEquals("Navigation", driver.findElement(By.id("title")).getText());
    }
    @Test
    public void Test5_HandlingTabs() {
        String oldTab = driver.getWindowHandle();
        driver.findElement(By.id("openNewTab")).click();
        ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
        newTab.remove(oldTab);
        driver.switchTo().window(newTab.get(0));
        System.out.println("Tab text is: " +
                driver.findElement(By.id("new_tab_container")).getText());
        output = "This is a new Tab";
        assertEquals(output,
                driver.findElement(By.id("new_tab_container")).getText());
        driver.close();
        driver.switchTo().window(oldTab);
        assertEquals("Navigation", driver.findElement(By.id("title")).getText());
    }
    @Test
    public void Test06_windows() {
        String winHandleBefore = driver.getWindowHandle();
        driver.findElement(By.cssSelector("a[href='newWindow.html']")).click();
        for (String winHandle : driver.getWindowHandles()) //Switch to new window opened
        {
            driver.switchTo().window(winHandle);
        }
        System.out.println("Tab text is: " +
                driver.findElement(By.id("new_window_container")).getText());
        output = "This is a new Window";
        assertEquals(output,
                driver.findElement(By.id("new_window_container")).getText());
        driver.close();
        driver.switchTo().window(winHandleBefore);
        assertEquals("Navigation", driver.findElement(By.id("title")).getText());
    }

    @AfterClass
    public static void closeBrowser() {
        driver.quit();
    }
}
//public class NavigationsAndAlerts {
//}
