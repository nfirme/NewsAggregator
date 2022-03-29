import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import edu.calpoly.csc305.nfirme.article.Article;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class ArticleTest {
  @Test
  void testConstructorWithGoodParams() {
    Article article = new Article(
        "Title",
        "Description",
        "https://google.com",
        LocalDateTime.of(2021, 10, 26, 10, 30));
    assertEquals("Title", article.getTitle());
    assertEquals("Description", article.getDescription());
    assertEquals("https://google.com", article.getUrl());
    assertEquals(LocalDateTime.of(2021, 10, 26, 10, 30), article.getPublishedAt());
  }

  @Test
  void testConstructorWithNullTitle() {
    LocalDateTime date = LocalDateTime.of(2021, 10, 26, 10, 30);
    Exception exception = assertThrows(NullPointerException.class, () -> {
      Article article = new Article(null, "Description", "https://google.com", date);
    });
    assertEquals("Title field must not be null", exception.getMessage());
  }

  @Test
  void testConstructorWithNullDescription() {
    LocalDateTime date = LocalDateTime.of(2021, 10, 26, 10, 30);
    Exception exception = assertThrows(NullPointerException.class, () -> {
      Article article = new Article("Title", null, "https://google.com", date);
    });
    assertEquals("Description field must not be null", exception.getMessage());
  }

  @Test
  void testConstructorWithNullUrl() {
    LocalDateTime date = LocalDateTime.of(2021, 10, 26, 10, 30);
    Exception exception = assertThrows(NullPointerException.class, () -> {
      Article article = new Article("Title", "Description", null, date);
    });
    assertEquals("URL field must not be null", exception.getMessage());
  }

  @Test
  void testConstructorWithNullDate() {
    Exception exception = assertThrows(NullPointerException.class, () -> {
      Article article = new Article("Title", "Description", "https://google.com", null);
    });
    assertEquals("publishedAt field must not be null", exception.getMessage());
  }

  @Test
  void testToString() {
    Article article = new Article(
      "Title",
      "Description",
      "https://google.com",
      LocalDateTime.of(2021, 10, 26, 10, 30)
    );
    String expected = "Title\nDescription\n2021-10-26T10:30\nhttps://google.com\n";
    assertEquals(expected, article.toString());
  }
}
