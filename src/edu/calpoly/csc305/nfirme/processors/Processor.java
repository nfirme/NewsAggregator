package edu.calpoly.csc305.nfirme.processors;

import edu.calpoly.csc305.nfirme.article.Article;
import edu.calpoly.csc305.nfirme.datasources.DataSource;
import edu.calpoly.csc305.nfirme.parsers.Parser;
import java.util.ArrayList;
import java.util.List;

public class Processor implements BaseProcessor {
  private final Parser parser;
  private final DataSource data;

  public Processor(Parser parser, DataSource data) {
    this.parser = parser;
    this.data = data;
  }

  /**
   * Accesses the Reader from the data instances and calls the parser
   * to create a list of Article objects.
   *
   * @return A list of Article objects
   */
  public List<Article> process() {
    List<Article> articles = new ArrayList<>();
    try {
      articles.addAll(parser.getArticles(this.data.getReader()));
      data.cleanup();
    } catch (Exception e) {
      parser.logger().warning(e.getMessage());
    }
    return articles;
  }
}
