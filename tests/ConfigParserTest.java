import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import edu.calpoly.csc305.config.grammars.AggregatorConfigLexer;
import edu.calpoly.csc305.config.grammars.AggregatorConfigParser;
import edu.calpoly.csc305.nfirme.datasources.FileSource;
import edu.calpoly.csc305.nfirme.processors.BaseProcessor;
import edu.calpoly.csc305.nfirme.visitors.ProcessorVisitor;
import java.io.StringReader;
import java.util.List;
import java.util.logging.Logger;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.Test;

class ConfigParserTest {
  String simpleJson = "{\n"
      + "  \"description\": \"Extend Assignment #2 to support processor configuration.\",\n"
      + "  \"publishedAt\": \"2020-10-10 17:32:17.601812\",\n"
      + "  \"title\": \"Assignment #3\",\n"
      + "  \"url\": \"https://canvas.calpoly.edu/courses/29932/assignments/134271\"\n"
      + "}";

  String configString = "url\n"
      + "name: Simple Url\n"
      + "format: simple\n"
      + "address: http://users.csc.calpoly.edu/~akeen/courses/csc305/simple.json\n";

  Logger logger = Logger.getLogger(ConfigParserTest.class.getName());

  @Test
  void testProcessorCreationFromNonEmptyConfig() {
    ParseTree parseTree = null;

    CommonTokenStream tokens = new CommonTokenStream(
        new AggregatorConfigLexer(CharStreams.fromString(configString)));
    AggregatorConfigParser parser = new AggregatorConfigParser(tokens);

    if (parser.getNumberOfSyntaxErrors() == 0) {
      parseTree = parser.sources();
    }

    List<BaseProcessor> processorList = parseTree.accept(
        new ProcessorVisitor(logger)
    );

    assertEquals(1, processorList.size());
  }

  @Test
  void testProcessorCreationFromEmptyConfig() {
    FileSource mockedFileSource = mock(FileSource.class);
    when(mockedFileSource.getReader()).thenReturn(new StringReader(simpleJson));

    ParseTree parseTree = null;

    CommonTokenStream tokens = new CommonTokenStream(
        new AggregatorConfigLexer(CharStreams.fromString("")));
    AggregatorConfigParser parser = new AggregatorConfigParser(tokens);

    if (parser.getNumberOfSyntaxErrors() == 0) {
      parseTree = parser.sources();
    }

    List<BaseProcessor> processorList = parseTree.accept(
        new ProcessorVisitor(logger)
    );

    assertEquals(0, processorList.size());
  }
}
