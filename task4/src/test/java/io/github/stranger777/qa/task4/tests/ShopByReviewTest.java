package io.github.stranger777.qa.task4.tests;

import io.github.stranger777.qa.task4.pageobjects.AllCategoriesPage;
import io.github.stranger777.qa.task4.pageobjects.ConcreteCategoryPage;
import io.github.stranger777.qa.task4.pageobjects.LoginPage;
import io.github.stranger777.qa.task4.pageobjects.MainPage;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShopByReviewTest extends Functional {

  private static final String LOGIN_TEXT = "Войти";
  private static final String CSV_EXTENSION = ".csv";
  private static final String REPLACEMENT = "";
  private static final String COMMA = ",";
  private static final int sleepTimeout = 15000;
  private String goodiesHTMLAnchors = "(<a class=\"ModelReviewsHome__NameModel\".*</a>)";
  private String trimHTML = "<.*?>";

  public ShopByReviewTest() throws IOException, SAXException,
      ParserConfigurationException,
      XPathExpressionException {
  }

  @Test
  public void testShopByReviewToCSV() throws IOException, InterruptedException {
    assertPageIsDisplay();
    assertAuth();
    MainPage mainPage = new MainPage(getDriver());
    assertCategoryTitle(mainPage);
    writeCSV();
    assertLogout(mainPage);
  }

  protected void assertLogout(MainPage mainPage) throws InterruptedException {
    mainPage.logOut();
    Thread.sleep(sleepTimeout);
    getDriver().get(getSiteURL());
    LoginPage loginPageAfterLogout = new LoginPage(getDriver());

    String expectedLoginText = LOGIN_TEXT;
    assertEquals(expectedLoginText, loginPageAfterLogout.getEnterText());
  }

  protected void writeCSV() throws IOException {
    Path file = createCSVFile();
    FileUtils.writeStringToFile(file.toFile(), getCSVString());
    boolean fileIsExistExpected = true;
    boolean fileIsExistActual = Files.exists(file);
    assertEquals(fileIsExistExpected, fileIsExistActual);
  }

  private Path createCSVFile() throws IOException {
    Calendar calendar = Calendar.getInstance();
    String csvFileName = getMomentOfTimeAsString(calendar) + CSV_EXTENSION;
    String csvFileFullPathWithName = getCsvPath() + csvFileName;
    String pathToDir = StringEscapeUtils.escapeJava(getCsvPath());
    if (!Files.exists(Paths.get(pathToDir))) {
      Files.createDirectory(Paths.get(pathToDir));
    }
    return Files.createFile(Paths.get(csvFileFullPathWithName));
  }

  protected String getCSVString() {
    getDriver().get(getSiteURL());
    String pageSource = getDriver().getPageSource();
    Pattern pattern = Pattern.compile(goodiesHTMLAnchors);
    Matcher matcher = pattern.matcher(pageSource);
    StringBuilder stringBuilder = new StringBuilder();
    while (matcher.find()) {
      stringBuilder.append(matcher.group().replaceAll(trimHTML, REPLACEMENT) + COMMA);
    }
    return stringBuilder.toString();
  }

  protected void assertCategoryTitle(MainPage mainPage) {
    mainPage.getAllCatalog();
    AllCategoriesPage allCategoriesPage = new AllCategoriesPage(getDriver());
    String titleExpected = allCategoriesPage.getConcreteCategoryName();
    allCategoriesPage.getConcreteCategoryPage();
    ConcreteCategoryPage concreteCategoryPage = new ConcreteCategoryPage(getDriver());
    String titleActual = concreteCategoryPage.getTitleText();
    assertEquals(titleExpected, titleActual);
  }

  protected void assertAuth() throws InterruptedException {
    loginPage.authActions(getUserTelephone(), getUserPassword());
    boolean authExpected = true;
    MainPage mainPage = new MainPage(getDriver());
    boolean authActual = mainPage.verifyAuth(getUserLogin());
    assertEquals(authExpected, authActual);
  }

  protected void assertPageIsDisplay() {
    boolean expected = true;
    boolean actual = loginPage.isDisplayed();
    assertEquals(expected, actual);
  }

  protected String getMomentOfTimeAsString(Calendar calendar) {
    return String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)) + "_"
        + String.valueOf(calendar.get(Calendar.MONTH)) + "_"
        + String.valueOf(calendar.get(Calendar.YEAR)) + "__"
        + String.valueOf(calendar.get(Calendar.HOUR_OF_DAY))
        + "_" + String.valueOf(calendar.get(Calendar.MINUTE))
        + "_" + String.valueOf(calendar.get(Calendar.SECOND));
  }
}
