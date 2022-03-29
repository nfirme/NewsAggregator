package edu.calpoly.csc305.nfirme.visitors;

import edu.calpoly.csc305.config.grammars.AggregatorConfigParser;
import edu.calpoly.csc305.config.grammars.AggregatorConfigParserBaseVisitor;
import edu.calpoly.csc305.nfirme.datasources.DataSource;
import edu.calpoly.csc305.nfirme.datasources.FileSource;
import edu.calpoly.csc305.nfirme.datasources.UrlSource;
import java.io.IOException;

public class DataSourceVisitor extends AggregatorConfigParserBaseVisitor<DataSource> {
  @Override
  public DataSource visitFileSourceType(AggregatorConfigParser.FileSourceTypeContext ctx) {
    String location = ctx.sourceLocation().LINE().toString().trim();
    return new FileSource(location);
  }

  @Override
  public DataSource visitUrlSourceType(AggregatorConfigParser.UrlSourceTypeContext ctx) {
    try {
      String address = ctx.sourceAddress().LINE().toString().trim();
      return new UrlSource(address);
    } catch (IOException e) {
      return null;
    }
  }
}
