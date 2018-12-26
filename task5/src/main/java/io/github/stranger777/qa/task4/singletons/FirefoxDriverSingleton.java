package io.github.stranger777.qa.task4.singletons;

import io.github.stranger777.qa.task5.Downloader;

import org.openqa.selenium.firefox.FirefoxDriver;

public final class FirefoxDriverSingleton {
  private static FirefoxDriverSingleton ourInstance = new FirefoxDriverSingleton();

  private FirefoxDriver firefoxDriver;

  private FirefoxDriverSingleton() {
    firefoxDriver = new FirefoxDriver(Downloader.firefoxDownloadOptions());
  }

  public static FirefoxDriverSingleton getInstance() {
    return ourInstance;
  }

  public FirefoxDriver getDriver() {
    return firefoxDriver;
  }
}
