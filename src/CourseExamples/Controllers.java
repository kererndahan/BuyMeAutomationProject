package CourseExamples;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
public class Controllers {
    private static WebDriver driver;
    private String s = "vdsabnsf";
    @BeforeClass
    public static void beforeMyClass() {
        System.out.println("beforeMyClass");
        System.setProperty("webdriver.chrome.driver",
                "C:\\Automation course\\My Drivers\\chromedriver_win32\\chromedriver.exe");


        ChromeOptions options =new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
// driver.get("https://translate.google.com");
// driver.get("https://www.youtube.com/");
//
        driver.get("https://dgotlieb.github.io/web.Controllers/web.Controllers.html");
        driver.get("http://www.facebook.com");
// driver.manage().window().maximize();
    }
    // @Test
    public void translateTest() {
        driver.findElement(By.id("source")).sendKeys("היי");
                driver.findElement(By.id("gt-submit")).submit();
    }
    // @Test
    public void youTubeTest() {
        driver.findElement(By.name("search_query")).sendKeys("The lion king");
        driver.findElement(By.id("search-icon-legacy")).submit();
    }
    //@Test
    public void lastTest() {
        List<WebElement> list = driver.findElements(By.name("group1"));// exercise b
        for (int i = 0; i < list.size(); i++) {
            WebElement tempElement = list.get(i);
            if (tempElement.getAttribute("value").equals("Cheese")) {
                System.out.println(tempElement.getAttribute("value"));

                tempElement.click();
            }
        }
        Select selection = new Select(driver.findElement(By.name("dropdownmenu")));
        selection.selectByValue("Milk");
        for (int i = 0; i < selection.getOptions().size(); i++) {
            System.out.println(selection.getOptions().get(i).getText());
        }
    }
    // @Test
    public void secondTranslateTest() {
        WebElement firstElement = driver.findElement(By.id("gt-submit"));

        WebElement secondElement = driver.findElement(By.className("jfk-button jfk- button-action"));

                WebElement thirdElement = driver.findElement(By.xpath("//input[@value='Translate']"));
        System.out.println(firstElement);
        System.out.println(secondElement);
        System.out.println(thirdElement);
    }
    @Test
    public void facebookTest() {
        driver.findElement(By.id("email")).sendKeys("d@d.com");
        driver.findElement(By.id("pass")).sendKeys("12345");
        driver.findElement(By.id("u_0_2")).click();

    }
    @Test
    public void cookiesTest() {
        driver.manage().deleteAllCookies();
        Set<Cookie> cookieSet = driver.manage().getCookies();
        for (Cookie cookie: cookieSet) {
            System.out.println(cookie);
        }
    }
    @AfterClass
    public static void afterClassEnds() {
        driver.quit();
    }
}
