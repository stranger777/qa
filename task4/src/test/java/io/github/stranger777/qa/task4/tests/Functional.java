package io.github.stranger777.qa.task4.tests;

import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.stranger777.qa.task4.WebDriverFabric;
import io.github.stranger777.qa.task4.pageobjects.LoginPage;
import io.github.stranger777.xmlconfigreader.XMLConfigReader;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.net.URI;
import java.util.concurrent.TimeUnit;

/**
 * <strong>FunctionalTest</strong> handles setup and teardown of WebDriver.
 *
 * @author Kim Schiller
 */
public class Functional {
  private static final String URL = "//url";
  private static final String CSV = "//csv";
  private static final String BROWSER = "//browser";

  private static final String SRC_TEST_RESOURCES = "src/test/resources";
  private static final String TEST_CONFIG_NAME = "taskconfig.xml";
  private static final int IMPLICIT_TIMEOUT_SECS = 15;

  private static final String TELEPHONE = "//telephone";
  private static final String PASSWORD = "//password";
  private static String LOGIN = "//login";

  protected LoginPage loginPage;

  private WebDriver driver;
  private XMLConfigReader xmlConfigReader;


  private String browserName;
  private String siteURL;
  private String csvPath;

  private String userLogin;
  private String userTelephone;
  private String userPassword;


  /**
   * Base Class for all tests
   *
   * @throws XPathExpressionException
   * @throws IOException
   * @throws SAXException
   * @throws ParserConfigurationException
   */
  public Functional() throws XPathExpressionException,
      IOException, SAXException,
      ParserConfigurationException {

    xmlConfigReader = new XMLConfigReader(getConfigURIByName(getTestConfigName()));
    siteURL = getXmlConfigReader().getNodeByXpathString(URL).getTextContent();
    csvPath = getXmlConfigReader().getNodeByXpathString(CSV).getTextContent();
    browserName = getXmlConfigReader().getNodeByXpathString(BROWSER).getTextContent();
    userLogin = getXmlConfigReader().getNodeByXpathString(LOGIN).getTextContent();
    userPassword = getXmlConfigReader().getNodeByXpathString(PASSWORD).getTextContent();
    userTelephone = getXmlConfigReader().getNodeByXpathString(TELEPHONE).getTextContent();

  }

  public static String getTestConfigName() {
    return TEST_CONFIG_NAME;
  }

  public String getUserLogin() {
    return userLogin;
  }

  /**
   * @return user phone number
   */
  public String getUserTelephone() {
    return userTelephone;
  }

  public String getUserPassword() {
    return userPassword;
  }

  /**
   *
   */
  @BeforeEach
  public void beforeEach() {
    initDriver();
    getDriver().get(getSiteURL());
    loginPage = new LoginPage(getDriver());
  }

  @AfterEach
  public void afterEach() {
    getDriver().manage().deleteAllCookies();
    getDriver().close();
  }

  protected void initDriver() {
    WebDriverManager.getInstance(DriverManagerType.valueOf(this.getBrowserName().toUpperCase())).setup();

    setDriver(new WebDriverFabric()
        .getByName(this
            .getBrowserName()));
    getDriver()
        .manage()
        .timeouts()
        .implicitlyWait(IMPLICIT_TIMEOUT_SECS, TimeUnit.SECONDS);
    getDriver()
        .manage()
        .window()
        .maximize();
  }

  @AfterEach
  public void tearDown() {
    cleanUp();
  }

  protected void cleanUp() {
    getDriver().manage().deleteAllCookies();
  }

  protected WebDriver getDriver() {
    return driver;
  }

  protected void setDriver(WebDriver driver) {
    this.driver = driver;
  }

  protected XMLConfigReader getXmlConfigReader() {
    return xmlConfigReader;
  }

  protected String getSiteURL() {
    return siteURL;
  }

  protected String getCsvPath() {
    return csvPath;
  }

  protected URI getConfigURIByName(String name) {
    return FileUtils.getFile(SRC_TEST_RESOURCES, name).toURI();
  }

  protected String getBrowserName() {
    return browserName;
  }
}