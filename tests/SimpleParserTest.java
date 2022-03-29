import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import edu.calpoly.csc305.nfirme.parsers.Parser;
import edu.calpoly.csc305.nfirme.parsers.SimpleParser;
import java.time.LocalDateTime;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;

class SimpleParserTest {

  Logger simpleLogger = Logger.getLogger(SimpleParser.class.getName());
  Parser simpleParser = new SimpleParser(simpleLogger);

  @Test
  void testParseValidDate() {
    String dateStr = "2021-06-12 18:00:30.102233";
    LocalDateTime expected = LocalDateTime.of(2021, 6, 12, 18, 0, 30, 102233000);
    assertEquals(expected, simpleParser.parseDate(dateStr));
  }

  @Test
  void testParseInvalidDate() {
    assertNull(simpleParser.parseDate("2021-06-12 :00:30.102233"));
  }
}
