package edu.calpoly.csc305.nfirme.parsers;

import edu.calpoly.csc305.nfirme.article.Article;
import java.io.Reader;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

public class NewsApiParser extends Parser {
  private final Logger logger;

  public NewsApiParser(Logger logger) {
    super(logger);
    this.logger = logger;
  }

  /**
   * Parses the article from JSON source.
   *
   * @param reader The file or url source to read from
   * @return A list of Article objects
   */
  public List<Article> getArticles(Reader reader) {
    JSONObject obj = getObjectFromReader(reader);
    JSONArray articles = obj.getJSONArray("articles");
    return parseArticles(articles);
  }

  private List<Article> parseArticles(JSONArray array) {
    List<Article> articles = new ArrayList<>();

    for (int i = 0; i < array.length(); i++) {
      try {
        JSONObject obj = array.getJSONObject(i);
        Article article = new Article(
            parseField(obj, "title"),
            parseField(obj, "description"),
            parseField(obj, "url"),
            parseDate(parseField(obj, "publishedAt"))
        );
        articles.add(article);
      } catch (NullPointerException e) {
        logger.warning(e.getMessage() + " (at index " + i + ")");
      }
    }

    return articles;
  }

  /**
   * Parses a String into a LocalDateTime to be stored in the Article.
   *
   * @param dateString The string to parse.
   * @return A LocalDateTime object representing the parsed string.
   */
  public LocalDateTime parseDate(String dateString) {
    try {
      return LocalDateTime.parse(dateString, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    } catch (DateTimeException e) {
      logger.warning(e.getMessage());
      return null;
    }
  }
}
