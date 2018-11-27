package io.github.stranger777.qa.task4.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

public class AllCategoriesPage extends PageObject {
  private String categoriesListSelector = "div.Page__ElementPageCatalog div a";
  private WebElement concreteElem = getRandomCategoryElement();

  public AllCategoriesPage(WebDriver driver) {
    super(driver);
  }

  private List<WebElement> getCategoriesList() {
    return driver.findElements(By.cssSelector(categoriesListSelector));
  }

  private WebElement getRandomCategoryElement() {
    return getCategoriesList().get(new Random().nextInt(getCategoriesList().size()));
  }

  public String getConcreteCategoryName() {
    return concreteElem.getAttribute("innerHTML");
  }

  public void getConcreteCategoryPage() {
    driver.get(concreteElem.getAttribute("href"));
  }
}
