package edu.calpoly.csc305.nfirme.processors;

import edu.calpoly.csc305.nfirme.article.Article;
import java.util.List;

public interface BaseProcessor {
  List<Article> process();
}
