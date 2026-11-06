public class MobileAppSubscriber implements Subscriber {
    private final String name;
    private final String deviceId;

    public MobileAppSubscriber(String name, String deviceId) {
        this.name = name;
        this.deviceId = deviceId;
    }

    @Override public String getName() { return name; }

    @Override
    public void update(News news) {
        System.out.println("  Push to device " + deviceId + " (" + name + "): "
                + news.getHeadline() + " - " + news.getContent());
    }
}