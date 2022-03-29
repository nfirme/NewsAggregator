import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import edu.calpoly.csc305.nfirme.parsers.NewsApiParser;
import edu.calpoly.csc305.nfirme.parsers.Parser;
import edu.calpoly.csc305.nfirme.parsers.SimpleParser;
import java.io.Reader;
import java.io.StringReader;
import java.util.logging.Logger;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

class ParserTest {
  Logger simpleLogger = Logger.getLogger(SimpleParser.class.getName());
  Logger newsLogger = Logger.getLogger(NewsApiParser.class.getName());
  Parser simpleParser = new SimpleParser(simpleLogger);
  Parser newsParser = new NewsApiParser(newsLogger);

  Reader simpleStringReader = new StringReader("{\n" +
    "  \"description\": \"Extend Assignment #1 to support multiple sources and to introduce source processor.\",\n" +
    "  \"publishedAt\": \"2021-04-16 09:53:23.709229\",\n" +
    "  \"title\": \"Assignment #2\",\n" +
    "  \"url\": \"https://canvas.calpoly.edu/courses/55411/assignments/274503\"\n" +
    "}\n");

  Reader newsStringReader = new StringReader("{\n" +
    "  \"status\": \"ok\",\n" +
    "  \"totalResults\": 38,\n" +
    "  \"articles\": [\n" +
    "    {\n" +
    "      \"source\": {\n" +
    "        \"id\": \"cnn\",\n" +
    "        \"name\": \"CNN\"\n" +
    "      },\n" +
    "      \"author\": \"By <a href=\\\"/profiles/julia-hollingsworth\\\">Julia Hollingsworth</a>, CNN\",\n" +
    "      \"title\": \"The latest on the coronavirus pandemic and vaccines: Live updates - CNN\",\n" +
    "      \"description\": \"The coronavirus pandemic has brought countries to a standstill. Meanwhile, vaccinations have already started in some countries as cases continue to rise. Follow here for the latest.\",\n" +
    "      \"url\": \"https://www.cnn.com/world/live-news/coronavirus-pandemic-vaccine-updates-03-24-21/index.html\",\n" +
    "      \"urlToImage\": \"https://cdn.cnn.com/cnnnext/dam/assets/200213175739-03-coronavirus-0213-super-tease.jpg\",\n" +
    "      \"publishedAt\": \"2021-03-24T22:32:00Z\",\n" +
    "      \"content\": \"A senior European diplomat is urging caution over the use of proposed new rules that would govern exports of Covid-19 vaccines to outside of the EU. The rules were announced by the European Commissio… [+2476 chars]\"\n" +
    "    }\n" +
    "  ]\n" +
    "}");

  @Test
  void testGetSimpleObjectFromReader() {
    JSONObject expectedObject = new JSONObject();
    expectedObject.put("description", "Extend Assignment #1 to support multiple sources and to introduce source processor.");
    expectedObject.put("publishedAt", "2021-04-16 09:53:23.709229");
    expectedObject.put("title", "Assignment #2");
    expectedObject.put("url", "https://canvas.calpoly.edu/courses/55411/assignments/274503");
    JSONObject actualObject = simpleParser.getObjectFromReader(simpleStringReader);
    assertEquals(expectedObject.toString(), actualObject.toString());
  }

  @Test
  void testSimpleParseFieldText() {
    JSONObject obj = new JSONObject().put("title", "Cat in the Hat");
    String actual = simpleParser.parseField(obj, "title");
    assertEquals("Cat in the Hat", actual);
  }

  @Test
  void testSimpleParseFieldNullValue() {
    JSONObject obj = new JSONObject();
    obj.put("description", "A documentary about Dr. Seuss");
    String msg = simpleParser.parseField(obj, "title");
    assertNull(msg);
  }

  @Test
  void testComplexParseFieldText() {
    JSONObject obj = new JSONObject().put("title", "Cat in the Hat");
    String actual = newsParser.parseField(obj, "title");
    assertEquals("Cat in the Hat", actual);
  }

  @Test
  void testComplexParseFieldNullValue() {
    JSONObject obj = new JSONObject();
    obj.put("description", "A documentary about Dr. Seuss");
    String msg = newsParser.parseField(obj, "title");
    assertNull(msg);
  }
}
