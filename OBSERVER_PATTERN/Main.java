import java.util.EnumSet;

public class Main {
    public static void main(String[] args) {
        NewsPublisher agency = new NewsAgency();

        Subscriber alice = new EmailSubscriber("Alice", "alice@example.com");
        Subscriber bob = new MobileAppSubscriber("Bob", "device-42");
        Subscriber carol = new EmailSubscriber("Carol", "carol@example.com");

        agency.subscribe(alice);                                   // all categories
        agency.subscribe(bob, Category.SPORTS, Category.TECHNOLOGY);
        agency.subscribe(carol, Category.BUSINESS);

        agency.publish(new News("Team wins championship", "Final score 3-1.", Category.SPORTS));
        agency.publish(new News("Markets rally", "Index up 2%.", Category.BUSINESS));

        System.out.println("\n-- Bob unsubscribes; Carol adds TECHNOLOGY --");
        agency.unsubscribe(bob);
        agency.updatePreferences(carol, EnumSet.of(Category.BUSINESS, Category.TECHNOLOGY));

        agency.publish(new News("New chip announced", "Faster and cooler.", Category.TECHNOLOGY));
        agency.publish(new News("Election results in", "Turnout was high.", Category.POLITICS));
    }
}