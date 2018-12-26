package io.github.stranger777.qa.task5;

import io.github.stranger777.xmlconfigreader.XMLConfigReader;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FileUtils;
import org.xml.sax.SAXException;

public class SelfWritedTaskConfigReader extends XMLConfigReader {
  private static final String FILE_SEPARATOR = "file.separator";
  /** Represent default path to test resources in mMaven projects. */
  private static final String SRC_TEST_RESOURCES =
          new StringBuilder()
              .append("src")
              .append(System.getProperty(FILE_SEPARATOR))
              .append("test")
              .append(System.getProperty(FILE_SEPARATOR))
              .append("resources")
              .append(System.getProperty(FILE_SEPARATOR))
              .toString();

  /**
   * Allocate memory for configuration reader.
   *
   * @param configName name of configuration file.
   * @throws ParserConfigurationException .
   * @throws IOException .
   * @throws SAXException .
   */
  public SelfWritedTaskConfigReader(String configName)
      throws ParserConfigurationException, IOException, SAXException {
    super(FileUtils.getFile(SRC_TEST_RESOURCES, configName).toURI());
  }

}
