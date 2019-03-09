package BuyMeWebAutomation;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class Helper{
    public final String XMLPaTH = "C:\\WebMaterials\\web.xml";

    public static void main(String[] args) {
//        System.out.println("test");
//        System.out.println(getData("URL"));
//        System.out.println(getData("browser"));

    }

    public Helper() {
        WebDriver driver;
    }

    public String getData (String keyName) {
        File configXmlFile = new File(XMLPaTH);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document doc = null;
        try {
            assert dBuilder != null;
            doc = dBuilder.parse(configXmlFile);
        } catch (SAXException | IOException e) {
            e.printStackTrace();

        }
        if (doc != null) {
            doc.getDocumentElement().normalize();
        }
        assert doc != null;
        System.out.println(doc.getElementsByTagName(keyName).item(0).getTextContent());
        return doc.getElementsByTagName(keyName).item(0).getTextContent();
    }


    public void openURL(String URL,WebDriver driver) {
        driver.get(URL);

    }

    public static String takeScreenShot(String imageName,WebDriver driver) {
        String ImagesPath = "C:\\TestResults\\ReportImages\\" + imageName;
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

    public void staticScrollDown(WebDriver driver) throws InterruptedException {
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("scroll(0, 600);");
        //To scroll up - jse.executeScript("scroll(0, -250);");
        Thread.sleep(500);
    }
    public void ScrollToElement(WebElement element,WebDriver driver) throws InterruptedException {
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(500);
    }

}
