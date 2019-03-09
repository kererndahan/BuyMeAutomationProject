package CourseExamples;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.*;
/**
 * Created by Daniel on 4/28/2017.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MyActions {
    private static WebDriver driver;
    private static String expectedResult = "10";
    @BeforeClass
    public static void beforeMyClass() {
        System.setProperty("webdriver.chrome.driver", "C:\\...chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://dgotlieb.github.io/Actions/");
    }
    @Test
    public void test01_dragAndDrop() {
        WebElement locationElement = driver.findElement(By.id("drag1"));
        WebElement destinationElement = driver.findElement(By.id("div1"));
        Actions dnd = new Actions(driver);
        dnd.dragAndDrop(locationElement, destinationElement).build().perform();
    }
    @Test
    public void test02_doubleClick() {
        WebElement doubleClickElement =
                driver.findElement(By.xpath("//p[@ondblclick='doubleClickFunction()']"));
        Actions doubleClickAction = new Actions(driver);
        doubleClickAction.doubleClick(doubleClickElement);
        doubleClickAction.build().perform();

        String result = driver.findElement(By.id("demo")).getText();
        assertEquals("You double clicked", result);
    }
    @Test
    public void test03_mosueHover() {

        Actions hoverAction = new Actions(driver);
        WebElement firstHoverElement = driver.findElement(By.id("demo"));
        WebElement secondHoverElement = driver.findElement(By.id("close"));

        hoverAction.moveToElement(firstHoverElement).moveToElement(secondHoverElement).click().build().perform();
    }

    @Test
    public void test04_selectMultiple() {
        List<WebElement> elementsList= driver.findElements(By.name("kind"));
        Actions builder=new Actions(driver);
        builder.clickAndHold((WebElement)
                elementsList.get(0)).clickAndHold((WebElement) elementsList.get(2)).click();
        builder.build().perform();
    }
    @Test
    public void test05_uploadFile() {
        driver.findElement(By.name("pic")).sendKeys("C:\\vcredist.bmp");
    }
    @Test
    public void test06_scrollToElement() {
        WebElement element = driver.findElement(By.id("clickMe"));
        ((JavascriptExecutor)
                driver).executeScript("arguments[0].scrollIntoView(true);", element);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test07_scrollToLocation() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("javascript:window.scrollBy(250,350)");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @AfterClass
    public static void afterClassEnds() {
        driver.quit();


    }
}