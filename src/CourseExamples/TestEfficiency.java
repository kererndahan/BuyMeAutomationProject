//package CourseExamples;
//import org.openqa.selenium.WebDriver;
//        import org.junit.AfterClass;
//        import org.junit.Assert;
//        import org.junit.BeforeClass;
//        import org.junit.Test;
//        import org.openqa.selenium.By;
//        import org.openqa.selenium.WebDriver;
//        import org.openqa.selenium.chrome.ChromeDriver;
//        import org.openqa.selenium.support.PageFactory;
//public class TestEfficiency {
//    private static WebDriver driver;
//
//    @BeforeClass
//    public static void beforeMyClass() {
//        System.setProperty("webdriver.chrome.driver", "C:\\..chromedriver.exe");
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        driver.get("https://dgotlieb.github.io/WebCalculator");
//        POM_and_Factory pomAndFactory = PageFactory.initElements(driver,
//                POM_and_Factory.class);
//    }
//
//    @Test
//    public void myFirstTest() {
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println(driver.findElement(By.id(Constants.SEVEN)).getSize());
//        System.out.println(driver.findElement(By.id("six")).isDisplayed());
//        driver.findElement(By.id("five")).click();
//        driver.findElement(By.id("add")).click();
//        POM_and_Factory.pressFive(driver).click();
//        driver.findElement(By.id("equal")).click();
//        POM_and_Factory.clickCalculate();
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        String res = driver.findElement(By.id("screen")).getText();
//        String expectedResult = "10";
//        Assert.assertEquals(expectedResult, res);
//    }
//
//    @AfterClass
//    public static void afterClassEnds() {
//        driver.quit();
//    }
//}
