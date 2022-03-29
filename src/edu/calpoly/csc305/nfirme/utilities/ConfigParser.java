package edu.calpoly.csc305.nfirme.utilities;

import edu.calpoly.csc305.config.grammars.AggregatorConfigLexer;
import edu.calpoly.csc305.config.grammars.AggregatorConfigParser;
import java.io.IOException;
import java.util.logging.Logger;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class ConfigParser {

  private ConfigParser() {
    throw new IllegalArgumentException("Utility class");
  }

  /**
   * Parses a config file and generates a parse tree to be traversed
   * using visitors and generating a list of Processors.
   *
   * @param filename The name of the config file to parse.
   * @param logger The logger object passed to the Parser
   * @return A parseTree with a SourceTypeContext as the root
   */
  public static ParseTree parseFile(String filename, Logger logger) {
    ParseTree parseTree = null;

    try {
      CommonTokenStream tokens = new CommonTokenStream(
          new AggregatorConfigLexer(CharStreams.fromFileName(filename)));
      AggregatorConfigParser parser = new AggregatorConfigParser(tokens);

      if (parser.getNumberOfSyntaxErrors() == 0) {
        parseTree = parser.sources();
      }
    } catch (IOException e) {
      logger.warning(e.getMessage());
    }

    return parseTree;
  }
}
