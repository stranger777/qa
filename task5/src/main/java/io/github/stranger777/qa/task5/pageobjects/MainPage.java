package io.github.stranger777.qa.task5.pageobjects;

import io.github.stranger777.qa.task5.GlobalActionMenu;
import io.github.stranger777.qa.task5.GlobalActionMenu.Elements;
import io.github.stranger777.qa.task5.TestData;
import io.github.stranger777.qa.task5.elements.Button;
import io.github.stranger777.qa.task5.elements.RootHtml;

import org.openqa.selenium.By;

public class MainPage {
  private static final String REGEX_WORD = "\\w";
  private static final String LANG = "lang";
  private static final String GENRE_TAB_OPENER = "//*[@id='genre_tab']/span/span";
  private static final String GENRE_ACTION = "//div[@id='genre_flyout']//a[%s]";
  private static final String ENGLISH = "english";
  private static final String RUSSIAN = "russian";
  private static final String SEVEN_STRING = "7";
  private static final String SIXTEEN_STRING = "16";

  /** Select language via UI if needed. */
  public final void languageHandler() {
    if (!TestData.LANG.toString().toLowerCase().startsWith(getHtmlLangAttribute())) {
      GlobalActionMenu dropDownMenu;
      dropDownMenu = new GlobalActionMenu();
      dropDownMenu.getElement(Elements.OPEN_LANG_MENU).click();
      dropDownMenu.getElements(Elements.LANG_MENU_LIST).waitForPresent();
      dropDownMenu
          .getElement(Elements.CHANGE_LANG)
          .moveToAndGetClickableElement()
          .click();
      dropDownMenu
          .getElement(Elements.OPEN_LANG_MENU)
          .getWebElementWhenTextMatches(REGEX_WORD);
    }
  }

  /** See method name. */
  private String getHtmlLangAttribute() {
    final RootHtml rootHtml = new RootHtml();
    rootHtml.waitForPresent();
    return rootHtml.getElement().getAttribute(LANG);
  }

  /** See method name. */
  public final void navigateToActionsViaMenu() {
    this.openFlyoutMenu();
    this.openActionPage();
  }

  /** See method name. */
  private void openFlyoutMenu() {
    new Button(new By.ByXPath(GENRE_TAB_OPENER)).moveToClickableElement();
  }

  /** See method name. */
  private void openActionPage() {
    new Button(new By.ByXPath(getXpathExpressionActionGenreButton()))
        .moveToAndGetClickableElement()
        .click();
  }

  /** See method name. */
  private String getXpathExpressionActionGenreButton() {
    /*
    use String.format() for readability
    and GENRE_ACTION constant
    */
    return String.format(GENRE_ACTION, getCorrectIndexByLanguage());
  }

  /** See method name. */
  private String getCorrectIndexByLanguage() {
    String s = TestData.LANG.toString();
    if (ENGLISH.equals(s)) {
      return SEVEN_STRING;
    } else if (RUSSIAN.equals(s)) {
      return SIXTEEN_STRING;
    }
    throw new IllegalArgumentException();
  }
}
