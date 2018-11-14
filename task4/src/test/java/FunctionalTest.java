import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.stranger777.qa.task4.WebDriverSingleton;
import io.github.stranger777.xmlconfigreader.XMLConfigReader;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

/**
 * <strong>FunctionalTest</strong> handles setup and teardown of WebDriver.
 *
 * @author Kim Schiller
 */
public class FunctionalTest {
    protected static final String TEST_CONFIG_NAME = "taskconfig.xml";

    protected static WebDriver driver;
    protected static XMLConfigReader xmlConfigReader;
    protected static String browserName;
    protected static String siteURL;
    protected static String csvPath;

    public FunctionalTest() throws XPathExpressionException,
            URISyntaxException,
            IOException,
            SAXException,
            ParserConfigurationException {
        xmlConfigReader = new XMLConfigReader(getConfigURIByName(TEST_CONFIG_NAME));
        siteURL = xmlConfigReader.getNodeByXpathString("//url").getTextContent();
        csvPath = xmlConfigReader.getNodeByXpathString("//csv").getTextContent();
        browserName = xmlConfigReader.getNodeByXpathString("//browser").getTextContent();
    }

    @BeforeAll
    public static void beforeAll() throws InterruptedException {
        WebDriverManager
                .getInstance(DriverManagerType.valueOf( "FIREFOX" ))
                .setup();
        driver = WebDriverSingleton.WEB_DRIVER_SINGLETON.getInstance("firefox");
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
    }

    @AfterAll
    public static void tearDown() {
        driver.close();
    }

    protected String getBrowserName() {
        return browserName;
    }

    public static URI getConfigURIByName(String name) throws URISyntaxException {
        ClassLoader cl = FunctionalTest.class.getClassLoader();
        String file = cl.getResource(name).getFile();
        return (new File(file)).toURI();
    }

    @AfterEach
    public void cleanUp() {
        driver.manage().deleteAllCookies();
    }
}