package io.github.stranger777.qa.task4.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.concurrent.TimeUnit;

public class MainPage extends PageObject {
  private static final String menuOpenerSelector = "div.Header__BlockNameUser";
  private static final String logoutSelector = "#yt0";
  private static final String allCatalogLinkSelector = "a.Header__CatalogAllLink";
  private static final int duration = 30;
  private static final String HREF = "href";

  public MainPage(WebDriver driver) {
    super(driver);
  }

  public void logOut() {
    getUsername().click();
    FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver)
        .ignoring(NoSuchElementException.class);
    WebElement logout = fluentWait.until
        (ExpectedConditions.elementToBeClickable
            (new By.ByCssSelector(logoutSelector)));
    logout.click();
  }

  private WebElement getUsername() {
    FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver)
        .ignoring(NoSuchElementException.class).withTimeout(duration, TimeUnit.SECONDS);
    return fluentWait.until
        (ExpectedConditions.presenceOfElementLocated(new By.ByCssSelector(menuOpenerSelector)));
  }

  public void getAllCatalog() {
    FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver).ignoring(NoSuchElementException.class);
    WebElement catalogAllLink = fluentWait.until
        (ExpectedConditions.elementToBeClickable
            (new By.ByCssSelector(allCatalogLinkSelector)));
    String href = catalogAllLink.getAttribute(HREF);
    driver.get(href);
  }

  public boolean verifyAuth(String usernameString) {
    return this.getUsername().getText().equals(usernameString);
  }
}
