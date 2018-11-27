package io.github.stranger777.qa.task4.pageobjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class LoginPage extends PageObject {

  private static final String phoneSelector = "#LLoginForm_phone";
  private static final String passwordSelector = "#LLoginForm_password";
  private static final String buttonLoginSelector = ".Header__ButtonLogIn";
  private static final String loginTextSelector = ".Header__LoginLinkAuth > div:nth-child(1) > div:nth-child(1) > span:nth-child(1)";
  private static final String bodySelector = "body";
  private static final int timeOutInSeconds = 120;
  private static final String authOpenerSelector = ".Header__LoginLinkAuth";
  @FindBy(css = bodySelector)
  private WebElement body;
  @FindBy(css = loginTextSelector)
  private WebElement loginTextElement;
  @FindBy(css = phoneSelector)
  private WebElement loginFormPhone;
  @FindBy(css = passwordSelector)
  private WebElement LoginFormPassword;
  @FindBy(css = buttonLoginSelector)
  private WebElement loginButton;
  @FindBy(css = authOpenerSelector)
  private WebElement authopener;
  private String innerHTML;


  public LoginPage(WebDriver driver) {
    super(driver);
  }

  private WebElement getCliclableAuthOpener() {
    return (new WebDriverWait(driver, timeOutInSeconds)).ignoring(NoSuchElementException.class)
        .until(ExpectedConditions.elementToBeClickable(authopener));
  }

  public String getEnterText() {
    FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver)
        .ignoring(NoSuchElementException.class)
        .withTimeout(timeOutInSeconds, TimeUnit.SECONDS);
    return fluentWait.until(ExpectedConditions.elementToBeClickable(loginTextElement)).getText();
  }

  public boolean isDisplayed() {
    return this.body.isDisplayed();
  }

  /**
   * @param phone
   * @param password
   */
  public void authActions(String phone, String password) {
    Actions actions = new Actions(driver);
    WebElement cliclableAuthOpener = getCliclableAuthOpener();
    actions.click(cliclableAuthOpener);
    actions.build().perform();

    LoginFormPassword.sendKeys(password);
    // click() for avoid telephone number autofill
    loginFormPhone.click();
    loginFormPhone.sendKeys(phone);
    loginButton.click();
  }
}