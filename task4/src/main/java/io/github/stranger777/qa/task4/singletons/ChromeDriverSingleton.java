package io.github.stranger777.qa.task4.singletons;

import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeDriverSingleton {
  private static ChromeDriverSingleton ourInstance = new ChromeDriverSingleton();
  private ChromeDriver chromeDriver = new ChromeDriver();

  private ChromeDriverSingleton() {
  }

  public static ChromeDriverSingleton getInstance() {
    return ourInstance;
  }

  public ChromeDriver getDriver() {
    return chromeDriver;
  }
}
