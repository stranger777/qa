package io.github.stranger777.qa.task5.elements;

import org.openqa.selenium.By;

public class RootHtml extends BaseElement {

  private static final String HTML = "/html";

  public RootHtml() {
    super(By.xpath(HTML));
  }
}
