package io.github.stranger777.qa.task4.singletons;

import org.openqa.selenium.ie.InternetExplorerDriver;

public final class InternetExplorerDriverSingleton {
  private static InternetExplorerDriverSingleton ourInstance =
      new InternetExplorerDriverSingleton();
  private InternetExplorerDriver ie = new InternetExplorerDriver();

  private InternetExplorerDriverSingleton() {}

  public static InternetExplorerDriverSingleton getInstance() {
    return ourInstance;
  }

  public InternetExplorerDriver getDriver() {
    return ie;
  }
}
