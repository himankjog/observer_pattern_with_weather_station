package publishers;

import subscribers.Subscriber;

public interface Publisher {
    void addSubscriber(final Subscriber subscriber);
    void removeSubscriber(final Subscriber subscriber);
    void notifyAllSubscriber();
}
