package io.github.stranger777.qa.task5;

import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class JunitAnnotatedMethods extends WebDriverFabric {
  /** Setup browser. Maximize browser window. Navigate to URL from config. */
  @BeforeAll
  public static void beforeAll() {
    WebDriverManager.getInstance(
            DriverManagerType.valueOf(TestData.BROWSER.toString().toUpperCase()))
        .setup();
    getDriver().manage().window().maximize();
    getDriver().get(TestData.URL.toString());
  }

  /** Delete all cookies. Close browser. */
  @AfterAll
  public static void afterAll() {
    getDriver().manage().deleteAllCookies();
    getDriver().close();
  }
}
