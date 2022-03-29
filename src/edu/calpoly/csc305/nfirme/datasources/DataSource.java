package edu.calpoly.csc305.nfirme.datasources;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;

public interface DataSource {
  Reader getReader() throws FileNotFoundException;

  void cleanup() throws IOException;
}
