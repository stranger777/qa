package io.github.stranger777.qa.task5;

import io.github.stranger777.qa.task4.singletons.ChromeDriverSingleton;
import io.github.stranger777.qa.task4.singletons.FirefoxDriverSingleton;
import io.github.stranger777.qa.task4.singletons.InternetExplorerDriverSingleton;

import org.openqa.selenium.WebDriver;

public class WebDriverFabric {

  private static final String FIREFOX = "firefox";
  private static final String CHROME = "chrome";
  private static final String IEXPLORER = "iexplorer";
  private static final String MESSAGE =
      new StringBuilder()
          .append("Expected String ")
          .append("\"chrome\" or \"iexplorer\", or \"firefox\" \n ")
          .append("Set it in test configuration file")
          .toString();

  /**
   * Allocate memory for
   * {@link WebDriverFabric}.
   */
  protected WebDriverFabric() {}

  /**
   * Get concrete {@link WebDriver} instance by name from test configuration file that reading with
   * {@link SelfWritedTaskConfigReader}.
   *
   * @return {@link WebDriver}
   * @see SelfWritedTaskConfigReader
   */
  protected static WebDriver getDriver() {
    switch ((TestData.BROWSER.toString())) {
      case FIREFOX:
        return FirefoxDriverSingleton.getInstance().getDriver();
      case CHROME:
        return ChromeDriverSingleton.getInstance().getDriver();
      case IEXPLORER:
        return InternetExplorerDriverSingleton.getInstance().getDriver();
      default:
        throw new IllegalArgumentException(MESSAGE);
    }
  }
}
