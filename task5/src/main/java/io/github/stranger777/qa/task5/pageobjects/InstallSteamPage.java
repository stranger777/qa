package io.github.stranger777.qa.task5.pageobjects;

import io.github.stranger777.qa.task5.elements.Button;

import org.openqa.selenium.By;

public class InstallSteamPage {

  private static final String INSTALL_STEAM_BUTTON = "//*[@id=\"about_install_steam_link\"]";

  public final void clickForSteamDownload() {
    new Button(new By.ByXPath(INSTALL_STEAM_BUTTON)).click();
  }
}
