package edu.calpoly.csc305.nfirme.parsers;

import edu.calpoly.csc305.nfirme.article.Article;
import java.io.Reader;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public abstract class Parser {
  private final Logger logger;

  protected Parser(Logger logger) {
    this.logger = logger;
  }

  public abstract List<Article> getArticles(Reader reader);

  public abstract LocalDateTime parseDate(String dateString);

  public JSONObject getObjectFromReader(Reader reader) {
    JSONTokener token = new JSONTokener(reader);
    return new JSONObject(token);
  }

  /**
   * General purpose method to get the value of a given key from a JSON object.
   *
   * @param obj The JSONObject representing a single article.
   * @param key The key to get the value for.
   * @return A string representing the value from the key.
   */
  public String parseField(JSONObject obj, String key) {
    try {
      return obj.getString(key);
    } catch (JSONException e) {
      return null;
    }
  }

  public Logger logger() {
    return this.logger;
  }
}
