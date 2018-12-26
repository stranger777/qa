package io.github.stranger777.qa.task5.pageobjects;

import io.github.stranger777.qa.task5.WebDriverFabric;
import io.github.stranger777.qa.task5.elements.Button;
import io.github.stranger777.qa.task5.elements.SelectElement;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class AgeBlockerPage extends WebDriverFabric {

  private static final String AGECHECK = "agecheck";
  private static final String OPEN_AGEGATE_BUTTON = "div.btns > a:nth-child(1)";
  private static final String ONE_NUMBER_STRING = "1";

  /**
   * Invoke {@link #birthDaySelectHandler()}, then
   * click to unlock age gate.
   */
  public final void ageBlockerHandler() {
    final String currentUrl = getDriver().getCurrentUrl();
    if (currentUrl.contains(AGECHECK)) {
      birthDaySelectHandler();
      clickForUnlockAgeGate();
    }
  }

  /**
   * If exist age select, move to age select element, then
   * send key {@value #ONE_NUMBER_STRING} for set 1900 - valid year,
   * close select element.
   */
  private void birthDaySelectHandler() {
    SelectElement selectElement = new SelectElement(new By.ByXPath("//select[@id='ageYear']"));
    if (selectElement.isExisting()) {
      selectElement.moveToAndGetClickableElement().click();
      selectElement.getElement().sendKeys(ONE_NUMBER_STRING + Keys.ENTER);
    }
  }

  /** See method name. */
  private void clickForUnlockAgeGate() {
    new Button(new By.ByCssSelector(OPEN_AGEGATE_BUTTON)).click();
  }
}
