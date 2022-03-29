package edu.calpoly.csc305.nfirme.visitors;

import edu.calpoly.csc305.config.grammars.AggregatorConfigParser;
import edu.calpoly.csc305.config.grammars.AggregatorConfigParserBaseVisitor;
import edu.calpoly.csc305.nfirme.processors.BaseProcessor;
import edu.calpoly.csc305.nfirme.processors.FilterProcessor;
import edu.calpoly.csc305.nfirme.processors.Processor;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ProcessorVisitor extends AggregatorConfigParserBaseVisitor<List<BaseProcessor>> {
  private final Logger logger;

  public ProcessorVisitor(Logger logger) {
    this.logger = logger;
  }

  @Override
  public List<BaseProcessor> visitSources(AggregatorConfigParser.SourcesContext ctx) {
    List<BaseProcessor> processors = new ArrayList<>();
    ParserVisitor parserVisitor = new ParserVisitor(logger);
    DataSourceVisitor dataSourceVisitor = new DataSourceVisitor();
    FilterVisitor filterVisitor = new FilterVisitor();
    for (AggregatorConfigParser.SourceTypeContext sourceType : ctx.sourceType()) {
      processors.add(new FilterProcessor(
          new Processor(
              sourceType.accept(parserVisitor),
              sourceType.accept(dataSourceVisitor)),
          sourceType.accept(filterVisitor)));
    }

    return processors;
  }
}
