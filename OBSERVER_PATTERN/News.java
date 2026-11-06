import java.time.LocalDateTime;

public final class News {
    private final String headline;
    private final String content;
    private final Category category;
    private final LocalDateTime publishedAt;

    public News(String headline, String content, Category category) {
        this.headline = headline;
        this.content = content;
        this.category = category;
        this.publishedAt = LocalDateTime.now();
    }

    public String getHeadline() { return headline; }
    public String getContent() { return content; }
    public Category getCategory() { return category; }
    public LocalDateTime getPublishedAt() { return publishedAt; }

    @Override
    public String toString() {
        return "[" + category + "] " + headline;
    }
}