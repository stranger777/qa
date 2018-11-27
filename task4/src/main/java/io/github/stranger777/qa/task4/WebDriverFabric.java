package io.github.stranger777.qa.task4;

import io.github.stranger777.qa.task4.singletons.ChromeDriverSingleton;
import io.github.stranger777.qa.task4.singletons.FirefoxDriverSingleton;
import io.github.stranger777.qa.task4.singletons.InternetExplorerDriverSingleton;
import org.openqa.selenium.WebDriver;

public class WebDriverFabric {

  public WebDriverFabric() {
  }

  /**
   * @param name
   * @return Webdriver by browser name
   */
  public WebDriver getByName(String name) {
    switch (name) {
      case "firefox":
        return FirefoxDriverSingleton.getInstance().getDriver();
      case "chrome":
        return ChromeDriverSingleton.getInstance().getDriver();
      case "iexplorer":
        return InternetExplorerDriverSingleton.getInstance().getDriver();
      default:
        throw new IllegalArgumentException("Expected String \"chrome\" or \"iexplorer\", or \"firefox\" \n Set it in test configuration file");
    }
  }
}
