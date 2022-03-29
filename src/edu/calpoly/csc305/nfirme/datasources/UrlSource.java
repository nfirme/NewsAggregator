package edu.calpoly.csc305.nfirme.datasources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

public class UrlSource implements DataSource {
  private final URL url;
  private final Reader reader;

  public UrlSource(String urlString) throws IOException {
    this.url = new URL(urlString);
    this.reader = openReader();
  }

  public Reader getReader() {
    return this.reader;
  }

  public void cleanup() throws IOException {
    this.reader.close();
  }

  private Reader openReader() {
    try {
      InputStream stream = openStream();
      assert stream != null;
      return new BufferedReader(new InputStreamReader(stream));
    } catch (Exception e) {
      return null;
    }
  }

  private InputStream openStream() {
    try {
      return this.url.openStream();
    } catch (Exception e) {
      return null;
    }
  }
}
