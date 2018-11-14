package io.github.stranger777.qa.task4;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public enum WebDriverSingleton {
    WEB_DRIVER_SINGLETON;
    private FirefoxDriver firefoxDriver = new FirefoxDriver();
    private ChromeDriver chromeDriver = new ChromeDriver();
    private InternetExplorerDriver internetExplorerDriver = new InternetExplorerDriver();

    public WebDriver getInstance(String browserName) {

        switch (browserName) {
            case "firefox":
                return firefoxDriver;
            case "chrome":
                return chromeDriver;
            case "iexplorer":
                return internetExplorerDriver;
            default:
                throw new IllegalArgumentException("expected String iexplorer, chrome, firefox");
        }

    }
}
