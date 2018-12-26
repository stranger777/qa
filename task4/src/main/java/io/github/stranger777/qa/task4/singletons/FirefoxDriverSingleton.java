package io.github.stranger777.qa.task4.singletons;

import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxDriverSingleton {
  private static FirefoxDriverSingleton ourInstance;

  static {
    ourInstance = new FirefoxDriverSingleton();
  }

  private FirefoxDriver firefoxDriver;

  private FirefoxDriverSingleton() {
    firefoxDriver = new FirefoxDriver();
  }

  public static FirefoxDriverSingleton getInstance() {
    return ourInstance;
  }

  public FirefoxDriver getDriver() {
    return firefoxDriver;
  }
}
