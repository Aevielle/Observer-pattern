import java.util.EnumMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executor;

public class NewsAgency implements NewsPublisher {
    private final Map<Category, Set<Subscriber>> subscribers = new EnumMap<>(Category.class);
    private final Executor executor;

    public NewsAgency() {
        this(Runnable::run);
    }

    public NewsAgency(Executor executor) {
        this.executor = executor;
        for (Category c : Category.values()) {
            subscribers.put(c, new CopyOnWriteArraySet<>());
        }
    }

    @Override
    public void subscribe(Subscriber subscriber, Category... categories) {
        Category[] chosen = (categories == null || categories.length == 0) ? Category.values() : categories;
        for (Category c : chosen) {
            subscribers.get(c).add(subscriber);
        }
    }

    @Override
    public void unsubscribe(Subscriber subscriber) {
        for (Set<Subscriber> set : subscribers.values()) {
            set.remove(subscriber);
        }
    }

    @Override
    public void updatePreferences(Subscriber subscriber, Set<Category> categories) {
        unsubscribe(subscriber);
        subscribe(subscriber, categories.toArray(new Category[0]));
    }

    @Override
    public void publish(News news) {
        System.out.println("\n>>> NewsAgency publishing: " + news);
        for (Subscriber s : subscribers.get(news.getCategory())) {
            executor.execute(() -> {
                try {
                    s.update(news);
                } catch (RuntimeException e) { // one failing subscriber must not affect others
                    System.err.println("Delivery to " + s.getName() + " failed: " + e.getMessage());
                }
            });
        }
    }
}