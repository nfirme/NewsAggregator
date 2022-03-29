package edu.calpoly.csc305.nfirme.visitors;

import edu.calpoly.csc305.config.grammars.AggregatorConfigParser;
import edu.calpoly.csc305.config.grammars.AggregatorConfigParserBaseVisitor;
import edu.calpoly.csc305.nfirme.expressions.AndExpression;
import edu.calpoly.csc305.nfirme.expressions.Expression;
import edu.calpoly.csc305.nfirme.expressions.KeywordExpression;
import edu.calpoly.csc305.nfirme.expressions.OrExpression;

public class FilterVisitor extends AggregatorConfigParserBaseVisitor<Expression> {
  @Override
  public Expression visitFileSourceType(AggregatorConfigParser.FileSourceTypeContext ctx) {
    return ctx.filter().accept(this);
  }

  @Override
  public Expression visitUrlSourceType(AggregatorConfigParser.UrlSourceTypeContext ctx) {
    return ctx.filter().accept(this);
  }

  // If no filter is supplied, the Expression returned will be null
  // FilterProcessor checks for a null Expression param
  // and returns the original list of articles if so.
  @Override
  public Expression visitFilter(AggregatorConfigParser.FilterContext ctx) {
    return ctx.selector() == null ? null : ctx.selector().accept(this);
  }

  @Override
  public Expression visitNestedExpression(AggregatorConfigParser.NestedExpressionContext ctx) {
    return ctx.children.get(1).accept(this);
  }

  @Override
  public Expression visitAndExpression(AggregatorConfigParser.AndExpressionContext ctx) {
    return new AndExpression(ctx.lft.accept(this), ctx.rht.accept(this));
  }

  @Override
  public Expression visitOrExpression(AggregatorConfigParser.OrExpressionContext ctx) {
    return new OrExpression(ctx.lft.accept(this), ctx.rht.accept(this));
  }

  @Override
  public Expression visitKeywordExpression(AggregatorConfigParser.KeywordExpressionContext ctx) {
    return new KeywordExpression(ctx.KEYWORD().toString());
  }
}
