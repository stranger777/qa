package io.github.stranger777.qa.task5.elements;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ListOfElements extends BaseElement {

  public ListOfElements(By ctorLocator) {
    super(ctorLocator);
  }

  /**
   * Returns list {@link WebElement}, when they presented.
   *
   * @return list {@link WebElement }
   */
  public final List<WebElement> getListOfPresenceWebElements() {
    getIgnoringNoSuchElement()
        .until(ExpectedConditions.presenceOfAllElementsLocatedBy(getLocator()));
    return getDriver().findElements(getLocator());
  }
}
