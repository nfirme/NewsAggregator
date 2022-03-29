package edu.calpoly.csc305.nfirme.datasources;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class FileSource implements DataSource {
  private final File file;
  private final Reader reader;

  public FileSource(String fileName) {
    this.file = new File(fileName);
    this.reader = openReader();
  }

  public Reader getReader() {
    return this.reader;
  }

  /**
   * Closes the FileReader.
   *
   * @throws IOException if the reader cannot be closed
   */
  public void cleanup() throws IOException {
    this.reader.close();
  }

  private Reader openReader() {
    try {
      return new FileReader(this.file);
    } catch (FileNotFoundException e) {
      return null;
    }
  }
}
