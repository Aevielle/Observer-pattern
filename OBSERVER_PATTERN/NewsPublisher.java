import java.util.Set;

public interface NewsPublisher {
    void subscribe(Subscriber subscriber, Category... categories);

    void unsubscribe(Subscriber subscriber);

    void updatePreferences(Subscriber subscriber, Set<Category> categories);

    void publish(News news);
}