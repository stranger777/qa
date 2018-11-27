package io.github.stranger777.qa.task4.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ConcreteCategoryPage extends PageObject {

  private static final String titleActivePageSelector = "Page__TitleActivePage";
  @FindBy(className = titleActivePageSelector)
  private WebElement titleActivePage;

  public ConcreteCategoryPage(WebDriver driver) {
    super(driver);
  }

  public String getTitleText() {
    return titleActivePage.getText();
  }
}
