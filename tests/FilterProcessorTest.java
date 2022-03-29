import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import edu.calpoly.csc305.nfirme.article.Article;
import edu.calpoly.csc305.nfirme.expressions.AndExpression;
import edu.calpoly.csc305.nfirme.expressions.KeywordExpression;
import edu.calpoly.csc305.nfirme.expressions.OrExpression;
import edu.calpoly.csc305.nfirme.processors.FilterProcessor;
import edu.calpoly.csc305.nfirme.processors.Processor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class FilterProcessorTest {
  @Test
  void testFilterProcessorNoFilter() {
    List<Article> articles = new ArrayList<>();
    articles.add(new Article(
        "Assignment #2",
        "Extend Assignment #1 to support multiple sources and to introduce source processor.",
        "https://canvas.calpoly.edu/courses/55411/assignments/274503",
        LocalDateTime.MAX));
    Processor mockedProcessor = mock(Processor.class);
    when(mockedProcessor.process()).thenReturn(articles);
    FilterProcessor filterProcessor = new FilterProcessor(mockedProcessor, null);
    List<Article> filteredArticles = filterProcessor.process();
    assertEquals(articles, filteredArticles);
  }

  @Test
  void testFilterProcessorWithFilter() {
    List<Article> articles = new ArrayList<>();
    Article result1 = new Article(
        "Vice President Kamala Harris to lead White House efforts to stem migration"
          + " at the border - USA TODAY",
        "Vice President Kamala Harris will  work on establishing a partnership"
          + " with Mexico and the northern triangle of Honduras, Guatemala and El Salvador.",
        "https://www.usatoday.com/story/news/politics/2021/03/24/"
          + "kamala-harris-lead-biden-admin-stemming-migration-border/6984000002/",
        LocalDateTime.of(2021, 3, 24, 21, 33, 45));
    Article result2 = new Article("blah", "blah", "blah", LocalDateTime.MAX);
    articles.add(result1);
    articles.add(result2);

    Processor mockedProcessor = mock(Processor.class);
    when(mockedProcessor.process()).thenReturn(articles);

    AndExpression filterExp = new AndExpression(
        new KeywordExpression("kamala"),
        new OrExpression(
            new KeywordExpression("washingtonpost"),
            new KeywordExpression("usatoday")));

    FilterProcessor filterProcessor = new FilterProcessor(mockedProcessor, filterExp);
    List<Article> filteredArticles = filterProcessor.process();

    assertEquals(1, filteredArticles.size());
  }
}