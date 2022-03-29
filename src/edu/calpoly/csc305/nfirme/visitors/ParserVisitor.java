package edu.calpoly.csc305.nfirme.visitors;

import edu.calpoly.csc305.config.grammars.AggregatorConfigParser;
import edu.calpoly.csc305.config.grammars.AggregatorConfigParserBaseVisitor;
import edu.calpoly.csc305.nfirme.parsers.NewsApiParser;
import edu.calpoly.csc305.nfirme.parsers.Parser;
import edu.calpoly.csc305.nfirme.parsers.SimpleParser;
import java.util.logging.Logger;

public class ParserVisitor extends AggregatorConfigParserBaseVisitor<Parser> {
  private final Logger logger;

  public ParserVisitor(Logger logger) {
    this.logger = logger;
  }

  @Override
  public Parser visitFileSourceType(AggregatorConfigParser.FileSourceTypeContext ctx) {
    return ctx.format().accept(this);
  }

  @Override
  public Parser visitUrlSourceType(AggregatorConfigParser.UrlSourceTypeContext ctx) {
    return ctx.format().accept(this);
  }

  @Override
  public Parser visitFormat(AggregatorConfigParser.FormatContext ctx) {
    return ctx.format_option().accept(this);
  }

  @Override
  public Parser visitNewsApiFormat(AggregatorConfigParser.NewsApiFormatContext ctx) {
    return new NewsApiParser(logger);
  }

  @Override
  public Parser visitSimpleFormat(AggregatorConfigParser.SimpleFormatContext ctx) {
    return new SimpleParser(logger);
  }
}
