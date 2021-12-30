package displays;

import elements.DisplayElement;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import models.BaseData;
import models.WeatherData;
import publishers.WeatherDataPublisher;
import subscribers.Subscriber;

@Log4j2
@Getter
@Setter
public class HeatIndexDisplay implements Subscriber, DisplayElement {
    private Integer temperature;
    private Integer humidity;
    private final WeatherDataPublisher weatherDataPublisher;

    public HeatIndexDisplay(final WeatherDataPublisher weatherDataPublisher) {
        this.weatherDataPublisher = weatherDataPublisher;
        weatherDataPublisher.addSubscriber(this);
    }

    @Override
    public void display() {
        log.info("Heat index is {}", (getHumidity()*getTemperature())/2);
    }

    @Override
    public void update(BaseData data) {
        if (data instanceof WeatherData) {
            final WeatherData weatherData = (WeatherData) data;
            setHumidity(weatherData.getHumidity());
            setTemperature(weatherData.getTemperature());
            display();
        }
    }

    public void removeSubscription() {
        log.warn("Removing HeatIndexDisplay as subscriber");
        weatherDataPublisher.removeSubscriber(this);
    }
}
