package io.github.stranger777.qa.task5;

import io.github.stranger777.qa.task4.singletons.ChromeDriverSingleton;
import io.github.stranger777.qa.task4.singletons.FirefoxDriverSingleton;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

public class Downloader extends WebDriverFabric {

  private static final String SAFEBROWSING_ENABLED = "safebrowsing.enabled";
  private static final boolean ENABLED = true;
  private static final String PREFS = "prefs";
  private static final String NEVER_ASK_SAVE_TO_DISK = "browser.helperApps.neverAsk.saveToDisk";
  private static final String APPLICATION_OCTET_STREAM = "application/octet-stream";

  /**
   * Represent {@link FirefoxOptions} for using in {@link FirefoxDriver} initialization and into
   * {@link FirefoxDriverSingleton}.
   *
   * @return {@link FirefoxOptions} for using in {@link FirefoxDriver} initialization and into
   *     {@link FirefoxDriverSingleton}
   */
  public static FirefoxOptions firefoxDownloadOptions() {
    FirefoxProfile profile = new FirefoxProfile();
    profile.setPreference(getNeverAskSaveToDisk(), getApplicationOctetStream());
    return new FirefoxOptions().setProfile(profile);
  }

  private static String getNeverAskSaveToDisk() {
    return NEVER_ASK_SAVE_TO_DISK;
  }

  private static String getApplicationOctetStream() {
    return APPLICATION_OCTET_STREAM;
  }

  /**
   * Represent {@link ChromeOptions} for using in {@link FirefoxDriver} initialization and into
   * {@link ChromeDriverSingleton}.
   *
   * @return {@link ChromeOptions} for using in {@link FirefoxDriver} initialization and into {@link
   *     ChromeDriverSingleton}.
   */
  public static ChromeOptions getChromeDownloadOptions() {
    Map<String, Object> map = new HashMap<>();
    map.put(getSafebrowsingEnabled(), getEnabled());
    return new ChromeOptions().setExperimentalOption(getPrefs(), map);
  }

  private static String getSafebrowsingEnabled() {
    return SAFEBROWSING_ENABLED;
  }

  private static boolean getEnabled() {
    return ENABLED;
  }

  private static String getPrefs() {
    return PREFS;
  }
}
