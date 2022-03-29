import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.calpoly.csc305.nfirme.article.Article;
import edu.calpoly.csc305.nfirme.expressions.AndExpression;
import edu.calpoly.csc305.nfirme.expressions.KeywordExpression;
import edu.calpoly.csc305.nfirme.expressions.OrExpression;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class ExpressionTest {
  @Test
  void evaluateKeywordExpressionPresent() {
    Article article = new Article(
        "title banana", "blah", "google.com", LocalDateTime.MAX);
    KeywordExpression exp = new KeywordExpression("banana");
    assertTrue(exp.evaluate(article));
  }

  @Test
  void evaluateKeywordExpressionNotPresent() {
    Article article = new Article(
        "title banana", "blah", "google.com", LocalDateTime.MAX);
    KeywordExpression exp = new KeywordExpression("apple");
    assertFalse(exp.evaluate(article));
  }

  @Test
  void evaluateOrExpressionPresent() {
    Article article = new Article(
        "title banana", "blah", "google.com", LocalDateTime.MAX);
    OrExpression orExp = new OrExpression(
        new KeywordExpression("apple"), new KeywordExpression("banana"));
    assertTrue(orExp.evaluate(article));
  }

  @Test
  void evaluateOrExpressionNotPresent() {
    Article article = new Article(
        "title banana", "blah", "google.com", LocalDateTime.MAX);
    OrExpression orExp = new OrExpression(
        new KeywordExpression("dog"), new KeywordExpression("cat"));
    assertFalse(orExp.evaluate(article));
  }

  @Test
  void evaluateAndExpressionPresent() {
    Article article = new Article(
        "title banana", "blahapple", "google.com", LocalDateTime.MAX);
    AndExpression andExp = new AndExpression(
        new KeywordExpression("apple"), new KeywordExpression("banana"));
    assertTrue(andExp.evaluate(article));
  }

  @Test
  void evaluateAndExpressionNotPresent() {
    Article article = new Article(
        "title banana", "blah", "google.com", LocalDateTime.MAX);
    AndExpression andExp = new AndExpression(
        new KeywordExpression("apple"), new KeywordExpression("banana"));
    assertFalse(andExp.evaluate(article));
  }
}
