package publishers;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import models.WeatherData;
import subscribers.Subscriber;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
public class WeatherDataPublisher implements Publisher {
    private final List<Subscriber> subscriberList;
    private WeatherData weatherData;

    private static final class WeatherDataPublisherHolder {
        public static final WeatherDataPublisher weatherDataPublisherInstance = WeatherDataPublisher.builder()
                .subscriberList(new ArrayList<>())
                .weatherData(WeatherData.builder().build())
                .build();
    }

    public static WeatherDataPublisher getInstance() {
        return WeatherDataPublisherHolder.weatherDataPublisherInstance;
    }

    @Override
    public void addSubscriber(final Subscriber subscriber) {
        this.subscriberList.add(subscriber);
    }

    @Override
    public void removeSubscriber(final Subscriber subscriber) {
        int subscriberIndex = subscriberList.indexOf(subscriber);
        if (subscriberIndex >= 0) {
            subscriberList.remove(subscriberIndex);
        }
    }

    @Override
    public void notifyAllSubscriber() {
        for (Subscriber subscriber: subscriberList) {
            subscriber.update(weatherData);

        }
    }

    public void measurementsChanged() {
        notifyAllSubscriber();
    }
}

