package edu.calpoly.csc305.nfirme.expressions;

import edu.calpoly.csc305.nfirme.article.Article;

public class AndExpression implements Expression {
  private final Expression left;
  private final Expression right;

  public AndExpression(Expression left, Expression right) {
    this.left = left;
    this.right = right;
  }

  public boolean evaluate(Article article) {
    return left.evaluate(article) && right.evaluate(article);
  }

  @Override
  public String toString() {
    return String.format("%s && %s", left, right);
  }
}
