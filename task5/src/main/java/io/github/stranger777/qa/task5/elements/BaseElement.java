package io.github.stranger777.qa.task5.elements;

import io.github.stranger777.qa.task5.WebDriverFabric;

import java.time.Duration;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class BaseElement extends WebDriverFabric {
  private static final int TIMEOUT_MINUTES = 5;
  private FluentWait<WebDriver> ignoringNoSuchElement;
  private By locator;

  /**
   * Allocate memory for WebElement wrapper, that ignoring {@link
   * NoSuchElementException} {@value #TIMEOUT_MINUTES} minutes.
   *
   * @param ctorLocator {@link By} locator.
   */
  public BaseElement(By ctorLocator) {
    this.locator = ctorLocator;
    this.ignoringNoSuchElement =
        new FluentWait<>(getDriver())
            .ignoring(NoSuchElementException.class)
            .withTimeout(Duration.ofMinutes(TIMEOUT_MINUTES));
  }

  public void click() {
    getClickableWebElementWhenPresented().click();
  }

  private WebElement getClickableWebElementWhenPresented() {
    waitForPresent();
    return getIgnoringNoSuchElement().until(ExpectedConditions.elementToBeClickable(getLocator()));
  }

  /** Wait for a {@link WebElement}
   * has been presented in DOM. */
  public void waitForPresent() {
    getIgnoringNoSuchElement().until(ExpectedConditions.presenceOfElementLocated(getLocator()));
  }

  FluentWait<WebDriver> getIgnoringNoSuchElement() {
    return ignoringNoSuchElement;
  }

  By getLocator() {
    return this.locator;
  }

  /**
   * Returns {@link WebElement} when WebElement contain text node matches with regex.
   *
   * @param regex regular expression that expected in element.
   * @return {@link WebElement} that contain text node matches with regex.
   */
  public WebElement getWebElementWhenTextMatches(String regex) {
    getIgnoringNoSuchElement()
        .until(ExpectedConditions.textMatches(getLocator(), Pattern.compile(regex)));
    return getDriver().findElement(getLocator());
  }

  /**
   * Move to {@link WebElement} when clickable and return this element (usually for click)
   * Returns {@link WebElement}.
   * @return {@link WebElement} .
   */
  public WebElement moveToAndGetClickableElement() {
    new Actions(getDriver()).moveToElement(getClickableWebElementWhenPresented()).build().perform();
    return getDriver().findElement(getLocator());
  }

  public void moveToClickableElement() {
    new Actions(getDriver()).moveToElement(getClickableWebElementWhenPresented()).build().perform();
  }

  public void waitForExists() {
    getIgnoringNoSuchElement().until(this::waitForElementExisting);
  }

  private Boolean waitForElementExisting(WebDriver input) {
    return !input.findElements(getLocator()).isEmpty();
  }

  /**
   * Represent existing element on page.
   *
   * @return {@code boolean}
   */
  public boolean isExisting() {
    getIgnoringNoSuchElement()
        .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("html")));
    return waitForElementExisting(getDriver());
  }

  public WebElement getElement() {
    return getDriver().findElement(getLocator());
  }
}
