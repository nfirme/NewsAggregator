package edu.calpoly.csc305.nfirme.expressions;

import edu.calpoly.csc305.nfirme.article.Article;

public interface Expression {
  boolean evaluate(Article article);
}
