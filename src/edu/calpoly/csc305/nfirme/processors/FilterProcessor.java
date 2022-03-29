package edu.calpoly.csc305.nfirme.processors;

import edu.calpoly.csc305.nfirme.article.Article;
import edu.calpoly.csc305.nfirme.expressions.Expression;
import java.util.ArrayList;
import java.util.List;

public class FilterProcessor implements BaseProcessor {
  private final BaseProcessor processor;
  private final Expression filterExp;

  public FilterProcessor(BaseProcessor processor, Expression filterExp) {
    this.processor = processor;
    this.filterExp = filterExp;
  }

  /**
   * Calls the inner Processor's process() method and gets the list of Articles.
   * If the object's FilterExpresssion is null, returns that list.
   * Otherwise, applies the filter to the processor's list of articles.
   *
   * @return A list of Articles with filter expression applied, if not null
   */
  public List<Article> process() {
    List<Article> origList = processor.process();
    if (filterExp == null) {
      return origList;
    } else {
      List<Article> filteredList = new ArrayList<>();
      for (Article article : origList) {
        if (filterExp.evaluate(article)) {
          filteredList.add(article);
        }
      }
      return filteredList;
    }
  }
}
