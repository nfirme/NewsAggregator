package edu.calpoly.csc305.nfirme.expressions;

import edu.calpoly.csc305.nfirme.article.Article;

public class KeywordExpression implements Expression {
  private final String keyword;

  public KeywordExpression(String keyword) {
    this.keyword = keyword;
  }

  @Override
  public boolean evaluate(Article article) {
    String title = article.getTitle().toLowerCase();
    String description = article.getDescription().toLowerCase();
    String url = article.getUrl().toLowerCase();
    String lowerKey = keyword.toLowerCase();

    if (title.contains(lowerKey)) {
      return true;
    }
    if (description.contains(lowerKey)) {
      return true;
    }
    if (url.contains(lowerKey)) {
      return true;
    }
    return article.getPublishedAt().toString().toLowerCase().contains(lowerKey);
  }

  @Override
  public String toString() {
    return this.keyword;
  }
}
