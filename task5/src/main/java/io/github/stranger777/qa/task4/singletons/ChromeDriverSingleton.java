package io.github.stranger777.qa.task4.singletons;

import io.github.stranger777.qa.task5.Downloader;

import org.openqa.selenium.chrome.ChromeDriver;

public final class ChromeDriverSingleton {
  private static ChromeDriverSingleton ourInstance = new ChromeDriverSingleton();
  private ChromeDriver chromeDriver;

  private ChromeDriverSingleton() {
    chromeDriver = new ChromeDriver(Downloader.getChromeDownloadOptions());
  }

  public static ChromeDriverSingleton getInstance() {
    return ourInstance;
  }

  public ChromeDriver getDriver() {
    return chromeDriver;
  }
}
