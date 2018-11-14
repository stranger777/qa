import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShopByReviewTest extends FunctionalTest {
    WebDriver driver;

    public ShopByReviewTest() throws
            IOException,
            SAXException,
            ParserConfigurationException,
            URISyntaxException,
            XPathExpressionException {
        super();
    }

    @Test
    public void testEx(){
        assertEquals(siteURL, "");
    }


}
