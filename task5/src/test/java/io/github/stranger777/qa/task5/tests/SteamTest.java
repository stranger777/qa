package io.github.stranger777.qa.task5.tests;

import com.sun.org.apache.xml.internal.dtm.ref.DTMNodeList;
import io.github.stranger777.qa.task5.GlobalActionMenu;
import io.github.stranger777.qa.task5.JunitAnnotatedMethods;
import io.github.stranger777.qa.task5.SelfWritedTaskConfigReader;
import io.github.stranger777.qa.task5.TestData;
import io.github.stranger777.qa.task5.elements.Label;
import io.github.stranger777.qa.task5.pageobjects.ActionGamesPage;
import io.github.stranger777.qa.task5.pageobjects.AgeBlockerPage;
import io.github.stranger777.qa.task5.pageobjects.InstallSteamPage;
import io.github.stranger777.qa.task5.pageobjects.MainPage;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.stream.IntStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.FluentWait;
import org.xml.sax.SAXException;

class SteamTest extends JunitAnnotatedMethods {

  private static final int WAIT_FOR_FILE_DOWNLOAD_HOURS = 1;
  private static final String APP_NAME = "//div[@class='apphub_AppName']";
  private static final String FILE_NAME = "SteamSetup.exe";
  private static final String APP = "app";
  private static final String FILE_SEPARATOR = "file.separator";
  private static final String AGEBLOCKER_URL = "//ageblocker/url";
  private static final String CONFIG_NAME = "taskconfig.xml";
  private static final String BUNDLE = "bundle";
  private static final String BUNDLE_NAME =
      "/html/body/div[1]/div[7]/div[3]/div[1]/div[2]/div/div[1]/h2";
  private static final String PAGE_IS_INCORRECT = "Page is incorrect";

  @Test
  void test() {
    MainPage mainPage = new MainPage();
    mainPage.languageHandler();
    mainPage.navigateToActionsViaMenu();

    ActionGamesPage actionGamesPage = new ActionGamesPage();
    actionGamesPage.assertDiscountExists();
    actionGamesPage.assertDiscountTextOnVariousPages();

    new GlobalActionMenu().navigateToInstallSteamPage();
    new InstallSteamPage().clickForSteamDownload();

    assertThatSteamInstallerDownloaded(FILE_NAME);
  }

  private void assertThatSteamInstallerDownloaded(String fileName) {
    File file =
        new File(
            new StringBuilder()
                .append(FileUtils.getUserDirectoryPath())
                .append(getDownloadFullPathName(fileName))
                .toString());

    new FluentWait<>(file)
        .withTimeout(Duration.ofHours(getWaitForFileDownloadHours()))
        .until(SteamTest::isSteamInstallerDownloaded);
    Assertions.assertTrue(file.exists() && file.length() == getIntInstallerSizeBytes());
    file.deleteOnExit();
  }

  static String getDownloadFullPathName(String fileName) {
    return new StringBuilder()
        .append(String.format("%sDownloads", getFileSeparator()))
        .append(getFileSeparator())
        .append(fileName)
        .toString();
  }

  static int getWaitForFileDownloadHours() {
    return WAIT_FOR_FILE_DOWNLOAD_HOURS;
  }

  /**
   * Wait for file size equals file size in selfwrited XML config.
   *
   * @param file file for waiting.
   * @return {@code boolean}
   */
  private static boolean isSteamInstallerDownloaded(File file) {
    return file.length() == getIntInstallerSizeBytes();
  }

  static int getIntInstallerSizeBytes() {
    return Integer.parseInt(TestData.INSTALLER_SIZE_BYTES.toString());
  }

  private static String getFileSeparator() {
    return System.getProperty(FILE_SEPARATOR);
  }

  @Test
  void test2()
      throws IOException, SAXException, ParserConfigurationException, XPathExpressionException {
    final DTMNodeList nodeSet =
        new SelfWritedTaskConfigReader(CONFIG_NAME).getNodeSet(AGEBLOCKER_URL);
    int bound = nodeSet.getLength();
    IntStream.range(NumberUtils.SHORT_ZERO, bound)
        .forEach(
            i -> {
              getDriver().get(nodeSet.item(i).getTextContent());
              new AgeBlockerPage().ageBlockerHandler();
              assertThatPageIsCorrect();
            });
  }

  private void assertThatPageIsCorrect() {
    Label label = null;
    if (getDriver().getCurrentUrl().contains(BUNDLE)) {
      label = new Label(By.xpath(BUNDLE_NAME));
    } else if (getDriver().getCurrentUrl().contains(APP)) {
      label = new Label(By.xpath(getAppName()));
    }

    if (label != null) {
      // Label#waitForPresent do not work (JS specific)
      label.waitForExists();
      Assertions.assertTrue(
          getDriver()
              .getTitle()
              .toLowerCase()
              .contains(label.getElement().getText().toLowerCase()));
    } else {
      Assertions.fail(PAGE_IS_INCORRECT);
    }
  }

  static String getAppName() {
    return APP_NAME;
  }
}
