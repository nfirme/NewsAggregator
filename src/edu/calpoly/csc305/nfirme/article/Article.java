package edu.calpoly.csc305.nfirme.article;

import java.time.LocalDateTime;
import java.util.Objects;

public class Article {
  private final String title;
  private final String description;
  private final String url;
  private final LocalDateTime publishedAt;

  /**
   * Constructs an Article object, checking that each field is non-null.
   *
   * @param title The title of the article
   * @param description The description of the article
   * @param url The url of the article
   * @param publishedAt The date and time the article was published at
   */
  public Article(String title, String description, String url, LocalDateTime publishedAt) {
    this.title = Objects.requireNonNull(title, "Title field must not be null");
    this.description = Objects.requireNonNull(description, "Description field must not be null");
    this.url = Objects.requireNonNull(url, "URL field must not be null");
    this.publishedAt = Objects.requireNonNull(publishedAt, "publishedAt field must not be null");
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public String getUrl() {
    return url;
  }

  public LocalDateTime getPublishedAt() {
    return publishedAt;
  }

  @Override
  public String toString() {
    return String.format("%s%n%s%n%s%n%s%n",
      getTitle(), getDescription(), getPublishedAt().toString(), getUrl());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Article article = (Article) o;

    if (!title.equals(article.title)) {
      return false;
    }

    if (!description.equals(article.description)) {
      return false;
    }

    if (!url.equals(article.url)) {
      return false;
    }

    return publishedAt.equals(article.publishedAt);
  }

  @Override
  public int hashCode() {
    int result = title.hashCode();
    result = 31 * result + description.hashCode();
    result = 31 * result + url.hashCode();
    result = 31 * result + publishedAt.hashCode();
    return result;
  }
}
