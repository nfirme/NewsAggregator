import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import edu.calpoly.csc305.nfirme.parsers.NewsApiParser;
import edu.calpoly.csc305.nfirme.parsers.Parser;
import java.time.LocalDateTime;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;

class NewsApiParserTest {

  Logger logger = Logger.getLogger(NewsApiParser.class.getName());
  Parser parser = new NewsApiParser(logger);

  @Test
  void testParseValidDate() {
    String dateStr = "2021-03-24T22:32:00Z";
    LocalDateTime expected = LocalDateTime.of(2021, 3, 24, 22, 32);
    LocalDateTime actual = parser.parseDate(dateStr);
    assertEquals(expected, actual);
  }

  @Test
  void testParseInvalidDate() {
    assertNull(parser.parseDate("2021-0324T22:32:00Z"));
  }
}
