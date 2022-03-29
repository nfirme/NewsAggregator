import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.calpoly.csc305.nfirme.article.Article;
import edu.calpoly.csc305.nfirme.datasources.DataSource;
import edu.calpoly.csc305.nfirme.datasources.FileSource;
import edu.calpoly.csc305.nfirme.parsers.SimpleParser;
import edu.calpoly.csc305.nfirme.processors.Processor;
import java.util.List;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;

class ProcessorTest {
  Logger logger = Logger.getLogger(SimpleParser.class.getName() + ".test");
  SimpleParser parser = new SimpleParser(logger);
  DataSource source = new FileSource("inputs/simple.json");

  @Test
  void testProcessSimple() {
    Processor processor = new Processor(parser, source);
    List<Article> articles = processor.process();
    Article article = articles.get(0);
    assertEquals(
        "Extend Assignment #1 to support multiple sources and to introduce source processor.",
        article.getDescription());
    assertEquals(
        "https://canvas.calpoly.edu/courses/55411/assignments/274503",
        article.getUrl());
    assertEquals("Assignment #2", article.getTitle());
  }
}