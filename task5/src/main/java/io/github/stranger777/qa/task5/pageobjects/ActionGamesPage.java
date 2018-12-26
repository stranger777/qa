package io.github.stranger777.qa.task5.pageobjects;

import io.github.stranger777.qa.task5.TestData;
import io.github.stranger777.qa.task5.WebDriverFabric;
import io.github.stranger777.qa.task5.elements.Label;
import io.github.stranger777.qa.task5.elements.ListOfElements;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ActionGamesPage extends WebDriverFabric {
  private static final String REGEX_DISCOUNT = "^-\\d{1,2}%$";
  private static final String BUNDLE = "bundle";
  private static final String DISCOUNT_PCT =
      "div.discount_block.game_purchase_discount div.discount_pct";
  private static final String BUNDLE_DISCOUNT_PCT =
      "//*[@id='game_area_purchase']/div/div/div/div[1]/div[2]";
  private static final String FALSE = "false";
  private static final String HREF = "href";
  private static final String REGEX_ONLY_DIGITS = "\\D*";
  private static final String TOP_SELLERS_DIV = "//*[@id='tab_select_TopSellers']/div";
  private static final String DISCOUNT_LIST = "a.tab_item div.discount_block div.discount_pct";

  /** . */
  public void assertDiscountExists() {
    this.selectTopSellers();
    /* assert that discount offer is exist (find in comments for this task) */
    Assertions.assertTrue(!this.getDiscountWebElements().isEmpty());
  }

  private void selectTopSellers() {
    new Label(new By.ByXPath(getTopSellersDiv())).click();
  }

  private List<WebElement> getDiscountWebElements() {
    List<WebElement> discountPercentageList;
    discountPercentageList =
        new ListOfElements(new By.ByCssSelector(getDiscountList())).getListOfPresenceWebElements();
    discountPercentageList.removeIf(ActionGamesPage::removeInvalidElements);
    if (Objects.equals(TestData.WITH_BUNDLE.toString(), getFalse())) {
      discountPercentageList.removeIf(ActionGamesPage::removeBundles);
    }
    return discountPercentageList;
  }

  private static String getTopSellersDiv() {
    return TOP_SELLERS_DIV;
  }

  private static String getDiscountList() {
    return DISCOUNT_LIST;
  }

  private static boolean removeInvalidElements(WebElement webElement) {
    return !(webElement.getText().matches(getRegexDiscount()));
  }

  private static String getFalse() {
    return FALSE;
  }

  private static boolean removeBundles(WebElement webElement) {
    return webElement.getAttribute(getHref()).contains(getBundle());
  }

  private static String getRegexDiscount() {
    return REGEX_DISCOUNT;
  }

  private static String getHref() {
    return HREF;
  }

  private static String getBundle() {
    return BUNDLE;
  }

  /** Assert that discount equals on various pages. */
  public void assertDiscountTextOnVariousPages() {
    WebElement maxDiscountWebElement = this.getMaxDiscountWebElement(getDiscountWebElements());
    final String maxDiscountInList = maxDiscountWebElement.getText();
    maxDiscountWebElement.click();
    new AgeBlockerPage().ageBlockerHandler();
    Assertions.assertEquals(maxDiscountInList, getGameDiscount());
  }

  /** See method name. */
  private WebElement getMaxDiscountWebElement(List<WebElement> discountPercentageList) {
    discountPercentageList.sort(Comparator.comparingInt(ActionGamesPage::applyAsInt));
    return discountPercentageList.get(discountPercentageList.size() - NumberUtils.SHORT_ONE);
  }

  /** See method name. */
  private String getGameDiscount() {
    if (getDriver().getCurrentUrl().contains(getBundle())) {
      return new Label(new By.ByXPath(getBundleDiscountPct()))
          .getWebElementWhenTextMatches(getRegexDiscount())
          .getText();
    }
    return new Label(new By.ByCssSelector(getDiscountPct())).getElement().getText();
  }

  /** Internal method for discount list sorting. */
  private static int applyAsInt(WebElement o) {
    return Integer.parseInt(o.getText().replaceAll(getRegexOnlyDigits(), StringUtils.EMPTY));
  }

  private static String getBundleDiscountPct() {
    return BUNDLE_DISCOUNT_PCT;
  }

  private static String getDiscountPct() {
    return DISCOUNT_PCT;
  }

  private static String getRegexOnlyDigits() {
    return REGEX_ONLY_DIGITS;
  }
}
