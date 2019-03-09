package CourseExamples;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.*;
public class DriversAndAssert {
    private static WebDriver driver;
    private String webPageName = "walla";
    @BeforeClass
    public static void beforeMyClass(){
        System.out.println("beforeMyClass");

        System.setProperty("webdriver.chrome.driver","C:\\Users\\Daniel\\Desktop\\My Course\\drivers and apps\\chromedriver_win32\\chromedriver.exe");
                driver = new ChromeDriver();

//driver = new FirefoxDriver();
//System.setProperty("webdriver.ie.driver", "C://....IEDriverServer.exe");
//driver = new InternetExplorerDriver();
        driver.manage().window().maximize();
        driver.get("http://www.walla.co.il");
        driver.get("http://www.ynet.co.il");
        driver.get("http://www.nrg.co.il ");
    }
    @Test
    public void myFirstTest(){
        driver.navigate().refresh();
        String actualTitleName = driver.getTitle();
        assertEquals(webPageName,actualTitleName);
    }
    @AfterClass
    public static void afterClassEnds(){
        driver.quit();
    }
}

