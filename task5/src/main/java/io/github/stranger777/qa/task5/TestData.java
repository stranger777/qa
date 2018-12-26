package io.github.stranger777.qa.task5;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

/** Represent enuemration for test configuration values. */
public enum TestData {
  /** Represent browser name. */
  BROWSER(Constants.BROWSER),
  /** Represent Steam Installer size (not on harddrive) in bytes. */
  INSTALLER_SIZE_BYTES(Constants.BYTES),
  /** Represent Steam language, Russian or English. */
  LANG(Constants.LANGUAGE),
  /** Represent Steam URL. */
  URL(Constants.URL),
  /** Represent rule for bundles processing. */
  WITH_BUNDLE(Constants.WITHBUNDLE);

  private static final String CONFIG_NAME = "taskconfig.xml";
  private static final String XPATH_PREFIX = "//";
  private final String string;

  TestData(String s) {
    this.string = s;
  }

  @Override
  public String toString() {
    try {
      return new SelfWritedTaskConfigReader(CONFIG_NAME)
          .getTextFromNodeByXpathString(XPATH_PREFIX + this.string);
    } catch (XPathExpressionException
        | ParserConfigurationException
        | IOException
        | SAXException e) {
      Logger.getAnonymousLogger().log(Level.ALL, e.getMessage());
    }
    throw new IllegalArgumentException();
  }

  private static class Constants {
    private static final String BYTES = "bytes";
    private static final String LANGUAGE = "language";
    private static final String URL = "url";
    private static final String WITHBUNDLE = "withbundle";
    private static final String BROWSER = "browser";
  }
}
