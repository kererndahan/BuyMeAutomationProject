package BuyMeWebAutomation;

//import FinelWebAutomationProject.*;
//import FinelWebAutomationProject.Helper;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static BuyMeWebAutomation.Helper.takeScreenShot;

public class Sanity {
    public static WebDriver driver;
    public static WebDriverWait wait;

    @Rule
    public TestName name = new TestName();
    // report location & details
    public static ExtentReports extent = new ExtentReports("C://Users//Keren//Desktop//report.html", false);
    public static ExtentTest test = extent.startTest("Buy me! ", "Keren's Automation project");
    private static Helper myHelper = new Helper();
    private static HomeScreen myHome= new HomeScreen();
    private static RegistrationScreen register = new RegistrationScreen();
    private static ChooseGiftScreen gift = new ChooseGiftScreen();
    private static SenderReceiverInfoScreen payment = new SenderReceiverInfoScreen();

    @BeforeClass
    public static void beforeMyClass() {
        String browserType = myHelper.getData("browser");
        String URL = myHelper.getData("URL");
        setDriverAccordingToBrowser(browserType,URL);// (driver,browserType);
        //myHelper.openURL(URL,driver);
        wait = new WebDriverWait(driver, 30);
        extent.addSystemInfo("Environment","Production");
        test.log(LogStatus.INFO, "Selected Browser", browserType);
     /* After driver & Wait are Intialized in Helper can use Class constractors *////

    }
 @Test
    public void sanity() throws Exception {
        int randomNum = 10 + (int) (Math.random() * 1000);
        String email = "kerenstore07+" + Integer.toString(randomNum) + "@gmail.com";
        test.log(LogStatus.INFO, "Logging you in....");
        /***************Test Login without details**********************************/
        myHome.ClickOnLoginButton(driver,wait);
        register.clickLoginWithoutDetails(driver,test);
        /***************REGISTER****************************************************/
        register.clickNewuser(driver,wait);
        register.createNewUser("Keren", email, "123456",driver,test);
        test.log(LogStatus.INFO,"Searching for gift....");
        /*****************Search for gift******************************************/
        myHome.Search4Gift(3,3,3,driver,test);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("page-title")));
        /******************Select Gift********************************************/
        gift.selectGiftSupplier(6,driver,test);
        payment.SelectGiftAndclickOnBuy(driver,test);
        payment.sendTo(driver,test,"aa","bb","userpath",1);
 }

    public static void setDriverAccordingToBrowser(String browser,String URL){
        switch (browser) {
            case "Chrome":
                System.setProperty("webdriver.chrome.driver", "C:\\Automation course\\My Drivers\\chromedriver_win32\\chromedriver.exe");
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                break;
            case "FireFox":
                System.setProperty("webdriver.gecko.driver", "C:\\Automation course\\My Drivers\\geckodriver-v0.24.0-win64");
                driver = new FirefoxDriver();
//                ((FirefoxDriver)driver).getKeyboard().pressKey(Keys.F11);
                break;
            case "Edge": //Fix so will ajust to edge
                System.setProperty("webdriver.edge.driver", "C:\\Automation course\\My Drivers\\Edge\\MicrosoftWebDriver.exe");
                driver = new EdgeDriver();
                break;
            default:
                System.setProperty("webdriver.edge.driver", "C:\\Automation course\\My Drivers\\chromedriver_win32\\chromedriver.exe");
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                  break;
        }
        driver.get(URL);
    }
    @AfterClass
    public static void afterClass() {
        test.log(LogStatus.INFO,"Test Completed", Sanity.test.addScreenCapture(takeScreenShot("done",driver)));
        driver.quit();
        extent.endTest(test);
        extent.flush();
    }
}
