package edu.calpoly.csc305.nfirme;

import edu.calpoly.csc305.nfirme.article.Article;
import edu.calpoly.csc305.nfirme.processors.BaseProcessor;
import edu.calpoly.csc305.nfirme.utilities.ConfigParser;
import edu.calpoly.csc305.nfirme.visitors.ProcessorVisitor;
import java.util.List;
import java.util.logging.Logger;

public class Main {
  /**
   * Gets a config file given from a command line argument and creates
   * a list of Processors, each generating a list of Articles.
   *
   * @param args The config file to specify formats and sources
   */
  public static void main(String[] args) {
    Logger logger = Logger.getLogger(Main.class.getName());
    List<BaseProcessor> processorList = ConfigParser.parseFile(args[0], logger).accept(
        new ProcessorVisitor(logger)
    );

    for (BaseProcessor proc : processorList) {
      for (Article article : proc.process()) {
        System.out.println(article);
      }
    }
  }
}
