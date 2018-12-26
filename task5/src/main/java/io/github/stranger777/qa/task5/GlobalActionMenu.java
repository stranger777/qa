package io.github.stranger777.qa.task5;

import io.github.stranger777.qa.task5.elements.Button;
import io.github.stranger777.qa.task5.elements.ListOfElements;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;

public class GlobalActionMenu {
  private static final String MENU_ROOT = "//div[@id='global_action_menu']/";

  public final void navigateToInstallSteamPage() {
    this.getElement(Elements.INSTALL_STEAM).click();
  }

  /**
   * Get {@link Button} by element enumeration.
   *
   * @param elementDiff concrete element of {@link Elements} enumeration.
   * @return Top menu {@link Button}.
   */
  public final Button getElement(Elements elementDiff) {
    return new Button(By.xpath(getRoot() + elementDiff.toString()));
  }

  private String getRoot() {
    return MENU_ROOT;
  }

  /**
   * Get list of elements by element enumeration.
   *
   * @param elementDiff concrete element of {@link Elements} enumeration.
   * @return ListOfElements {@link ListOfElements}.
   */
  public final ListOfElements getElements(Elements elementDiff) {
    return new ListOfElements(
        By.xpath(new StringBuilder().append(getRoot()).append(elementDiff.toString()).toString()));
  }

  public enum Elements {
    INSTALL_STEAM(Elements.Constants.INSTALL_STEAM),
    OPEN_LANG_MENU(Elements.Constants.OPEN_LANG_MENU),
    LANG_MENU_LIST(Elements.Constants.LANG_MENU_LIST),
    CHANGE_LANG(getChangeLangSelector());

    private String root;

    Elements(String root) {
      this.root = root;
    }

    /**
     * Returns {@link String} XPath expression for using in {@link #CHANGE_LANG}.
     *
     * @return {@link String} XPaath expression for using in {@link #CHANGE_LANG}.
     */
    private static String getChangeLangSelector() {
      final String capitalize = StringUtils.capitalize(TestData.LANG.toString());
      return String.format(Constants.CHANGE_LANG_SELECTOR_STRING, capitalize);
    }

    @Override
    public String toString() {
      return this.root;
    }

    private static class Constants {
      private static final String INSTALL_STEAM = "div[1]/a";
      private static final String OPEN_LANG_MENU = "span";
      private static final String LANG_MENU_LIST = "div[2]/div/a";
      private static final String CHANGE_LANG_SELECTOR_STRING =
          "div[2]/div/a[contains(text(),'%s')]";
    }
  }
}
